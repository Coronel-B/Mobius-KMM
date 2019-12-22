package org.itdevelopers.mobius.addedituser.presentation;

import android.annotation.SuppressLint;
import android.content.res.Resources;

import org.itdevelopers.mobius.R;
import org.itdevelopers.mobius.addedituseraccount.data.ICacheUserAccountStore;
import org.itdevelopers.mobius.addedituserprofile.data.ICacheUserProfileStore;
import org.itdevelopers.mobius.users.domain.entities.User;
import org.itdevelopers.mobius.users.domain.entities.UserAccount;
import org.itdevelopers.mobius.users.domain.entities.UserProfile;
import org.itdevelopers.mobius.users.domain.usecases.ISaveUser;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

/**
 * 14/1/2019
 *
 * PRO: Implementa al contrato del presentador
 */
public class AddEditUserPresenter implements AddEditUserMvp.Presenter {

    public static final String TAG = AddEditUserPresenter.class.getSimpleName();

    /*
    Campos de relación acorde a la sección de arquitectura:

     . La vista
     . El caso de uso para guardar al usuario
     . Caché de perfil de usuario
     . Caché de cuenta de usuario
     */
    private AddEditUserMvp.View view;
    private ISaveUser iSaveUser;
    private ICacheUserAccountStore iCacheUserAccountStore;
    private ICacheUserProfileStore iCacheUserProfileStore;

    private final Resources resources;

    @SuppressLint("RestrictedApi")
    public AddEditUserPresenter(@NonNull AddEditUserMvp.View view,
                                @NonNull ISaveUser iSaveUser,
                                @NonNull ICacheUserProfileStore iCacheUserProfileStore,
                                @NonNull ICacheUserAccountStore iCacheUserAccountStore,
                                @NonNull Resources resources) {
        this.view = Preconditions.checkNotNull(view);
        this.iSaveUser = Preconditions.checkNotNull(iSaveUser);
        this.iCacheUserProfileStore =  Preconditions.checkNotNull(iCacheUserProfileStore);
        this.iCacheUserAccountStore = Preconditions.checkNotNull(iCacheUserAccountStore);
        this.resources = resources;
    }

    /**
     * Guardar Usuario
     *
     * 1. Crear una nueva instancia User con los parámetros de entrada
     * 2. Validar la integridad de los parámetros que lo requieran y lanzar un mensaje de error sino
     *    se cumplen las validaciones
     * 3. Ejecutar el caso de uso p/guardar la entidad
     *      a. Si sale bien, se limpia la caché del perfil y de la cuenta; y se redirige al usuario
     *         hacia el Login de usuarios
     *      b. De lo contrario se muestra un error.
     * OBS: Un usuario futuro es quien tiene la intención de registrarse.
     */
    @Override
    public void saveUser(String userName) {
        User user = new User(
                iCacheUserProfileStore.getUserProfile(),
                iCacheUserAccountStore.getUserAccount(),
                userName
        );

        boolean invalidFutureUserInput = false;

        if (user.emptyUserProfile()) {
            view.showLoadingIndicator(false);
            view.enableMenuItem(true);
            view.showUserProfileError();
            invalidFutureUserInput = true;
        }

        if (user.emptyUserAccount()) {
            view.showLoadingIndicator(false);
            view.enableMenuItem(true);
            view.showUserAccountError();
            invalidFutureUserInput = true;
        }

        if (user.invalidUserName()) {
            view.showLoadingIndicator(false);
            view.enableMenuItem(true);
            view.showUserNameError(resources.getString(R.string.empty_user_name));
            invalidFutureUserInput = true;
        }


        if (!invalidFutureUserInput) {
            view.showLoadingIndicator(true);
            view.enableMenuItem(false);
            iSaveUser.execute(
                    user,
                    new ISaveUser.ExecuteCallback() {

//                        Controlador de petición y guardado exitoso
                        @Override
                        public void onSuccess(String message) {
                            iCacheUserProfileStore.deleteUserProfile();
                            iCacheUserAccountStore.deleteUserAccount();
                            view.showLoadingIndicator(false);
                            view.enableMenuItem(false);
                            view.showLoginScreen(message);
                        }

//                        Controlador de petición no exitosa
                        @Override
                        public void onError(String error) {
                            view.showLoadingIndicator(false);
                            view.enableMenuItem(true);
                            view.showSaveError(error);
                        }

//                        Controlador de petición exitosa con error de email
                        @Override
                        public void onErrorEmail(String emailError) {
                            view.showLoadingIndicator(false);
                            view.enableMenuItem(true);
                            view.showEditUserAccountEmailErrorScreen(emailError);
                        }

//                        Controlador de petición exitosa con error de nombre de usuario
                        @Override
                        public void onErrorUserName(String errorUserName) {
                            view.showLoadingIndicator(false);
                            view.enableMenuItem(true);
                            view.showUserNameError(errorUserName);
                        }
                    }
            );
        }

    }

    @Override
    public void addNewUserProfile() {
        view.showAddUserProfileScreen();
    }

    @Override
    public void addNewUserAccount() {
        view.showAddUserAccountScreen();
    }

    @Override
    public void editUserProfile(UserProfile userProfile) {
        view.showEditUserProfileScreen(userProfile);
    }

    @Override
    public void editUserAccount(UserAccount userAccount) {
        view.showEditUserAccountScreen(userAccount);
    }

    /**
     * Manejar Resultados de Creación/Edición
     *
     * Los resultados de crear y editar no se ven reflejados con datos resultantes de las actividades
     * AddEditUserProfileActivity y AddEditUserAccountActivity, por lo que los métodos quedarán
     * vacíos.
     * Pero si se requiere procesar alguna acción adicional, quedan a disposición.
     */
    @Override
    public void manageUserProfileAdditionResult() {

    }
    @Override
    public void manageUserAccountAdditionResult() {

    }
    @Override
    public void manageUserProfileEditionResult() {

    }
    @Override
    public void manageUserAccountEditionResult() {

    }

    /**
     * PRO: Vuelve a intentar guardar el usuario
     * OBS: Luego de confirmar la edición por un error de email.
     * @param userName: nombre de usuario
     */
    @Override
    public void manageUserAccountEmailErrorEditionResult(String userName) {
        saveUser(userName);
    }

    /**
     * PRO: Obtiene de la caché el perfil, si es que existe, y lo muestra con view.showUserProfile()
     * OBS: El perfil puede estar vacío dependiendo si se borro la caché antes de invocar a este método.
     */
    @Override
    public void loadUserProfile() {
        iCacheUserProfileStore.getUserProfile(
                new ICacheUserProfileStore.LoadUserProfileCallback() {

                    /**
                     * PRO: Carga el perfil y habilita el botón de 'Agregar' acorde a si este existe o no
                     * OBS: Si sigo el flujo de ejecución de quien invocó a este método, el objeto
                     * userProfile viene desde la caché.
                     */
                    @Override
                    public void onUserProfileLoaded(UserProfile userProfile) {
//                        Actualizar perfil de usuario
                        view.showUserProfile(userProfile);

//                        TODO: Por transitividad de iCacheUserProfileStore.getUserProfile()
                        if (userProfile == null)
                            view.showAddUserProfileButton(true);
                        else
                            view.showAddUserProfileButton(false);
                    }
                }
        );
    }

    /**
     * PRO: Obtiene de la caché la cuenta, si es que existe, y la muestra con view.showUserAccount()
     * OBS: La cuenta puede estar vacía dependiendo si se borro la caché antes de invocar a este método.
     */
    @Override
    public void loadUserAccount() {
        iCacheUserAccountStore.getUserAccount(
                new ICacheUserAccountStore.LoadUserAccountCallback() {

                    /**
                     * PRO: Carga la cuenta y habilita el botón de 'Agregar' acorde a si esta existe o no
                     * OBS: Si sigo el flujo de ejecución de quien invocó a este método, el objeto
                     * userProfile viene desde la caché.
                     */
                    @Override
                    public void onUserAccountLoaded(UserAccount userAccount) {
                        view.showUserAccount(userAccount);

//                        TODO: Por transitividad de iCacheUserAccountStore.getUserAccount()
                        if (userAccount == null)
                            view.showAddUserAccountButton(true);
                        else
                            view.showAddUserAccountButton(false);
                    }
                }
        );
    }

    @Override
    public void deleteUserProfile() {
//        Eliminar perfil existente
        iCacheUserProfileStore.deleteUserProfile();

//        Cargar perfil vacío
        loadUserProfile();
    }

    @Override
    public void deleteUserAccount() {
//        Eliminar cuenta existente
        iCacheUserAccountStore.deleteUserAccount();

//        Cargar cuenta vacía
        loadUserAccount();
    }

}
