package velimir.fitbody;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import velimir.fitbody.core.Health;

public class BodyTypeActivity extends AppCompatActivity {

    EditText bust, waist, hip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_type);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Body Type");

        bust = (EditText) findViewById(R.id.editTextBust);
        waist = (EditText) findViewById(R.id.editTextWaist);
        hip = (EditText) findViewById(R.id.editTextHip);

    }


    public void onBodyTypeCalClick(View view){

        if(bust.getText().toString().equals("") || waist.getText().toString().equals("") || hip.getText().toString().equals("")){
            Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show();
        } else {

            double bustCm = Double.valueOf(bust.getText().toString());
            double waistCm = Double.valueOf(waist.getText().toString());
            double hipCm = Double.valueOf(hip.getText().toString());

            Health health = new Health();
            String result = health.calculateBodyType(bustCm, waistCm, hipCm);

            Toast.makeText(this, "Your body type is: " +result, Toast.LENGTH_SHORT).show();


        }

    }


}
