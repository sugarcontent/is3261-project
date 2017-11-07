package com.example.chuak.projectapplication;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public TutorialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tutorial, container, false);
        // Inflate the layout for this fragment

        ImageButton btn1 = view.findViewById(R.id.tutorial1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction(view.getResources()
                        .getString(R.string.tutorialkey1), 1);
            }
        });

        ImageButton btn2 = view.findViewById(R.id.tutorial2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction(view.getResources()
                        .getString(R.string.tutorialkey2), 2);
            }
        });

        ImageButton btn3 = view.findViewById(R.id.tutorial3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction(view.getResources()
                        .getString(R.string.tutorialkey3), 3);
            }
        });

        ImageButton btn4 = view.findViewById(R.id.tutorial4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction(view.getResources()
                        .getString(R.string.tutorialkey4), 4);
            }
        });

        ImageButton btn5 = view.findViewById(R.id.tutorial5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction(view.getResources()
                        .getString(R.string.tutorialkey5), 5);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String youtubeID, int videoNum);
    }

}
