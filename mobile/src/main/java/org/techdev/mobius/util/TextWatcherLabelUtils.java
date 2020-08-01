package org.techdev.mobius.util;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

/**
 * PRO: Resetea el error de una etiqueta flotante
 * OBS: Clase de utilidad
 */
public class TextWatcherLabelUtils implements TextWatcher {

    private TextInputLayout floatingLabel;

    public TextWatcherLabelUtils(TextInputLayout floatingLabel) {
        this.floatingLabel = floatingLabel;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    /**
     * PRO: Resetea el error de la etiqueta flotante y limpia el enfoque
     */
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        floatingLabel.setError(null);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
