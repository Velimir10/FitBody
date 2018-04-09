package velimir.fitbody;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import velimir.fitbody.core.Health;
import velimir.fitbody.core.onFragmentSendMessegeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaloriCalculatorFragment extends Fragment implements View.OnClickListener {

    private Button calorie_button;
    private EditText age, height, weight;
    private RadioGroup gender;
    private RadioButton male, female;
    private Spinner spinner;

    public CaloriCalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calorie_calculator, container, false);
        calorie_button = (Button) view.findViewById(R.id.calorie_button);
        calorie_button.setOnClickListener(this);

        age = (EditText) view.findViewById(R.id.ageEditText);
        height = (EditText) view.findViewById(R.id.heightEditText);
        weight = (EditText) view.findViewById(R.id.weightEditText);

        gender = (RadioGroup) view.findViewById(R.id.genderRadioGroup);
        male = (RadioButton) view.findViewById(R.id.maleRadioButton);
        female = (RadioButton) view.findViewById(R.id.femaleRadioButton);
        spinner = (Spinner) view.findViewById(R.id.spinner);

        return view;

    }

    @Override
    public void onClick(View view) {

        String ageText = age.getText().toString();
        String genderText = "";
        double heightNum;
        double weightNum;


        if (ageText.equals("")) {
            if(listener != null){
                //Send messege to parent activity
                CharSequence messege = "Enter your age. ";
                listener.onFragmentSendMsg(messege);
            }

        } else {

            int ageNum = Integer.valueOf(ageText);

            if (ageNum < 18) {
                if(listener != null){
                    //Send messege to parent activity
                    CharSequence messege = "You must be over 18.";
                    listener.onFragmentSendMsg(messege);
                }

            } else {
                String heightText = height.getText().toString();
                String weightText = weight.getText().toString();


                if (heightText.equals("")) {
                    if(listener != null){
                        //Send messege to parent activity
                        CharSequence messege = "Please enter your height.";
                        listener.onFragmentSendMsg(messege);
                    }

                } else {
                    heightNum = Double.valueOf(heightText);

                    if (weightText.equals("")) {
                        if(listener != null){
                            //Send messege to parent activity
                            CharSequence messege = "Please enter your weight.";
                            listener.onFragmentSendMsg(messege);
                        }

                    } else {
                        weightNum = Double.valueOf(weightText);

                        int selectedId = gender.getCheckedRadioButtonId();

                        if (selectedId == -1) {
                            if(listener != null){
                                //Send messege to parent activity
                                CharSequence messege = "Please select gender.";
                                listener.onFragmentSendMsg(messege);
                            }

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
                            if(listener != null){
                                //Send messege to parent activity
                                CharSequence messege = "You have to loose: " +result+ " calories";
                                listener.onFragmentSendMsg(messege);
                            }



                        }
                    }
                }
            }
        }
    }


    onFragmentSendMessegeListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (onFragmentSendMessegeListener) context;
    }


}