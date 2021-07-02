package pl.edu.pwr.s249270.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button StartMainMenu = findViewById(R.id.StartMainMenu);
        Button OptionsMainMenu = findViewById(R.id.Options);
        Button ExitMainMenu = findViewById(R.id.ExitMainMenu);


        Intent mainActivityWindow = new Intent(this, MainActivityWindow.class);
        Intent mainOptionWindow = new Intent(this, MainOptionWindow.class);



        StartMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainActivityWindow);
            }
        });

        OptionsMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(mainOptionWindow);

            }
        });

        ExitMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}