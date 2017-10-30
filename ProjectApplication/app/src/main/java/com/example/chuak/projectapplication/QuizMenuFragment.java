package com.example.chuak.projectapplication;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizMenuFragment extends Fragment {

    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;

    public QuizMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quizzes, container, false);

        option1 = (Button) view.findViewById(R.id.option1);
        option2 = (Button) view.findViewById(R.id.option2);
        option3 = (Button) view.findViewById(R.id.option3);
        option4 = (Button) view.findViewById(R.id.option4);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Quiz1Activity.class);
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return view;

    }

}
