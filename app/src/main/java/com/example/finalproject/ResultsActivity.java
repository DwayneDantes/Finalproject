package com.example.finalproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private TextView textViewFirstName, textViewLastName, textViewEmail, textViewPhone, textViewHeight,
            textViewWeight, textViewPagibig, textViewPhilhealth, textViewTin, textViewGsis,
            textViewEmergencyContactName, textViewEmergencyContactPhone, textViewGender,
            textViewCivilStatus, textViewRelationship;

    private TextView textViewElementarySchool;
    private TextView textViewElementaryDegree;
    private TextView textViewSecondarySchool;
    private TextView textViewSecondaryDegree;
    private TextView textViewVocationalSchool;
    private TextView textViewVocationalDegree;
    private TextView textViewCollegeSchool;
    private TextView textViewCollegeDegree;
    private TextView textViewGraduateSchool;
    private TextView textViewGraduateDegree;
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferences1;

    private SharedPreferences sharedPreferences2;
    private String administrativeOffense;
    private String administrativeOffenseDetails;
    private String criminallyCharged;
    private String criminallyChargedDetails;
    private String convictedOfCrime;
    private String convictedOfCrimeDetails;
    private String indigenousGroup;
    private String indigenousGroupDetails;
    private String personWithDisability;
    private String personWithDisabilityDetails;
    private String soloParent;
    private String soloParentDetails;

    private TextView textViewAdministrativeOffense;
    private TextView textViewAdministrativeOffenseDetails;
    private TextView textViewCriminallyCharged;
    private TextView textViewCriminallyChargedDetails;
    private TextView textViewConvictedOfCrime;
    private TextView textViewConvictedOfCrimeDetails;
    private TextView textViewIndigenousGroup;
    private TextView textViewIndigenousGroupDetails;
    private TextView textViewPersonWithDisability;
    private TextView textViewPersonWithDisabilityDetails;
    private TextView textViewSoloParent;
    private TextView textViewSoloParentDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        // Enable the back button in the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity if needed
            }
        });

        // Find views
        textViewFirstName = findViewById(R.id.textViewFirstName);
        textViewLastName = findViewById(R.id.textViewLastName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewHeight = findViewById(R.id.textViewHeight);
        textViewWeight = findViewById(R.id.textViewWeight);
        textViewPagibig = findViewById(R.id.textViewPagibig);
        textViewPhilhealth = findViewById(R.id.textViewPhilhealth);
        textViewTin = findViewById(R.id.textViewTin);
        textViewGsis = findViewById(R.id.textViewGsis);
        textViewEmergencyContactName = findViewById(R.id.textViewEmergencyContactName);
        textViewEmergencyContactPhone = findViewById(R.id.textViewEmergencyContactPhone);
        textViewGender = findViewById(R.id.textViewGender);
        textViewCivilStatus = findViewById(R.id.textViewCivilStatus);
        textViewRelationship = findViewById(R.id.textViewRelationship);
        textViewElementarySchool = findViewById(R.id.textViewElementarySchool);
        textViewElementaryDegree = findViewById(R.id.textViewElementaryDegree);
        textViewSecondarySchool = findViewById(R.id.textViewSecondarySchool);
        textViewSecondaryDegree = findViewById(R.id.textViewSecondaryDegree);
        textViewVocationalSchool = findViewById(R.id.textViewVocationalSchool);
        textViewVocationalDegree = findViewById(R.id.textViewVocationalDegree);
        textViewCollegeSchool = findViewById(R.id.textViewCollegeSchool);
        textViewCollegeDegree = findViewById(R.id.textViewCollegeDegree);
        textViewGraduateSchool = findViewById(R.id.textViewGraduateSchool);
        textViewGraduateDegree = findViewById(R.id.textViewGraduateDegree);

        textViewAdministrativeOffense = findViewById(R.id.textViewAdministrativeOffense);
        textViewAdministrativeOffenseDetails = findViewById(R.id.textViewAdministrativeOffenseDetails);
        textViewCriminallyCharged = findViewById(R.id.textViewCriminallyCharged);
        textViewCriminallyChargedDetails = findViewById(R.id.textViewCriminallyChargedDetails);
        textViewConvictedOfCrime = findViewById(R.id.textViewConvictedOfCrime);
        textViewConvictedOfCrimeDetails = findViewById(R.id.textViewConvictedOfCrimeDetails);
        textViewIndigenousGroup = findViewById(R.id.textViewIndigenousGroup);
        textViewIndigenousGroupDetails = findViewById(R.id.textViewIndigenousGroupDetails);
        textViewPersonWithDisability = findViewById(R.id.textViewPersonWithDisability);
        textViewPersonWithDisabilityDetails = findViewById(R.id.textViewPersonWithDisabilityDetails);
        textViewSoloParent = findViewById(R.id.textViewSoloParent);
        textViewSoloParentDetails = findViewById(R.id.textViewSoloParentDetails);

        // Retrieve data from SharedPreferences
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String photoUriString = sharedPreferences.getString("photoUri", null);
        String firstName = sharedPreferences.getString("firstName", "");
        String lastName = sharedPreferences.getString("lastName", "");
        String email = sharedPreferences.getString("email", "");
        String phone = sharedPreferences.getString("phone", "");
        String height = sharedPreferences.getString("height", "");
        String weight = sharedPreferences.getString("weight", "");
        String pagibig = sharedPreferences.getString("pagibig", "");
        String philhealth = sharedPreferences.getString("philhealth", "");
        String tin = sharedPreferences.getString("tin", "");
        String gsis = sharedPreferences.getString("gsis", "");
        String emergencyContactName = sharedPreferences.getString("emergencyContactName", "");
        String emergencyContactPhone = sharedPreferences.getString("emergencyContactPhone", "");
        String gender = sharedPreferences.getString("gender", "");
        String civilStatus = sharedPreferences.getString("civilStatus", "");
        String relationship = sharedPreferences.getString("relationship", "");
        sharedPreferences1 = getSharedPreferences("MyPrefs1", MODE_PRIVATE);
        String elementarySchool = sharedPreferences1.getString("elementarySchool", "");
        String elementaryDegree = sharedPreferences1.getString("elementaryDegree", "");
        String secondarySchool = sharedPreferences1.getString("secondarySchool", "");
        String secondaryDegree = sharedPreferences1.getString("secondaryDegree", "");
        String vocationalSchool = sharedPreferences1.getString("vocationalSchool", "");
        String vocationalDegree = sharedPreferences1.getString("vocationalDegree", "");
        String collegeSchool = sharedPreferences1.getString("collegeSchool", "");
        String collegeDegree = sharedPreferences1.getString("collegeDegree", "");
        String graduateSchool = sharedPreferences1.getString("graduateSchool", "");
        String graduateDegree = sharedPreferences1.getString("graduateDegree", "");
        sharedPreferences2 = getSharedPreferences("MyPrefs2", MODE_PRIVATE);
        administrativeOffense = sharedPreferences2.getString("administrativeOffense", "");
        administrativeOffenseDetails = sharedPreferences2.getString("administrativeOffenseDetails", "");
        criminallyCharged = sharedPreferences2.getString("criminallyCharged", "");
        criminallyChargedDetails = sharedPreferences2.getString("criminallyChargedDetails", "");
        convictedOfCrime = sharedPreferences2.getString("convictedOfCrime", "");
        convictedOfCrimeDetails = sharedPreferences2.getString("convictedOfCrimeDetails", "");
        indigenousGroup = sharedPreferences2.getString("indigenousGroup", "");
        indigenousGroupDetails = sharedPreferences2.getString("indigenousGroupDetails", "");
        personWithDisability = sharedPreferences2.getString("personWithDisability", "");
        personWithDisabilityDetails = sharedPreferences2.getString("personWithDisabilityDetails", "");
        soloParent = sharedPreferences2.getString("soloParent", "");
        soloParentDetails = sharedPreferences2.getString("soloParentDetails", "");

        // Display data in TextViews
        textViewFirstName.setText("First Name: "+firstName);
        textViewLastName.setText("Last Name: "+lastName);
        textViewEmail.setText("Email: "+email);
        textViewPhone.setText("Phone: "+phone);
        textViewHeight.setText("Height: "+height);
        textViewWeight.setText("Weight: "+weight);
        textViewPagibig.setText("Pag-Ibig No.: "+pagibig);
        textViewPhilhealth.setText("PhilHealth No.: "+philhealth);
        textViewTin.setText("TIN No.: "+tin);
        textViewGsis.setText("GSIS No.: "+gsis);
        textViewEmergencyContactName.setText("Emergency Contact Name: "+emergencyContactName);
        textViewEmergencyContactPhone.setText("Emergency Contact Number: "+emergencyContactPhone);
        textViewGender.setText("Gender: "+gender);
        textViewCivilStatus.setText("Civil Status: "+civilStatus);
        textViewRelationship.setText("Relationship: "+relationship);
        textViewElementarySchool.setText("Elementary School: "+elementarySchool);
        textViewElementaryDegree.setText("Elementary Degree: "+elementaryDegree);
        textViewSecondarySchool.setText("Secondary School: "+ secondarySchool);
        textViewSecondaryDegree.setText("Secondary School Degree: "+secondaryDegree);
        textViewVocationalSchool.setText("Vocational School: "+vocationalSchool);
        textViewVocationalDegree.setText("Vocational Degree: "+vocationalDegree);
        textViewCollegeSchool.setText("College School: " + collegeSchool);
        textViewCollegeDegree.setText("College Degree: "+collegeDegree);
        textViewGraduateSchool.setText("Graduate School: "+graduateSchool);
        textViewGraduateDegree.setText("Graduate Degree: "+graduateDegree);
        textViewAdministrativeOffense.setText("Administrative Offense: "+administrativeOffense);
        textViewAdministrativeOffenseDetails.setText("Administrative Offense Details: "+administrativeOffenseDetails);
        textViewCriminallyCharged.setText("Criminally Charged? "+criminallyCharged);
        textViewCriminallyChargedDetails.setText("Criminal Charge Details: "+criminallyChargedDetails);
        textViewConvictedOfCrime.setText("Convicted of Crime? "+convictedOfCrime);
        textViewConvictedOfCrimeDetails.setText("Conviction Details: "+convictedOfCrimeDetails);
        textViewIndigenousGroup.setText("Part of Indigenous Group? "+indigenousGroup);
        textViewIndigenousGroupDetails.setText("Indigenous Group Details: "+indigenousGroupDetails);
        textViewPersonWithDisability.setText("Person with Disability? "+personWithDisability);
        textViewPersonWithDisabilityDetails.setText("PWD Details: "+personWithDisabilityDetails);
        textViewSoloParent.setText("Solo Parent? "+soloParent);
        textViewSoloParentDetails.setText("Details "+soloParentDetails);

        ImageView imageViewPhoto = findViewById(R.id.textViewPhotoUri);

        Uri photoUri = null;

        if (photoUriString != null) {
            photoUri = Uri.parse(photoUriString);
        }
        imageViewPhoto = findViewById(R.id.textViewPhotoUri);

        if (photoUri != null) {
            imageViewPhoto.setImageURI(photoUri);
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Call the default back button action (go back)
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
