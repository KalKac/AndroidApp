package pl.edu.pwr.s249270.fitnessapp;

import android.app.AlertDialog;
import android.app.AppComponentFactory;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddDialog extends AppCompatDialogFragment {
    private TextView mealName;
    private TextView mealcalor;
    private DialogListener listener;




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_add_meal,null);


        builder.setView(view)
                .setTitle("Add Meal")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.Save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String MealName = mealName.getText().toString();
                        String MealKcal = mealcalor.getText().toString();
                        listener.applyText(MealName,MealKcal);

                    }
                });


        mealName = view.findViewById(R.id.MealsName);
        mealcalor = view.findViewById(R.id.Mealskcal);

        return builder.create();
    }

    public interface DialogListener{
        void applyText(String MealName, String MealKcal);
        void setUpKcal(int Kcal);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener =(DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement Example");
        }

    }


}
