package ru.arizara.equation;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SeeFragment extends Fragment {

    private static final String ARG_PATH = "PATH";

    private String path;

    public SeeFragment() {
        // Required empty public constructor
    }

    public static SeeFragment newInstance(String path) {
        SeeFragment fragment = new SeeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PATH, path);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            path = getArguments().getString(ARG_PATH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_see, container, false);
        TextView tv = (TextView) rootView.findViewById(R.id.tv_see);
        //ConstraintLayout cl = (ConstraintLayout) rootView.findViewById(R.id.fl_see);
        tv.clearComposingText();
        try{
            FileReader reader = new FileReader(path);
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                tv.append(sc.nextLine()+"\n");
            }
            reader.close();
        } catch (IOException e) {
            //создаем Snackbar
           /* Snackbar snackbar = Snackbar.make(cl, "Ошибка чтения файла", Snackbar.LENGTH_SHORT);
            // показываем Snackbar
            snackbar.show();
     */   }

        return rootView;
    }
}