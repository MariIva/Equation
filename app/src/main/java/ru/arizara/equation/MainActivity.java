package ru.arizara.equation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
                          implements MenuFragment.OnClickMenuFragmentListener,
                                     CalcFragment.OnClickCalcFragmentListener{

    // код запроса разрешения на запись
    public static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 2;
    // теги для фрагментов
    final static String TAG_LIST = "TAG_LIST";
    final static String TAG_CALC= "TAG_CALC";
    final static String TAG_SEE= "TAG_SEE";

    // путь к файлу
    String path ;

    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // путь к файлу
        path = getFilesDir().toString()+"/equation";
        // получение размешения на чтение и запись
        permission();



        // создание фрагментов
        menuFragment = new MenuFragment();

        // установка фрагмента с кнопками
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_list, menuFragment)
                .commit();


    }


    public void permission()  {
        int permissionStatus_write = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionStatus_write == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,     // эта активность
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},     // список размешений
                    PERMISSION_WRITE_EXTERNAL_STORAGE);   // код запроса разрешения
        }
    }

    @Override
    public void onClickCalc() {
        CalcFragment fragment = new CalcFragment();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // замена фрагмента списка на настройки
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_list, fragment, TAG_CALC)
                    .addToBackStack(null)
                    .commit();
        }
        // если ориентация горизонтальная
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // установка  фрагмента в правую часть экрана
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_content, fragment, TAG_CALC)
                    .commit();
        }
    }

    @Override
    public void onClickSee() {
        SeeFragment fragment = SeeFragment.newInstance(path);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // замена фрагмента списка на настройки
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_list, fragment, TAG_SEE)
                    .addToBackStack(null)
                    .commit();
        }
        // если ориентация горизонтальная
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // установка  фрагмента в правую часть экрана
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_content, fragment, TAG_SEE)
                    .commit();
        }

    }

    @Override
    public void onClickSave(String str) throws IOException {
        int permissionStatus_write = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionStatus_write == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,     // эта активность
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},     // список размешений
                    PERMISSION_WRITE_EXTERNAL_STORAGE);   // код запроса разрешения
        }else{
            FileWriter fileWriter = new FileWriter(path, true);
            fileWriter.write(str+"\n");
            fileWriter.close();
        }
        // если ориентация вертикальная
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // удаление фрагмента с активности
            getFragmentManager().popBackStack();
            FragmentManager fm = getSupportFragmentManager();
            if( fm.getBackStackEntryCount()>0) {
                fm.popBackStack();
            }
        }
    }

}