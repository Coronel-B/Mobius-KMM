package app.mobius.addedituseraccount.presentation;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import androidx.core.util.Preconditions;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import app.mobius.mobius.R;
import app.mobius.users.domain.entities.UserAccount;
import app.mobius.util.TextWatcherLabelUtils;

public class AddEditUserAccountFragment extends Fragment implements AddEditUserAccountMvp.View {

//    Campo de relación con el presentador
    private AddEditUserAccountMvp.Presenter presenter;

//    Referencias a widgets con los que el usuario interactua
    private EditText emailField, passwordField, passwordConfirmationField;
    private TextInputLayout emailFieldWrapper, passwordFieldWrapper, passwordConfirmationFieldWrapper;

    public AddEditUserAccountFragment() {
    }

    public static AddEditUserAccountFragment newInstance() {
        return new AddEditUserAccountFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * PRO: Configura vista de UI
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_edit_user_account, container, false);

//        Se toman las referencias de UI de los views que el usuario manipulará
        emailField = root.findViewById(R.id.text_field_email);
        emailFieldWrapper = root.findViewById(R.id.float_label_email);
        passwordField = root.findViewById(R.id.text_field_password);
        passwordFieldWrapper = root.findViewById(R.id.float_label_password);
        passwordConfirmationField = root.findViewById(R.id.text_field_confirm_password);
        passwordConfirmationFieldWrapper = root.findViewById(R.id.float_label_confirm_password);

        /*
        Eventos de UI:
         . Tipeo en 3 campos de textos
         . Action Bar
         */
        emailField.addTextChangedListener(new TextWatcherLabelUtils(emailFieldWrapper));
        passwordField.addTextChangedListener(new TextWatcherLabelUtils(emailFieldWrapper));
        passwordConfirmationField.addTextChangedListener(new TextWatcherLabelUtils(passwordConfirmationFieldWrapper));

//        Se habilita la contribución a la Action Bar por parte del fragmento
        setHasOptionsMenu(true);

        return root;
    }

    /**
     * PRO: Carga cuenta de usuario a editar
     * OBS: Cada vez que la actividad vuelva de un segundo plano
     */
    @Override
    public void onResume() {
        super.onResume();
        presenter.populateUserAccount();
    }

    /**
     * PRO: Maneja eventos de la Toolbar
     * OBS: Una de las acciones a procesar es el guardado de la cuenta.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.action_save_user_account == item.getItemId()) {
            presenter.saveUserAccount(
                    new UserAccount(
                            emailField.getText().toString(),
                            passwordField.getText().toString()
                    ),
                    passwordConfirmationField.getText().toString()
            );
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showEmailError(boolean show, String emailError) {
        if (!show) {
            emailFieldWrapper.setErrorEnabled(false);
            emailFieldWrapper.clearFocus();
        } else {
            emailFieldWrapper.setErrorEnabled(true);
            emailFieldWrapper.setError(emailError);
            emailFieldWrapper.requestFocus();
        }
    }

    @Override
    public void showPasswordError(boolean show) {
        if (!show) {
            passwordFieldWrapper.setErrorEnabled(false);
            passwordFieldWrapper.clearFocus();
        } else {
            passwordFieldWrapper.setErrorEnabled(true);
            passwordFieldWrapper.setError(getString(R.string.password_error));
            passwordFieldWrapper.requestFocus();
        }

    }

    @Override
    public void showPasswordConfirmationError(boolean show) {
        if (!show) {
            passwordConfirmationFieldWrapper.setErrorEnabled(false);
            passwordConfirmationFieldWrapper.clearFocus();
        } else {
            passwordConfirmationFieldWrapper.setErrorEnabled(true);
            passwordConfirmationFieldWrapper.setError(getString(R.string.password_confirmation_error));
            passwordConfirmationFieldWrapper.requestFocus();
        }
    }

    /**
     * PRO: Retorna a la edición de usuario
     * OBS: Seteando el código de resultado Activity.RESULT_OK p/indicar que hubo una creación exitosa
     * de la cuenta de usuario. Luego se termina la actividad
     */
    @Override
    public void showAddEditUserScreen() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void showEmail(String email) {
//        Deshabilito el error
        emailFieldWrapper.setErrorEnabled(false);
        emailField.setText(email);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setPresenter(AddEditUserAccountMvp.Presenter presenter) {
        this.presenter = Preconditions.checkNotNull(presenter);
    }
}
