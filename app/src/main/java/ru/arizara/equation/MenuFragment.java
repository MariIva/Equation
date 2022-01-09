package ru.arizara.equation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {

    Button btn_calc, btn_see;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_menu, container, false);

        btn_calc = (Button) rootView.findViewById(R.id.btn_calc);
        btn_see = (Button) rootView.findViewById(R.id.btn_see);

        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // получение методов интерфейса реализованных в активности
                OnClickMenuFragmentListener listener = (OnClickMenuFragmentListener) getActivity();
                // запуск метода активности
                listener.onClickCalc();
            }
        });

        btn_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // получение методов интерфейса реализованных в активности
                OnClickMenuFragmentListener listener = (OnClickMenuFragmentListener) getActivity();
                // запуск метода активности
                listener.onClickSee();
            }
        });

        return rootView;
    }


    // интервейс для реализации в активности
    public interface OnClickMenuFragmentListener {
        void onClickCalc();
        void onClickSee();
    }
}