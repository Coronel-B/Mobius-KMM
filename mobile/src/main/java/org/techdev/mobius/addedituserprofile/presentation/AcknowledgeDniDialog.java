package org.techdev.mobius.addedituserprofile.presentation;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import org.techdev.mobius.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

/**
 * 6/2/2019
 *
 * PRO: Lanza un diálogo p/reconocer al DNI
 * PRE: La escucha tiene que implementarse en un fragmento
 * OBS: Como se requiere el reconocimiento del usuario para proceder, una simple acción es presentada
 * mediante un botón.
 * Fuente: https://material.io/design/components/dialogs.html#actions
 */
public class AcknowledgeDniDialog extends DialogFragment {

    /**
     * Fuente:
     * https://stackoverflow.com/questions/13733304/callback-to-a-fragment-from-a-dialogfragment
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle(R.string.title_acknowledge_dni_dialog)
                .setMessage(R.string.message_acknowledge_dni_dialog)
                .setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            /**
                             * PRO: Comunica al diálogo con el fragmento p/reconocer al id del DNI
                             * OBS: Implementación de una escucha personalizada entre el fragmento y el diálogo
                             */
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                getTargetFragment().onActivityResult(
                                        getTargetRequestCode(),
                                        Activity.RESULT_OK,
                                        getActivity().getIntent());
                            }
                        });
        return builder.create();
    }

}
