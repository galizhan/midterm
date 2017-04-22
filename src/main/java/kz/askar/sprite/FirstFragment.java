package kz.askar.sprite;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    FragmentTransaction fragmentTransaction;
    TextView highScore;

    SharedPreferences spf;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, null);
        highScore = (TextView) v.findViewById(R.id.textViewHighScore);
//        highScore.setText(((MainActivity)getActivity()).highScore);
        spf = v.getContext().getSharedPreferences("new", Context.MODE_PRIVATE);
        float time = (float) spf.getLong("record",0)/ (1000) %60;
        highScore.setText(""+ time +" seconds");
        (v.findViewById(R.id.buttonStart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,new SecondFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return v;
    }

}
