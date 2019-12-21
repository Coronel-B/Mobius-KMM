package org.itdevelopers.mobius.addedituseraccount.presentation;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Preconditions;
import android.text.TextUtils;
import android.util.Patterns;

import org.itdevelopers.mobius.R;
import org.itdevelopers.mobius.addedituseraccount.data.ICacheUserAccountStore;
import org.itdevelopers.mobius.users.domain.entities.UserAccount;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 21/1/2019
 *
 * OBS: Presentador de Añadir/Editar cuenta de usuario
 */
public class AddEditUserAccountPresenter implements AddEditUserAccountMvp.Presenter {

    public static final String TAG = AddEditUserAccountPresenter.class.getSimpleName();


    /*
    Campos de relación:

     . Cuenta de usuario a editar si fuera el caso
     . La vista
     . La caché de cuenta de usuario
     . La fuente de datos de recursos de Android. De utilidad p/obtener algunos textos.
     */
    private UserAccount userAccount;
    private final AddEditUserAccountMvp.View view;
    private final ICacheUserAccountStore iCacheUserAccountStore;
    private final Resources resources;

//    Tomo las referencias de los campos
    @SuppressLint("RestrictedApi")
    public AddEditUserAccountPresenter(@Nullable UserAccount userAccount,
                                       @NonNull AddEditUserAccountMvp.View view,
                                       @NonNull ICacheUserAccountStore iCacheUserAccountStore,
                                       @NonNull Resources resources) {
        this.userAccount = userAccount;
        this.view = Preconditions.checkNotNull(view);
        this.iCacheUserAccountStore = Preconditions.checkNotNull(iCacheUserAccountStore);
        this.resources = Preconditions.checkNotNull(resources);
    }

    /**
     * PRO: Carga cuenta de usuario a editar
     * OBS: En el caso de que el futuro usuario haya optado por una edición, se carga el email y
     * asigna el resultado a la vista.
     */
    @Override
    public void populateUserAccount() {
        if (isEdition()) {
            iCacheUserAccountStore.getUserAccount(
                    new ICacheUserAccountStore.GetUserAccountCallback() {
                        @Override
                        public void onUserAccountLoaded(UserAccount userAccount) {
                            showAccount(userAccount);
                        }
                    }
            );
        }
    }

    /**
     * PRO: Valida la edición
     * OBS: Comprobando si this.userAccount no es nulo
     * @return boolean
     */
    private boolean isEdition() {
        return userAccount != null;
    }

    /**
     * PRO: Simplifica la muestra del email de cuenta (campo que se autocompleta)
     * OBS: Las contraseñas son tipeadas nuevamente por el usuario.
     */
    private void showAccount(UserAccount userAccount) {
        view.showEmail(userAccount.getEmail());
    }

    /**
     * OBS: Guardar la cuenta de usuario significa lo siguiente:
     *
     *  1. Validar que el campo del email siga el formato normativo en su sintaxis
     *  2. Validar que el campo de la contraseña sea segura
     *  3. Validar que el campo de la confirmación de la contraseña sea correcto
     *  4. Crear un objeto UserAccount
     *  5. Guardar el objeto en la caché
     *  6. Volver a la actividad de creación del usuario
     */
    @Override
    public void saveUserAccount(UserAccount account, String confirmationPassword) {

        /*
        Valida correo:
            Comprobando si está vacío y determinando si sigue el patrón estándar
         */
        if (TextUtils.isEmpty(account.getEmail()) ||
                !Patterns.EMAIL_ADDRESS.matcher(account.getEmail()).matches()) {
            view.showEmailError(true, resources.getString(R.string.email_format_error));
            return;
        } else
            view.showEmailError(false, "");

        /*
        Valida contraseña:
            Determinando si el nivel de seguridad es al menos medianamente seguro.
         */
        if (!isFairlySafePassword(account.getPassword())) {
            view.showPasswordError(true);
            return;
        } else
            view.showPasswordError(false);

        /*
        Valida confirmación de contraseña:
            Comprobando si la contraseña es igual a la confirmación de contraseña.
         */
        if (!account.getPassword().equals(confirmationPassword)) {
            view.showPasswordConfirmationError(true);
            return;
        } else
            view.showPasswordConfirmationError(false);

//        Determino el nivel de seguridad de la contraseña
        String passwordSecurity = resources.getString(R.string.fairly_safe_pw);
        if (isStrongPassword(account.getPassword()))
            passwordSecurity = resources.getString(R.string.strong_pw);

        UserAccount userAccount = new UserAccount(account.getEmail(), account.getPassword(), passwordSecurity);
        iCacheUserAccountStore.saveUserAccount(userAccount);
        view.showAddEditUserScreen();
    }

    /**
     * PRO: Describe si la contraseña es fuerte
     * Fuente: https://stackoverflow.com/a/36574313/5279996
     * OBS: La pw tiene que tener letras, números y al menos 1 caracter especial
     */
    private static boolean isStrongPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * PRO: Describe si la contraseña es medianamente segura
     * OBS: Contiene letras minúsculas y números
     * Fuente: https://stackoverflow.com/a/11533559/5279996
     */
    private static boolean isFairlySafePassword(final String password) {
        String n = ".*[0-9].*";
        String a = ".*[a-z].*";
        return password.matches(n) && password.matches(a) &&
                password.length() >= 8;
    }

}
