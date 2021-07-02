package pl.edu.pwr.s249270.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainOptionWindow extends AppCompatActivity implements LifeStyleDialog.DialogListener {


    private int calcCalories;
    private int gender = 0;
    private String calToDisplay;
    private double lifeStyle = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_option);

        EditText age = findViewById(R.id.InputAge);
        EditText height = findViewById(R.id.HeightID);
        EditText weight = findViewById(R.id.WeightID);


        Switch genderSwitch = findViewById(R.id.switch1);
        Button saveYourOptions = findViewById(R.id.SaveOptions);
        Button setLifeStyle = findViewById(R.id.LifeStyle);
        TextView setKcal = findViewById(R.id.SetCalories);
        TextView calBulkorCat = findViewById(R.id.calToBulkOrCut);



        saveYourOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int AGE = Integer.parseInt(age.getText().toString());
                int WEIGHT = Integer.parseInt(weight.getText().toString());
                int HEIGHT = Integer.parseInt(height.getText().toString());
                int GENDER = gender;

                int calToBulk;
                int calToCut;

                System.out.println(gender);
                CalcuateKcal(AGE,WEIGHT,HEIGHT,GENDER);
                calToDisplay = String.valueOf(calcCalories);
                setKcal.setText("Your maintance kcal is: " + calToDisplay);

                calToBulk = calcCalories + 200;
                calToCut = calcCalories  -200;
                calBulkorCat.setText("If you want bulk: " +calToBulk  + "\nIf you want cut:   "+ calToCut);
            }
        });

        genderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked){

                    gender = 0;
                }
                else {
                    gender = 1;

                }
            }
        });
        setLifeStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLifeStyleDialog();
            }
        });
    }



    public void CalcuateKcal(int age, int weight, int height, int gender){


        if(gender == 0){
            calcCalories = (int) (66 + (13.7 * weight) + (5 * height) - (6.76 *age));
            System.out.println(calcCalories);
            calcCalories = (int) (calcCalories * lifeStyle);
            System.out.println(calcCalories);


        }
        else if(gender == 1) {
            calcCalories = (int) (655 + (9.6 * weight) + (1.8 * height) - (4.76 *age));
            calcCalories = (int) (calcCalories * lifeStyle);
        }
    }

    public void OpenLifeStyleDialog(){
        LifeStyleDialog lifeStyleDialog = new LifeStyleDialog();
        lifeStyleDialog.show(getSupportFragmentManager(),"Life Style");

    }


    @Override
    public void applySelectedLifestyle(int item) {
        switch (item){
            case 0:
                lifeStyle = 1;
                break;
            case 1:
                lifeStyle = 1.2;
                break;
            case 2:
                lifeStyle = 1.4;
                break;
            case 3:
                lifeStyle = 1.6;
                break;
            case 4:
                lifeStyle = 1.8;
                break;
            case 5:
                lifeStyle = 2;
                break;
        }
    }
}