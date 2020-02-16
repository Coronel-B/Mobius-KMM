package org.techdev.mobius.addedituserprofile.presentation;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import org.techdev.mobius.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


/**
 * 9/2/2019
 *
 * PRO: Lanza un diálogo p/reconocer al id del DNI
 * PRE: La escucha tiene que implementarse en un fragmento
 * OBS: Como se requiere el reconocimiento del usuario para proceder, una simple acción es presentada
 * mediante un botón.
 * Fuente: https://material.io/design/components/dialogs.html#actions
 */
public class AcknowledgeDniIdDialog extends DialogFragment {

    /**
     * PRO: Asigna una imagen al dialogo
     * OBS: Usar getActivity().getLayoutInflater() al lanzarlo el diálogo desde un fragmento.
     * Fuente:
     *  . https://stackoverflow.com/a/8900918/5279996
     *  . https://stackoverflow.com/a/22655641/5279996
     *  . https://stackoverflow.com/q/6276501/5279996
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

//        LayoutInflater inflater = getActivity().getLayoutInflater();
        LayoutInflater inflater = LayoutInflater.from(getContext());

        final View dialogView = inflater.inflate(R.layout.alert_dialog_aknowledge_dni_id, null);

        builder
                .setTitle(R.string.title_acknowledge_dni_id_dialog)
                .setMessage(R.string.message_acknowledge_dni_id_dialog)
                .setView(dialogView)
                .setNeutralButton(
                        android.R.string.ok,
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
