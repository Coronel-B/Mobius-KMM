package org.techdev.mobius.addedituserprofile.presentation;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * 9/2/2018
 *
 * PRO: Lanza un diálogo p/seleccionar la fecha de cumpleaños
 * PRE: Extender de DialogFragment
 * Fuente: SM #4 p17
 */
public class BirthdatePickerDialog extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    public void setCallback(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        return new DatePickerDialog(
                getContext(),
                listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
    }

}
