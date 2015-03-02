package com.example.doug.lab4act;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.doug.lab4act.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Lab 3
 * Douglas Applegate
 * Nick Love
 * Dave McVicar
 * Joshua Spencer
 */

public class SettingActivity extends Activity {
    public static final int REQUEST_CODE_TAKE_FROM_CAMERA = 0;
    public static final int REQUEST_CODE_CROP_PHOTO = 2;

    private static final String IMAGE_UNSPECIFIED = "image/*";
    private static final String URI_INSTANCE_STATE_KEY = "saved_uri";

    private Uri imageCaptureUri;
    private ImageView mImageView;

    public static final String PREFS_NAME = "com.example.joshuaspencer.lab3act";

    private EditText userName;
    private EditText userEmail;
    private EditText userPhoneNumber;
    private RadioButton userMale;
    private RadioButton userFemale;
    private EditText userClass;
    private EditText userMajor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
<<<<<<< HEAD
        setContentView(R.layout.SettingLayout);
=======
        setContentView(R.layout.activity_lab4);
>>>>>>> origin/master
=======
        setContentView(R.layout.activity_lab4);
>>>>>>> origin/master

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);


        mImageView = (ImageView) findViewById(R.id.profile_picture);

        userName = (EditText) findViewById(R.id.user_name_edit);
        userEmail = (EditText) findViewById(R.id.user_email_edit);
        userPhoneNumber = (EditText) findViewById(R.id.user_phone_number);
        userMale = (RadioButton) findViewById(R.id.radio_ninjas);
        userFemale = (RadioButton) findViewById(R.id.radio_pirates);
        userClass = (EditText) findViewById(R.id.user_class_edit);
        userMajor = (EditText) findViewById(R.id.user_major_edit);

        userName.setText(settings.getString("Name",""));
        userEmail.setText(settings.getString("Email",""));
        userPhoneNumber.setText(settings.getString("Phone",""));
        userMale.setChecked(settings.getBoolean("Male",true));
        userFemale.setChecked(settings.getBoolean("Female",false));
        userClass.setText(settings.getString("ClassYear",""));
        userMajor.setText(settings.getString("Major",""));

        if(savedInstanceState != null)
            imageCaptureUri = savedInstanceState.getParcelable(URI_INSTANCE_STATE_KEY);

        loadSnap();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Saving image uri before activity goes into bground
        outState.putParcelable(URI_INSTANCE_STATE_KEY, imageCaptureUri);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK)
            return;
        switch (requestCode){
            case REQUEST_CODE_TAKE_FROM_CAMERA:
                cropImage(); //Send picture taken to be cropped
                break;
            case REQUEST_CODE_CROP_PHOTO:
                Bundle extras = data.getExtras();
                if (extras != null) {
                    mImageView.setImageBitmap((Bitmap) extras.getParcelable("data"));
                }

                //delete temp image before crop
                File f = new File(imageCaptureUri.getPath());
                if (f.exists())
                    f.delete();
                break;
        }
    }


    /**********On Button Clicks************************************************************************/

    public void onChangePictureClick(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //constructing temporary image path
        imageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp_"
                + String.valueOf(System.currentTimeMillis())
                + ".jpg"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageCaptureUri);
        intent.putExtra("return-data", true);

        try {
            startActivityForResult(intent, REQUEST_CODE_TAKE_FROM_CAMERA);
        }
        catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onSave(View view){
        Toast.makeText(getApplicationContext(), "Saved",
                Toast.LENGTH_LONG).show();

        saveSnap();

        System.out.print("Pressed save button!");

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("Name",userName.getText().toString());
        editor.putString("Email",userEmail.getText().toString());
        editor.putString("Phone",userPhoneNumber.getText().toString());

        //Radio buttons save
        editor.putBoolean("Male",userMale.isChecked());
        editor.putBoolean("Female",userFemale.isChecked());

        editor.putString("ClassYear",userClass.getText().toString());
        editor.putString("Major",userMajor.getText().toString());

        editor.commit();

    }

    public void leaveApp(){
        Toast.makeText(getApplicationContext(), "Goodbye",
                Toast.LENGTH_LONG).show();

        finish();
    }

    /**********Private Helpers*************************************************************************/

    private void loadSnap() {
        // Load profile photo from internal storage
        try {
            FileInputStream fis = openFileInput(getString(R.string.profile_photo_file_name));
            Bitmap bmap = BitmapFactory.decodeStream(fis);
            mImageView.setImageBitmap(bmap);
            fis.close();
        } catch (IOException e) {
            // Default profile photo if no photo saved before.
            mImageView.setImageResource(R.drawable.ic_launcher);
        }
    }

    private void saveSnap() {
        // Commit all the changes into preference file
        // Save profile image into internal storage.
        mImageView.buildDrawingCache();
        Bitmap bmap = mImageView.getDrawingCache();
        try {
            FileOutputStream fos = openFileOutput(
                    getString(R.string.profile_photo_file_name), MODE_PRIVATE);
            bmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void cropImage() {
        // Use existing crop activity.
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(imageCaptureUri, IMAGE_UNSPECIFIED);

        // Specify image size
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);

        // Specify aspect ratio, 1:1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        // REQUEST_CODE_CROP_PHOTO is an integer tag you defined to
        // identify the activity in onActivityResult() when it returns
        startActivityForResult(intent, REQUEST_CODE_CROP_PHOTO);
    }

}
