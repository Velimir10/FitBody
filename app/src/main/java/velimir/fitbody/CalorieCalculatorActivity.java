package velimir.fitbody;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import velimir.fitbody.core.Health;

public class CalorieCalculatorActivity extends AppCompatActivity {

    EditText age, height, weight;
    RadioGroup gender;
    RadioButton male, female;
    Spinner spinner;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calculator);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Calorie Calculator");

        age = (EditText) findViewById(R.id.ageEditText);
        height = (EditText) findViewById(R.id.heightEditText);
        weight = (EditText) findViewById(R.id.weightEditText);

        gender = (RadioGroup) findViewById(R.id.genderRadioGroup);
        male = (RadioButton) findViewById(R.id.maleRadioButton);
        female = (RadioButton) findViewById(R.id.femaleRadioButton);
        spinner = (Spinner) findViewById(R.id.spinner);
        calculate = (Button) findViewById(R.id.calorie_button);


    }


    public void onCalorieCalClick(View view) {

        String ageText = age.getText().toString();
        String genderText = "";
        double heightNum;
        double weightNum;


        if (ageText.equals("")) {
            Toast.makeText(this, "Enter your age.", Toast.LENGTH_SHORT).show();
        } else {

            int ageNum = Integer.valueOf(ageText);

            if (ageNum < 18) {
                Toast.makeText(this, "You must be over 18.", Toast.LENGTH_SHORT).show();
            } else {
                String heightText = height.getText().toString();
                String weightText = weight.getText().toString();


                if (heightText.equals("")) {
                    Toast.makeText(this, "Please enter your height.", Toast.LENGTH_SHORT).show();
                } else {
                    heightNum = Double.valueOf(heightText);

                    if (weightText.equals("")) {
                        Toast.makeText(this, "Please enter your weight.", Toast.LENGTH_SHORT).show();
                    } else {
                        weightNum = Double.valueOf(weightText);

                        int selectedId = gender.getCheckedRadioButtonId();

                        if (selectedId == -1) {
                            Toast.makeText(this, "Please select gender.", Toast.LENGTH_SHORT).show();
                        } else {

                            if (selectedId == male.getId()) {
                                genderText = "M";
                            } else if (selectedId == female.getId()) {
                                genderText = "F";
                            }

                            int selectedSpinnerIndex = spinner.getSelectedItemPosition();
                            String[] spinnerValues = getResources().getStringArray(R.array.spinner_values);
                            double activity = Double.valueOf(spinnerValues[selectedSpinnerIndex]);

                            Health health = new Health();
                            int result = health.calculateCalorie(genderText, ageNum, heightNum, weightNum, activity);
                            Toast.makeText(this, "You have to loose: " + result + " calories", Toast.LENGTH_SHORT).show();


                        }
                    }


                }
            }
        }

    }
}






