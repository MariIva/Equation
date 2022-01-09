package ru.arizara.equation;

import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;


public class CalcFragment extends Fragment {

    private static final String ARG_VALUES = "PATH";

    Button btn_answer, btn_save;
    TextInputLayout te_A, te_B, te_C;
    TextView tv_answer;
    CoordinatorLayout coordinatorLayout;

    Equation equation;

    public CalcFragment() {
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
        View rootView =  inflater.inflate(R.layout.fragment_calc, container, false);

        btn_answer = (Button) rootView.findViewById(R.id.btn_answer);
        btn_save = (Button) rootView.findViewById(R.id.btn_save);
        te_A = (TextInputLayout) rootView.findViewById(R.id.te_A) ;
        te_B = (TextInputLayout) rootView.findViewById(R.id.te_B) ;
        te_C = (TextInputLayout) rootView.findViewById(R.id.te_C) ;
        tv_answer = (TextView)  rootView.findViewById(R.id.tv_answer);
        //coordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.fl_calc);

        btn_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = Integer.parseInt(te_A.getEditText().getText().toString());
                    int b = Integer.parseInt(te_B.getEditText().getText().toString());
                    int c = Integer.parseInt(te_C.getEditText().getText().toString());
                    equation = new Equation(a, b, c);
                    equation.calc();
                    tv_answer.setText(equation.getX());
                }
                catch (NumberFormatException | NullPointerException e){
                    //создаем Snackbar
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "Введите все коэффициенты", Snackbar.LENGTH_INDEFINITE);
                    // добавляем кнопку на Snackbar и описываем клик на нее
                    snackbar.setAction("OK", new View.OnClickListener (){
                        @Override
                        public void onClick(View v) {
                            // при клике закрываем Snackbar
                            snackbar.dismiss();
                        }
                    });
                    // показываем Snackbar
                    snackbar.show();
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // получение методов интерфейса реализованных в активности
                OnClickCalcFragmentListener listener = (OnClickCalcFragmentListener) getActivity();
                // запуск метода активности
                try {
                    listener.onClickSave(equation.toString());
                } catch (IOException e) {
                    //создаем Snackbar
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "Ошибка записи в файл", Snackbar.LENGTH_SHORT);
                    // показываем Snackbar
                    snackbar.show();
                }
            }
        });

        return rootView;

    }

    // интервейс для реализации в активности
    public interface OnClickCalcFragmentListener {
        void onClickSave(String str) throws IOException;
    }
}