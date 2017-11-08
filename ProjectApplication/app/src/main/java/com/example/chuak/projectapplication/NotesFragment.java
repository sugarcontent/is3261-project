package com.example.chuak.projectapplication;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.example.chuak.projectapplication.R.id.btn_note_1;
import static com.example.chuak.projectapplication.R.id.btn_note_2;
import static com.example.chuak.projectapplication.R.id.btn_note_3;
import static com.example.chuak.projectapplication.R.id.btn_note_4;
import static com.example.chuak.projectapplication.R.id.btn_note_5;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment {

    private Button goToNotes1;
    private Button goToNotes2;
    private Button goToNotes3;
    private Button goToNotes4;
    private Button goToNotes5;

    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        goToNotes1 = view.findViewById(btn_note_1);
        goToNotes2 = view.findViewById(btn_note_2);
        goToNotes3 = view.findViewById(btn_note_3);
        goToNotes4 = view.findViewById(btn_note_4);
        goToNotes5 = view.findViewById(btn_note_5);

        String tutId;



        goToNotes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), NotesViewingActivity.class);
                i.putExtra("tutId", "1");
                startActivity(i);
            }
        });

        goToNotes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), NotesViewingActivity.class);
                i.putExtra("tutId", "2");
                startActivity(i);
            }
        });

        goToNotes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), NotesViewingActivity.class);
                i.putExtra("tutId", "3");
                startActivity(i);
            }
        });

        goToNotes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), NotesViewingActivity.class);
                i.putExtra("tutId", "4");
                startActivity(i);
            }
        });

        goToNotes5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), NotesViewingActivity.class);
                i.putExtra("tutId", "5");
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return view;

    }

}
