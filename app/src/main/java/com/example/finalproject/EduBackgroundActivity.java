package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EduBackgroundActivity extends AppCompatActivity {

    private CheckBox checkBoxElementary;
    private CheckBox checkBoxSecondary;
    private CheckBox checkBoxVocational;
    private CheckBox checkBoxCollege;
    private CheckBox checkBoxGraduate;

    private EditText editTextElementarySchool;
    private EditText editTextElementaryDegree;
    private EditText editTextSecondarySchool;
    private EditText editTextSecondaryDegree;
    private EditText editTextVocationalSchool;
    private EditText editTextVocationalDegree;
    private EditText editTextCollegeSchool;
    private EditText editTextCollegeDegree;
    private EditText editTextGraduateSchool;
    private EditText editTextGraduateDegree;


    private SharedPreferences sharedPreferences1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_background);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        sharedPreferences1 = getSharedPreferences("MyPrefs1", MODE_PRIVATE);

        // Initialize views
        checkBoxElementary = findViewById(R.id.checkBoxElementary);
        checkBoxSecondary = findViewById(R.id.checkBoxSecondary);
        checkBoxVocational = findViewById(R.id.checkBoxVocational);
        checkBoxCollege = findViewById(R.id.checkBoxCollege);
        checkBoxGraduate = findViewById(R.id.checkBoxGraduate);

        editTextElementarySchool = findViewById(R.id.editTextElementarySchool);
        editTextElementaryDegree = findViewById(R.id.editTextElementaryDegree);
        editTextSecondarySchool = findViewById(R.id.editTextSecondarySchool);
        editTextSecondaryDegree = findViewById(R.id.editTextSecondaryDegree);
        editTextVocationalSchool = findViewById(R.id.editTextVocationalSchool);
        editTextVocationalDegree = findViewById(R.id.editTextVocationalDegree);
        editTextCollegeSchool = findViewById(R.id.editTextCollegeSchool);
        editTextCollegeDegree = findViewById(R.id.editTextCollegeDegree);
        editTextGraduateSchool = findViewById(R.id.editTextGraduateSchool);
        editTextGraduateDegree = findViewById(R.id.editTextGraduateDegree);

        checkBoxElementary.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editTextElementarySchool.setEnabled(isChecked);
            editTextElementaryDegree.setEnabled(isChecked);
        });

        checkBoxSecondary.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editTextSecondarySchool.setEnabled(isChecked);
            editTextSecondaryDegree.setEnabled(isChecked);
        });

        checkBoxVocational.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editTextVocationalSchool.setEnabled(isChecked);
            editTextVocationalDegree.setEnabled(isChecked);
        });

        checkBoxCollege.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editTextCollegeSchool.setEnabled(isChecked);
            editTextCollegeDegree.setEnabled(isChecked);
        });

        checkBoxGraduate.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editTextGraduateSchool.setEnabled(isChecked);
            editTextGraduateDegree.setEnabled(isChecked);
        });

        // Submit button click listener
        findViewById(R.id.buttonSubmit).setOnClickListener(view -> submitForm());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Call the default back button action (go back)
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        // Handle the back button click event
        // You can perform any additional actions here if needed
        super.onBackPressed();
    }

    private void submitForm() {
        // Check if any required fields are empty
        if (checkBoxElementary.isChecked() && (editTextElementarySchool.getText().toString().isEmpty()
                || editTextElementaryDegree.getText().toString().isEmpty())) {
            showToast("Please fill in all necessary information.");
            return;
        }

        if (checkBoxSecondary.isChecked() && (editTextSecondarySchool.getText().toString().isEmpty()
                || editTextSecondaryDegree.getText().toString().isEmpty())) {
            showToast("Please fill in all necessary information.");
            return;
        }

        if (checkBoxVocational.isChecked() && (editTextVocationalSchool.getText().toString().isEmpty()
                || editTextVocationalDegree.getText().toString().isEmpty())) {
            showToast("Please fill in all necessary information.");
            return;
        }

        if (checkBoxCollege.isChecked() && (editTextCollegeSchool.getText().toString().isEmpty()
                || editTextCollegeDegree.getText().toString().isEmpty())) {
            showToast("Please fill in all necessary information.");
            return;
        }

        if (checkBoxGraduate.isChecked() && (editTextGraduateSchool.getText().toString().isEmpty()
                || editTextGraduateDegree.getText().toString().isEmpty())) {
            showToast("Please fill in all necessary information.");
            return;
        }

        // Store the inputted data and use it in other activities as needed
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        editor.putString("elementarySchool", editTextElementarySchool.getText().toString().trim());
        editor.putString("elementaryDegree", editTextElementaryDegree.getText().toString().trim());
        editor.putString("secondarySchool", editTextSecondarySchool.getText().toString().trim());
        editor.putString("secondaryDegree", editTextSecondaryDegree.getText().toString().trim());
        editor.putString("vocationalSchool", editTextVocationalSchool.getText().toString().trim());
        editor.putString("vocationalDegree", editTextVocationalDegree.getText().toString().trim());
        editor.putString("collegeSchool", editTextCollegeSchool.getText().toString().trim());
        editor.putString("collegeDegree", editTextCollegeDegree.getText().toString().trim());
        editor.putString("graduateSchool", editTextGraduateSchool.getText().toString().trim());
        editor.putString("graduateDegree", editTextGraduateDegree.getText().toString().trim());
        editor.apply();

        Intent intent = new Intent(EduBackgroundActivity.this, CriminalRecordActivity.class);
        startActivity(intent);

        showToast("Form submitted successfully!");

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
