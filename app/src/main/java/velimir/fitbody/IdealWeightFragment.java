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
import android.widget.TextView;
import android.widget.Toast;

import velimir.fitbody.core.Health;
import velimir.fitbody.core.onFragmentSendMessegeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class IdealWeightFragment extends Fragment implements View.OnClickListener{


    private Button calculate_button;
    private EditText ageText, heightText;
    private RadioGroup genderRadioGroup;
    private RadioButton maleButton, femaleButton;

    public IdealWeightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ideal_weight, container, false);
        calculate_button = (Button) view.findViewById(R.id.calculate_button);
        calculate_button.setOnClickListener(this);

        ageText = (EditText) view.findViewById(R.id.age_editText);
        heightText = (EditText) view.findViewById(R.id.height_editText);
        genderRadioGroup = (RadioGroup) view.findViewById(R.id.genderRadioGroup);
        maleButton = (RadioButton) view.findViewById(R.id.male_radioButton);
        femaleButton = (RadioButton)view. findViewById(R.id.female_radioButton);


        return view;
    }

    @Override
    public void onClick(View view) {

        String age = ageText.getText().toString();

        if (age.equals("")) {

            if(listener != null){
                //Send messege to parent activity
                CharSequence messege = "Please enter your age.";
                listener.onFragmentSendMsg(messege);
            }

        } else {
            int ageNum = Integer.valueOf(age);

            if (ageNum < 18) {

                if(listener != null){
                    //Send messege to parent activity
                    CharSequence messege = "You must be over 18.";
                    listener.onFragmentSendMsg(messege);
                }

            } else {
                if (heightText.getText().toString().equals("")) {

                    if(listener != null){
                        //Send messege to parent activity
                        CharSequence messege = "Please enter your height.";
                        listener.onFragmentSendMsg(messege);
                    }

                } else {
                    String gender = "";

                    int selectedId = genderRadioGroup.getCheckedRadioButtonId();
                    if (selectedId == -1) {

                        if(listener != null){
                            //Send messege to parent activity
                            CharSequence messege = "Choose your gender.";
                            listener.onFragmentSendMsg(messege);
                        }



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
                        if(listener != null){
                            //Send messege to parent activity
                            CharSequence messege = "Your ideal weight is: "+(int)result + " kg";
                            listener.onFragmentSendMsg(messege);
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
