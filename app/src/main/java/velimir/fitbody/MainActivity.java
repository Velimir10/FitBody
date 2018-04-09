package velimir.fitbody;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements velimir.fitbody.core.onFragmentSendMessegeListener {

    private View fragmentContainer;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainer = findViewById(R.id.fragmentContainer);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerListView = (ListView) findViewById(R.id.drawer_listview);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();


        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                if (fragmentContainer != null) {
                    switch (i) {
                        case 0:
                            Fragment idealWeightFragment = new IdealWeightFragment();
                            replaceFragment(idealWeightFragment, "ideal_weight");
                            break;

                        case 1:
                            Fragment calorieCalculatorFragment = new CaloriCalculatorFragment();
                            replaceFragment(calorieCalculatorFragment, "calorie_calculator");
                            break;
                        case 2:
                            Fragment bodyTypeFragmentt = new BodyTypeFragment();
                            replaceFragment(bodyTypeFragmentt, "body_type");
                            break;
                    }
                } else {
                    switch (i) {
                        case 0:
                            displayActivity(getApplicationContext(),"CALCULATOR_KEY",0);

                            break;
                        case 1:
                            displayActivity(getApplicationContext(),"CALCULATOR_KEY",1);

                            break;
                        case 2:
                            displayActivity(getApplicationContext(),"CALCULATOR_KEY",2);

                            break;
                    }

                }
            }
        });


        if (fragmentContainer != null) {
            if (savedInstanceState == null) {

                displayInitialFragment();

            }
        }

    }

    public void displayActivity(Context context, String putExtra, int value){

        Intent intent = new Intent(context, CalculatorActivity.class);
        intent.putExtra(putExtra, value);
        startActivity(intent);

    }

    private void displayInitialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment idealWeightFragment = new IdealWeightFragment();
        fragmentTransaction.add(fragmentContainer.getId(), idealWeightFragment);
        fragmentTransaction.commit();
    }


    public void onIdealWeightClick(View view) {

        if (fragmentContainer != null) {

            Fragment idealWeightFragment = new IdealWeightFragment();
            replaceFragment(idealWeightFragment, "ideal_weight");
        } else {
            displayActivity(getApplicationContext(),"CALCULATOR_KEY",0);
        }

    }

    public void onCalorieCalculatorClick(View view) {

        if (fragmentContainer != null) {

            Fragment calorieCalculatorFragment = new CaloriCalculatorFragment();
            replaceFragment(calorieCalculatorFragment, "calori_calculator");

        } else {
            displayActivity(getApplicationContext(),"CALCULATOR_KEY",1);
        }

    }

    public void onBodyTypeClick(View view) {
        if (fragmentContainer != null) {


            Fragment bodyTypeFragmentt = new BodyTypeFragment();
            replaceFragment(bodyTypeFragmentt, "body_type");

        } else {
            displayActivity(getApplicationContext(),"CALCULATOR_KEY",2);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.info_item:

                displayInfoMessege();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    // Show messege from fragment
    @Override
    public void onFragmentSendMsg(CharSequence messege) {
        Toast.makeText(this, messege, Toast.LENGTH_SHORT).show();
    }


    private void replaceFragment(Fragment fragment, String backStackTag) {


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.addToBackStack(backStackTag);
        fragmentTransaction.replace(fragmentContainer.getId(), fragment);
        fragmentTransaction.commit();

    }

    public void displayInfoMessege() {

        CharSequence text = "Fit Body\nVelimir Atanasovski\n2nd app";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, text, duration);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}
