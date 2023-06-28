package com.example.finalproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CriminalRecordActivity extends AppCompatActivity {

    private RadioGroup radioGroupAdministrativeOffense;
    private EditText editTextAdministrativeOffenseDetails;
    private RadioGroup radioGroupCriminallyCharged;
    private EditText editTextCriminallyChargedDetails;
    private RadioGroup radioGroupConvictedOfCrime;
    private EditText editTextConvictedOfCrimeDetails;
    private RadioGroup radioGroupIndigenousGroup;
    private EditText editTextIndigenousGroupDetails;
    private RadioGroup radioGroupPersonWithDisability;
    private EditText editTextPersonWithDisabilityDetails;
    private RadioGroup radioGroupSoloParent;
    private EditText editTextSoloParentDetails;
    private Button buttonSubmit;

    private SharedPreferences sharedPreferences2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal_record);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Find views
        radioGroupAdministrativeOffense = findViewById(R.id.radioGroupAdministrativeOffense);
        editTextAdministrativeOffenseDetails = findViewById(R.id.editTextAdministrativeOffenseDetails);
        radioGroupCriminallyCharged = findViewById(R.id.radioGroupCriminallyCharged);
        editTextCriminallyChargedDetails = findViewById(R.id.editTextCriminallyChargedDetails);
        radioGroupConvictedOfCrime = findViewById(R.id.radioGroupConvictedOfCrime);
        editTextConvictedOfCrimeDetails = findViewById(R.id.editTextConvictedOfCrimeDetails);
        radioGroupIndigenousGroup = findViewById(R.id.radioGroupIndigenousGroup);
        editTextIndigenousGroupDetails = findViewById(R.id.editTextIndigenousGroupDetails);
        radioGroupPersonWithDisability = findViewById(R.id.radioGroupPersonWithDisability);
        editTextPersonWithDisabilityDetails = findViewById(R.id.editTextPersonWithDisabilityDetails);
        radioGroupSoloParent = findViewById(R.id.radioGroupSoloParent);
        editTextSoloParentDetails = findViewById(R.id.editTextSoloParentDetails);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        PreferenceManager.getDefaultSharedPreferences(this).edit().apply();

        sharedPreferences2 = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("MyPrefs2", MODE_PRIVATE);

        radioGroupAdministrativeOffense.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateEditTextAvailability(editTextAdministrativeOffenseDetails, checkedId);
            }
        });

        radioGroupCriminallyCharged.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateEditTextAvailability(editTextCriminallyChargedDetails, checkedId);
            }
        });

        radioGroupConvictedOfCrime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateEditTextAvailability(editTextConvictedOfCrimeDetails, checkedId);
            }
        });




        radioGroupIndigenousGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateEditTextAvailability(editTextIndigenousGroupDetails, checkedId);
            }
        });

        radioGroupPersonWithDisability.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateEditTextAvailability(editTextPersonWithDisabilityDetails, checkedId);
            }
        });

        radioGroupSoloParent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateEditTextAvailability(editTextSoloParentDetails, checkedId);
            }
        });


        // Set listener for submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    // Store the data
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("administrativeOffense", getRadioButtonSelectedText(radioGroupAdministrativeOffense));
                    editor.putString("administrativeOffenseDetails", editTextAdministrativeOffenseDetails.getText().toString().trim());
                    editor.putString("criminallyCharged", getRadioButtonSelectedText(radioGroupCriminallyCharged));
                    editor.putString("criminallyChargedDetails", editTextCriminallyChargedDetails.getText().toString().trim());
                    editor.putString("convictedOfCrime", getRadioButtonSelectedText(radioGroupConvictedOfCrime));
                    editor.putString("convictedOfCrimeDetails", editTextConvictedOfCrimeDetails.getText().toString().trim());
                    editor.putString("indigenousGroup", getRadioButtonSelectedText(radioGroupIndigenousGroup));
                    editor.putString("indigenousGroupDetails", editTextIndigenousGroupDetails.getText().toString().trim());
                    editor.putString("personWithDisability", getRadioButtonSelectedText(radioGroupPersonWithDisability));
                    editor.putString("personWithDisabilityDetails", editTextPersonWithDisabilityDetails.getText().toString().trim());
                    editor.putString("soloParent", getRadioButtonSelectedText(radioGroupSoloParent));
                    editor.putString("soloParentDetails", editTextSoloParentDetails.getText().toString().trim());
                    editor.apply();

                    // Start the next activity (ResultsActivity) and pass the stored data
                    Intent intent = new Intent(CriminalRecordActivity.this, ResultsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Call the default back button action (go back)
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private boolean validateForm() {
        if (radioGroupAdministrativeOffense.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please answer the question: Administrative Offense.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (isCheckedYes(radioGroupAdministrativeOffense) && editTextAdministrativeOffenseDetails.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide details for Administrative Offense.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radioGroupCriminallyCharged.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please answer the question: Criminally Charged.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (isCheckedYes(radioGroupCriminallyCharged) && editTextCriminallyChargedDetails.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide details for Criminally Charged.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radioGroupConvictedOfCrime.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please answer the question: Convicted of Crime.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (isCheckedYes(radioGroupConvictedOfCrime) && editTextConvictedOfCrimeDetails.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide details for Convicted of Crime.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radioGroupIndigenousGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please answer the question: Indigenous Group.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (isCheckedYes(radioGroupIndigenousGroup) && editTextIndigenousGroupDetails.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide details for Indigenous Group.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radioGroupPersonWithDisability.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please answer the question: Person with Disability.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (isCheckedYes(radioGroupPersonWithDisability) && editTextPersonWithDisabilityDetails.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide details for Person with Disability.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radioGroupSoloParent.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please answer the question: Solo Parent.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (isCheckedYes(radioGroupSoloParent) && editTextSoloParentDetails.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide details for Solo Parent.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private String getRadioButtonSelectedText(RadioGroup radioGroup) {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        if (radioButtonId != -1) {
            RadioButton radioButton = findViewById(radioButtonId);
            return radioButton.getText().toString();
        }
        return "";
    }
    private void updateEditTextAvailability(EditText editText, int checkedId) {
        RadioButton radioButton = findViewById(checkedId);
        if (radioButton != null && radioButton.getId() == R.id.radioButtonAdministrativeOffenseYes
                || radioButton.getId() == R.id.radioButtonCriminallyChargedYes
                || radioButton.getId() == R.id.radioButtonConvictedOfCrimeYes
                || radioButton.getId() == R.id.radioButtonIndigenousGroupYes
                || radioButton.getId() == R.id.radioButtonPersonWithDisabilityYes
                || radioButton.getId() == R.id.radioButtonSoloParentYes) {
            editText.setEnabled(true);
            editText.setFocusable(true);
        } else {
            editText.setEnabled(false);
            editText.setFocusable(false);
            editText.setText(""); // Clear the text
        }
    }

    private boolean isCheckedYes(RadioGroup radioGroup) {
        return getRadioButtonSelectedText(radioGroup).equals("Yes");
    }
}
