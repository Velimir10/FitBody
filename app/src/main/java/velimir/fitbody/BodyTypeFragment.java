package velimir.fitbody;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import velimir.fitbody.R;
import velimir.fitbody.core.Health;
import velimir.fitbody.core.onFragmentSendMessegeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class BodyTypeFragment extends Fragment implements View.OnClickListener {


    private Button calculate;
    private EditText bust, waist, hip;

    public BodyTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_body_type, container, false);
        calculate = view.findViewById(R.id.button_bodyType);
        calculate.setOnClickListener(this);

        bust = view.findViewById(R.id.editTextBust);
        waist =  view.findViewById(R.id.editTextWaist);
        hip =  view.findViewById(R.id.editTextHip);


        return view;
    }

    @Override
    public void onClick(View view) {

        if(bust.getText().toString().equals("") || waist.getText().toString().equals("") || hip.getText().toString().equals("")){

            if(listener != null){
                // Send messege to parent activity
                CharSequence messege = "Please enter all values";
                listener.onFragmentSendMsg(messege);
            }

        } else {

            double bustCm = Double.valueOf(bust.getText().toString());
            double waistCm = Double.valueOf(waist.getText().toString());
            double hipCm = Double.valueOf(hip.getText().toString());

            Health health = new Health();
            String result = health.calculateBodyType(bustCm, waistCm, hipCm);

            if(listener != null){
                //Send messege to parent activity
                CharSequence messege = "Your body type is: "+ result;
                listener.onFragmentSendMsg(messege);
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
