package velimir.fitbody;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;


public class CalculatorActivity extends AppCompatActivity implements velimir.fitbody.core.onFragmentSendMessegeListener {

    private ViewPager viewPager;
    private CalculatorPageAdapter calculatorPageAdapter;
    private TabLayout tabLayout;

    public static class CalculatorPageAdapter extends FragmentPagerAdapter {

        static final int NUM_ITEMS = 3;
        static final String[] TAB_TITLES = new String[]{"Ideal Weight", "Calorie", "Body Type"};

        public CalculatorPageAdapter(FragmentManager fm) {
            super(fm);
        }

        // returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new IdealWeightFragment();
                case 1:
                    return new CaloriCalculatorFragment();
                case 2:
                    return new BodyTypeFragment();
                default:
                    return null;
            }
        }

        // returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return TAB_TITLES[position];
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        tabLayout = findViewById(R.id.tab_layout);


        viewPager = findViewById(R.id.view_pager);
        calculatorPageAdapter = new CalculatorPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(calculatorPageAdapter);


        int calculator_num = getIntent().getIntExtra("CALCULATOR_KEY", 0);
        viewPager.setCurrentItem(calculator_num);
        tabLayout.setupWithViewPager(viewPager);


    }


    @Override
    public void onFragmentSendMsg(CharSequence messege) {
        Toast.makeText(this, messege, Toast.LENGTH_SHORT).show();
    }

    private void changeActionBarText(String text) {
        getSupportActionBar().setTitle(text);
    }


}
