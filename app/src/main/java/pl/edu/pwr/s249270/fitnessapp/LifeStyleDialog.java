package pl.edu.pwr.s249270.fitnessapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Arrays;

public class LifeStyleDialog extends AppCompatDialogFragment {
        private DialogListener listener;
        private int selected = -1;



    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String[] lifeStyles = {"leżący lub siedzący tryb życia, brak aktywności fizycznej",
        "praca siedząca, aktywność fizyczna na niskim poziomie", "praca nie fizyczna, trening 2 razy w tygodniu",
                " lekka praca fizyczna, trening 3-4 razy w tygodniu","praca fizyczna, trening 5 razy w tygodniu",
                "ciężka praca fizyczna, codzienny trening"};


       builder.setTitle("Select your lifestyle: ")
               .setSingleChoiceItems(lifeStyles, selected, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

               selected = which;


           }
       })
            .setPositiveButton("Save", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

               listener.applySelectedLifestyle(selected);
           }
       });



        return builder.create();
    }

    public interface DialogListener{
        void applySelectedLifestyle(int item);
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
