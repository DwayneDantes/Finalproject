package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageViewPhoto;
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPhone, editTextHeight,
            editTextWeight, editTextPagibig, editTextPhilhealth, editTextTin, editTextGsis,
            editTextEmergencyContactName, editTextEmergencyContactPhone;
    private RadioGroup radioGroupGender, radioGroupCivilStatus;
    private Button buttonCamera, buttonSubmit;
    private Spinner spinnerRelationship;

    private String[] relationshipOptions = {"Father", "Mother", "Husband", "Wife", "Sister", "Brother", "Others"};
    private Bitmap photoBitmap;
    private Uri photoUri; // Uri to store the picture's content URI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Initialize views
        imageViewPhoto = findViewById(R.id.imageViewPhoto);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextPagibig = findViewById(R.id.editTextPagibig);
        editTextPhilhealth = findViewById(R.id.editTextPhilhealth);
        editTextTin = findViewById(R.id.editTextTin);
        editTextGsis = findViewById(R.id.editTextGsis);
        editTextEmergencyContactName = findViewById(R.id.editTextEmergencyContactName);
        editTextEmergencyContactPhone = findViewById(R.id.editTextEmergencyContactPhone);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioGroupCivilStatus = findViewById(R.id.radioGroupCivilStatus);
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        spinnerRelationship = findViewById(R.id.spinnerRelationship);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, relationshipOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRelationship.setAdapter(spinnerAdapter);

        // Set click listener for the camera button
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        // Set click listener for the submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle back button click
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create a temporary file to store the picture
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Grant read permission to the camera app
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create a unique file name for the picture
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String imageFileName = "JPEG_" + timeStamp + "_";

        // Get the directory where the picture will be stored
        File storageDir = getExternalCacheDirs()[0];
        if (storageDir == null) {
            storageDir = getCacheDir();
        }

        // Create the file
        File imageFile = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        return imageFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (photoUri != null) {
                // Display the captured image in the ImageView
                imageViewPhoto.setImageURI(photoUri);
            }
        }
    }

    private void submitForm() {
        // Get user inputs
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String height = editTextHeight.getText().toString().trim();
        String weight = editTextWeight.getText().toString().trim();
        String pagibig = editTextPagibig.getText().toString().trim();
        String philhealth = editTextPhilhealth.getText().toString().trim();
        String tin = editTextTin.getText().toString().trim();
        String gsis = editTextGsis.getText().toString().trim();
        String emergencyContactName = editTextEmergencyContactName.getText().toString().trim();
        String emergencyContactPhone = editTextEmergencyContactPhone.getText().toString().trim();
        String gender = getSelectedRadioButtonText(radioGroupGender);
        String civilStatus = getSelectedRadioButtonText(radioGroupCivilStatus);
        String relationship = spinnerRelationship.getSelectedItem().toString();

        // Check if any field is empty (except Pag-ibig No., Philhealth No., TIN No., and GSIS No.)
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty()
                || height.isEmpty() || weight.isEmpty() || emergencyContactName.isEmpty()
                || emergencyContactPhone.isEmpty() || gender.isEmpty() || civilStatus.isEmpty()) {
            Toast.makeText(this, "Please fill in all necessary information", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save the data to SharedPreferences
        saveDataToSharedPreferences(firstName, lastName, email, phone, height, weight, pagibig,
                philhealth, tin, gsis, emergencyContactName, emergencyContactPhone, gender,
                civilStatus, relationship);

        // Start the next activity (e.g., EduBackgroundActivity)
        Intent intent = new Intent(MainActivity.this, EduBackgroundActivity.class);
        startActivity(intent);
    }

    private String getSelectedRadioButtonText(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        if (radioButton != null) {
            return radioButton.getText().toString();
        }
        return "";
    }

    private void saveDataToSharedPreferences(String firstName, String lastName, String email, String phone,
                                             String height, String weight, String pagibig, String philhealth,
                                             String tin, String gsis, String emergencyContactName,
                                             String emergencyContactPhone, String gender, String civilStatus,
                                             String relationship) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firstName", firstName);
        editor.putString("lastName", lastName);
        editor.putString("email", email);
        editor.putString("phone", phone);
        editor.putString("height", height);
        editor.putString("weight", weight);
        editor.putString("pagibig", pagibig);
        editor.putString("philhealth", philhealth);
        editor.putString("tin", tin);
        editor.putString("gsis", gsis);
        editor.putString("emergencyContactName", emergencyContactName);
        editor.putString("emergencyContactPhone", emergencyContactPhone);
        editor.putString("gender", gender);
        editor.putString("civilStatus", civilStatus);
        editor.putString("relationship", relationship);
        editor.apply();

        if (photoUri != null) {
            // Save the picture's file path to SharedPreferences
            editor.putString("photoUri", photoUri.toString());
            editor.apply();
        }
    }
}
