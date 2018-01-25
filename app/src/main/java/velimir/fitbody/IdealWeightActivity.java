package velimir.fitbody;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import velimir.fitbody.core.Health;

public class IdealWeightActivity extends AppCompatActivity {

    EditText ageText, heightText;
    RadioGroup genderRadioGroup;
    RadioButton maleButton, femaleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal_weight);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Ideal Weight");

        ageText = (EditText) findViewById(R.id.age_editText);
        heightText = (EditText) findViewById(R.id.height_editText);
        genderRadioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        maleButton = (RadioButton) findViewById(R.id.male_radioButton);
        femaleButton = (RadioButton) findViewById(R.id.female_radioButton);


    }

    public void onCalculateButtonClick(View view) {

        String age = ageText.getText().toString();

        if (age.equals("")) {
            Toast.makeText(this, "Please enter your age.", Toast.LENGTH_SHORT).show();
        } else {
            int ageNum = Integer.valueOf(age);

            if (ageNum < 18) {
                Toast.makeText(this, "You must be over 18.", Toast.LENGTH_SHORT).show();
            } else {
                if (heightText.getText().toString().equals("")) {
                    Toast.makeText(this, "Please enter your height.", Toast.LENGTH_SHORT).show();
                } else {
                    String gender = "";

                    int selectedId = genderRadioGroup.getCheckedRadioButtonId();
                    if (selectedId == -1) {
                        Toast.makeText(this, "Choose your gender.", Toast.LENGTH_SHORT).show();

                    } else {
                        if (selectedId == maleButton.getId()) {
                            gender = "M";
                        } else if (selectedId == femaleButton.getId()) {
                            gender = "F";
                        }
                    }
                    double height = Double.valueOf(heightText.getText().toString());

                    if (!gender.equals("")) {
                        Health health = new Health();
                        double result = health.calculateIdealWeight(gender, height);
                        Toast.makeText(this, "Your ideal weight is: " +(int)result+ " kg", Toast.LENGTH_SHORT).show();

                    }
                }
            }


        }
    }


}
