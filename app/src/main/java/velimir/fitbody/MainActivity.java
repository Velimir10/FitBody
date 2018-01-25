package velimir.fitbody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void onIdealWeightClick(View view){
         Intent intent = new Intent(this, IdealWeightActivity.class );
         startActivity(intent);
    }

    public void onCalorieCalculatorClick(View view){
        Intent intent = new Intent(this, CalorieCalculatorActivity.class );
        startActivity(intent);
    }

    public void onBodyTypeClick(View view){
        Intent intent = new Intent(this, BodyTypeActivity.class );
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.info_item:

                CharSequence text = "Fit Body\nVelimir Atanasovski\n2nd app";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(this,text,duration);
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);

        }


    }
}
