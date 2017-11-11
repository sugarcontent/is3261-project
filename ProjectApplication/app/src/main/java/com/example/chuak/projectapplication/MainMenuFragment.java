package com.example.chuak.projectapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends Fragment {

    CarouselView carouselView;

    int[] sampleImages = {R.drawable.tutorial1, R.drawable.tutorial2,
            R.drawable.tutorial3, R.drawable.tutorial4, R.drawable.tutorial5};

    public MainMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        Button tutorial = view.findViewById(R.id.button_tutorial);
        Button quiz = view.findViewById(R.id.button_quizzes);
        Button notes = view.findViewById(R.id.button_notes);

        final NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        // set side navigation button active

        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TutorialFragment fragment = new TutorialFragment();
                FragmentManager manager = getFragmentManager();

                navigationView.getMenu().getItem(1).setChecked(true);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                transaction.replace(R.id.frameLayout, fragment).commit();
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizMenuFragment fragment = new QuizMenuFragment();
                FragmentManager manager = getFragmentManager();

                navigationView.getMenu().getItem(2).setChecked(true);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                transaction.replace(R.id.frameLayout, fragment).commit();
            }
        });

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesFragment fragment = new NotesFragment();
                FragmentManager manager = getFragmentManager();

                navigationView.getMenu().getItem(3).setChecked(true);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                transaction.replace(R.id.frameLayout, fragment).commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

}
