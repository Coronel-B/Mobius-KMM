package org.techdev.mobius.addedituser.presentation;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.techdev.mobius.R;
import org.techdev.mobius.addedituser.AddEditUserActivity;
import org.techdev.mobius.addedituseraccount.AddEditUserAccountActivity;
import org.techdev.mobius.addedituserprofile.AddEditUserProfileActivity;
import org.techdev.mobius.users.domain.entities.UserAccount;
import org.techdev.mobius.users.domain.entities.UserProfile;
import org.techdev.mobius.util.TextWatcherLabelUtils;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import static android.app.Activity.RESULT_OK;

/**
 * PRO: Modela a la vista para "Añadir Usuario"
 */
public class AddEditUserFragment extends Fragment implements
        AddEditUserMvp.View,
        View.OnClickListener {

    public static final String TAG = AddEditUserFragment.class.getSimpleName();

    //    Relación de composición con el presentador para invocar sus comportamientos.
    private AddEditUserMvp.Presenter presenter;

//    Instancias de los views del layout que se manipularán
    private Button addUserProfileButton, addUserAccountButton;
    private TextInputLayout addUserProfileButtonWrapper, addUserAccountButtonWrapper, userNameFieldWrapper;
    private EditText userNameField;
    private ProgressBar userProgressBar;
    private Menu userMenu;
    private UserAccountAdapter userAccountAdapter;
    private UserProfileAdapter userProfileAdapter;

    public static AddEditUserFragment newInstance() {
        return new AddEditUserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * PRO: Procesa los resultados desde el presentador de las creaciones/ediciones del perfil/cuenta
     * y la edición de una cuenta por error de email
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case AddEditUserActivity.REQUEST_ADD_USER_ACCOUNT:
                if (resultCode == RESULT_OK)
                    presenter.manageUserAccountAdditionResult();
                break;
            case AddEditUserActivity.REQUEST_ADD_USER_PROFILE:
                if (resultCode == RESULT_OK)
                    presenter.manageUserProfileAdditionResult();
                break;
            case AddEditUserActivity.REQUEST_EDIT_USER_ACCOUNT:
                if (resultCode == RESULT_OK)
                    presenter.manageUserAccountEditionResult();
                break;
            case AddEditUserActivity.REQUEST_EDIT_USER_PROFILE:
                if (resultCode == RESULT_OK)
                    presenter.manageUserProfileEditionResult();
                break;
            case AddEditUserActivity.REQUEST_EDIT_USER_ACCOUNT_ERROR_EMAIL:
                if (resultCode == RESULT_OK)
                    presenter.manageUserAccountEmailErrorEditionResult(userNameField.getText().toString());
                break;
        }
    }

    /**
     * PRO: Configura la vista de UI
     * OBS: Se asocian las escuchas p/los eventos correspondientes a las reacciones del presentador
     * del usuario
     *  . Añadir/Editar perfil/cuenta
     *  . Guardar usuario
     *  . El tipeo en el campo de texto nombre de usuario
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_edit_user, container, false);

//        Perfil
        addUserProfileButton = root.findViewById(R.id.add_profile_button);
        addUserProfileButtonWrapper = root.findViewById(R.id.add_profile_button_wrapper);
        RecyclerView userProfileList = root.findViewById(R.id.user_profile_list);
        DividerItemDecoration profileDecoration = new DividerItemDecoration(
                userProfileList.getContext(),
                DividerItemDecoration.VERTICAL
        );
        userProfileAdapter = new UserProfileAdapter(
                getContext(),
                new UserProfile(),
                new UserProfileAdapter.UserProfileListener() {
                    @Override
                    public void onProfileClick(UserProfile userProfile) {
                        presenter.editUserProfile(userProfile);
                    }

                    @Override
                    public void onRemoveProfileClick() {
                        presenter.deleteUserProfile();
                    }
                }
        );
        userProfileList.setAdapter(userProfileAdapter);
        userProfileList.addItemDecoration(profileDecoration);

        //        Cuenta
        addUserAccountButton = root.findViewById(R.id.add_account_button);
        addUserAccountButtonWrapper = root.findViewById(R.id.add_account_button_wrapper);
        RecyclerView userAccountList = root.findViewById(R.id.user_account_list);
        DividerItemDecoration accountDecoration = new DividerItemDecoration(
                userAccountList.getContext(),
                DividerItemDecoration.VERTICAL
        );
        userAccountAdapter = new UserAccountAdapter(
                new UserAccount(),
                new UserAccountAdapter.UserAccountListener() {
                    @Override
                    public void onAccountClick(UserAccount userAccount) {
                        presenter.editUserAccount(userAccount);
                    }

                    @Override
                    public void onRemoveAccountClick() {
                        presenter.deleteUserAccount();
                    }
                },
                getResources()
        );
        userAccountList.setAdapter(userAccountAdapter);
        userAccountList.addItemDecoration(accountDecoration);

//        Nombre de usuario
        userNameField = root.findViewById(R.id.text_field_user_name);
        userNameFieldWrapper = root.findViewById(R.id.float_label_user_name);

//        Barra de progreso
        userProgressBar = root.findViewById(R.id.user_progress_bar);

//        Eventos
        addUserAccountButton.setOnClickListener(this);
        addUserProfileButton.setOnClickListener(this);
        userNameField.addTextChangedListener(new TextWatcherLabelUtils(userNameFieldWrapper));

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_account_button:
                presenter.addNewUserAccount();
                break;
            case R.id.add_profile_button:
                presenter.addNewUserProfile();
                break;
        }
    }

    /**
     * PRO: Carga perfil/cuenta de usuario
     * OBS: Manteniendo actualiza la lista del perfil y cuenta.
     */
    @Override
    public void onResume() {
        super.onResume();
        presenter.loadUserAccount();
        presenter.loadUserProfile();
    }

    /**
     * OBS: P/habilitar o deshabilar los ítems de menu es necesario inicializar un campo con @param menu
     */
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        this.userMenu = menu;
    }

    /**
     * PRO: Guarda usuario
     * OBS: Recibiendo el evento del ícono de "check" que se tiene en la Toolbar de la actividad.
     * La reacción será la ejecución de saveUser() por parte del presentador.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save_user) {
            presenter.saveUser(userNameField.getText().toString());
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * PRO: Agrega el efecto de focus hacia el campo y muestra el teclado
     * Fuente:
     *  . https://stackoverflow.com/a/42455033/5279996
     *  . https://stackoverflow.com/a/27750335/5279996
     * @param view
     */
    private void requestViewFocus(View view) {
        if (view.requestFocus()) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(userNameField, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void showUserAccount(UserAccount userAccount) {
        userAccountAdapter.replaceData(userAccount);
        addUserAccountButtonWrapper.setErrorEnabled(false);
    }

    @Override
    public void showUserProfile(UserProfile userProfile) {
        userProfileAdapter.replaceData(userProfile);
        addUserProfileButtonWrapper.setErrorEnabled(false);
    }

    @Override
    public void showUserAccountError() {
        addUserAccountButtonWrapper.setErrorEnabled(true);
        addUserAccountButtonWrapper.setError(getString(R.string.empty_user_account));
    }

    @Override
    public void showUserProfileError() {
        addUserProfileButtonWrapper.setErrorEnabled(true);
        addUserProfileButtonWrapper.setError(getString(R.string.empty_user_profile));
    }

    @Override
    public void showUserNameError(String userNameError) {
        userNameFieldWrapper.setErrorEnabled(true);
        userNameFieldWrapper.setError(userNameError);

        requestViewFocus(userNameField);
    }



    @Override
    public void showAddUserProfileButton(boolean show) {
        addUserProfileButton.setEnabled(show);
    }

    @Override
    public void showAddUserAccountButton(boolean show) {
        addUserAccountButton.setEnabled(show);
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        userProgressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * PRE: userMenu tiene que estar inicializado dentro del método sobrescrito onPrepareOptionsMenu
     * @param enable
     */
    @Override
    public void enableMenuItem(boolean enable) {
        userMenu.findItem(R.id.action_save_user).setEnabled(enable);
    }

    /**
     * PRO: En lugar de limitarse a cerrar la actividad, se crea un intent incluyendo un mensaje
     * ingresado en el paquete de extras.
     * @param message: mensaje
     */
    @Override
    public void showLoginScreen(@NonNull String message) {
        Intent requestIntent = new Intent();
        requestIntent.putExtra(AddEditUserActivity.EXTRA_USER_SAVING_MESSAGE, message);
        getActivity().setResult(RESULT_OK, requestIntent);
        getActivity().finish();
    }

    @Override
    public void showAddUserAccountScreen() {
        Intent requestIntent = new Intent(getActivity(), AddEditUserAccountActivity.class);
        startActivityForResult(requestIntent, AddEditUserActivity.REQUEST_ADD_USER_ACCOUNT);
    }

    @Override
    public void showAddUserProfileScreen() {
        Intent requestIntent = new Intent(getActivity(), AddEditUserProfileActivity.class);
        startActivityForResult(requestIntent, AddEditUserActivity.REQUEST_ADD_USER_PROFILE);
    }

    /**
     * PRO: Muestra la actividad de creación pero esta vez se pasa la cuenta a editar
     * OBS: Como paso un objeto lo casteo a Parcelable. TODO Sino saco el casteo y en la entidad implemento a Parcelable
     * @param userAccount: cuenta de usuario creada a editar
     */
    @Override
    public void showEditUserAccountScreen(@NonNull UserAccount userAccount) {
        Intent requestIntent = new Intent(getActivity(), AddEditUserAccountActivity.class);
        requestIntent.putExtra(AddEditUserActivity.EXTRA_USER_ACCOUNT, (Parcelable) userAccount);
        startActivityForResult(requestIntent, AddEditUserActivity.REQUEST_EDIT_USER_ACCOUNT);
    }

    /**
     * PRO: Muestra la actividad de creación pero esta vez se pasa el perfil a editar
     * OBS: Como paso un objeto lo casteo a Parcelable. TODO Sino saco el casteo y en la entidad implemento a Parcelable
     * @param userProfile: perfil de usuario creado a editar
     */
    @Override
    public void showEditUserProfileScreen(@NonNull UserProfile userProfile) {
        Intent requestIntent = new Intent(getActivity(), AddEditUserProfileActivity.class);
        requestIntent.putExtra(AddEditUserActivity.EXTRA_USER_PROFILE, (Parcelable) userProfile);
        startActivityForResult(requestIntent, AddEditUserActivity.REQUEST_EDIT_USER_PROFILE);
    }

    /**
     * PRO: Muestra la actividad de creación
     * @param invalidEmail: error de email
     */
    @Override
    public void showEditUserAccountEmailErrorScreen(String invalidEmail) {
        Intent requestIntent = new Intent(getActivity(), AddEditUserAccountActivity.class);
        requestIntent.putExtra(AddEditUserActivity.EXTRA_USER_ACCOUNT_ERROR_EMAIL, invalidEmail);
        startActivityForResult(requestIntent, AddEditUserActivity.REQUEST_EDIT_USER_ACCOUNT_ERROR_EMAIL);
    }

    @Override
    public void showSaveError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setPresenter(AddEditUserMvp.Presenter presenter) {
        this.presenter = Preconditions.checkNotNull(presenter);
    }

}
