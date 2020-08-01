package org.techdev.mobius.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import org.techdev.mobius.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

/**
 * 16/1/2019
 *
 * PRO: Lanza un diálogo p/descartar cambios
 * OBS: El diálogo tiene un botón negativo y positivo
 */
public class DiscardChangesDialog extends DialogFragment {

    /**
     * PRO: Comunica el diálogo con la actividad para descartar cambios
     * OBS: Implementación de una escucha personalizada
     */
    public interface DiscardChangesDialogListener {

        /**
         * PRO: Cancela una operación (fill in)
         */
        void onDialogPositiveClick(DialogFragment dialogFragment);

        /**
         * PRO: Continua en la operación (fill in)
         */
        void onDialogNegativeClick(DialogFragment dialogFragment);
    }

    private DiscardChangesDialogListener discardDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.title_discard_changes_dialog)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        discardDialogListener.onDialogPositiveClick(DiscardChangesDialog.this);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        discardDialogListener.onDialogNegativeClick(DiscardChangesDialog.this);
                    }
                });

        return builder.create();
    }

    /**
     * PRO: Toma la instancia de la actividad p/verificar si está usando la interfaz de comunicación
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            discardDialogListener = (DiscardChangesDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    context.toString() + " tiene que implementar DiscardDialogListener");
        }
    }
}
