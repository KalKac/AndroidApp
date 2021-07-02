package pl.edu.pwr.s249270.fitnessapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivityWindow extends AppCompatActivity implements AddDialog.DialogListener, SetCalDialog.setUpCalories {

    private ListView listView;
    private FloatingActionButton button;
    private Button saveButton;
    private Button EndDayButton;
    private Button setCalButton;
    private TextView setKcalTW;
    private ProgressBar progressBar;
    public String [] meals = new String[]{};
    final List<String> mealList= new ArrayList<String>(Arrays.asList(meals));
    private TextView KcalLeft;
    private int calOfMeal = 0;
    private int totalCalInDay = 0;
    private int SetKcal = 0;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String KCALTOTAL = "KcalTotal";
    public static final String SETKCAL = "SetKCAL";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_main);

        KcalLeft = findViewById(R.id.KcalLeft);
        listView = findViewById(R.id.ListOfMeals);
        button = findViewById(R.id.AddMeal);
        saveButton = findViewById(R.id.Save);
        setCalButton = findViewById(R.id.SetYourCalories);
        progressBar = findViewById(R.id.progressBar);
        setKcalTW = findViewById(R.id.KcalToEatLeft);
        progressBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        EndDayButton = findViewById(R.id.EndDay);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mealList);
        listView.setAdapter(arrayAdapter);

        loadData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();


            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        EndDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               deleteData();
                recreate();

            }
        });
        setCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetCalDialog();
            }
        });
    }
    public void openDialog(){
        AddDialog exampleDialog = new AddDialog();
        exampleDialog.show(getSupportFragmentManager(),"Example Meal");
    }

    @Override
    public void applyText(String MealName, String MealKcal) {
        mealList.add(MealName+ "   Kcal: " + MealKcal);
        calOfMeal = Integer.parseInt(MealKcal);
        totalCalInDay+=calOfMeal;
        KcalLeft.setText("Kcal Eated: "+ totalCalInDay);
        progressBar.setProgress(totalCalInDay);

    }


public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String savedListOfMeals = toString(mealList);
        editor.putString(TEXT,savedListOfMeals);
        editor.putInt(KCALTOTAL,totalCalInDay);
        editor.putInt(SETKCAL,SetKcal);
        editor.apply();
        Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show();
}

public void loadData(){

            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            String csvList = sharedPreferences.getString(TEXT, "");
            String[] items = csvList.split(",");
            for (int i = 0; i < items.length; i++) {
                mealList.add(items[i]);
            }
            int kcal = sharedPreferences.getInt(KCALTOTAL,0);
            KcalLeft.setText("Kcal Eated: "+ String.valueOf(kcal));
            int SetKcal = sharedPreferences.getInt(SETKCAL, 0);
            setKcalTW.setText("Kcal set: \n" + SetKcal);




}
public void deleteData(){
    Toast.makeText(this,"Day Ended", Toast.LENGTH_SHORT).show();
    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.clear().apply();
}

public String toString(List<String> mealList){

        StringBuilder meal = new StringBuilder();
        for(String str: mealList){
            meal.append(str);
            meal.append(",");
        }
        String str = meal.toString();
        return str;
}

    public void openSetCalDialog(){
        SetCalDialog setCalDialog = new SetCalDialog();
        setCalDialog.show(getSupportFragmentManager(),"set Cal");
    }

    @Override
    public void setUpKcal(int Kcal){
        setKcalTW = findViewById(R.id.KcalToEatLeft);
        progressBar.setMax(Kcal);
        SetKcal = Kcal;
        setKcalTW.setText("Kcal set: \n" + Kcal);
    }

}
