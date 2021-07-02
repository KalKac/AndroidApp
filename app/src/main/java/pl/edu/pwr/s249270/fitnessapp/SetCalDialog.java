package pl.edu.pwr.s249270.fitnessapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SetCalDialog extends AppCompatDialogFragment {
        private  setUpCalories listener;
        private int KcalSetted =0;


    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_set_cal,null);

        EditText setKcal = view.findViewById(R.id.InputCalories);

        builder.setView(view)
                .setTitle("Set your Calories")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Kcal = setKcal.getText().toString();
                        KcalSetted = Integer.parseInt(Kcal);
                        listener.setUpKcal(KcalSetted);
                    }
                });



        return builder.create();
    }



    public interface setUpCalories{
        void setUpKcal(int kcal);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener =(setUpCalories) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement Example");
        }
    }
}
