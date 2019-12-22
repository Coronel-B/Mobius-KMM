package org.itdevelopers.mobius.addedituser.presentation;

import androidx.annotation.NonNull;

import org.itdevelopers.mobius.users.domain.entities.UserAccount;
import org.itdevelopers.mobius.users.domain.entities.UserProfile;

/**
 * 7/1/2019
 *
 * PRO: Representa las microinteracciones MVP
 * OBS: Del boceto se pueden extraer todos los comportamientos a programar de la vista y el presentador.
 */
public interface AddEditUserMvp {

    /**
     * PRO: La vista relacionada al presentador debe:
     *
     *  . Mostrar perfil/cuenta de usuario
     *  . Mostrar nombre de usuario
     *  . Mostrar error de ausencia de perfil/cuenta
     *  . Mostrar error de email de cuenta confirmada
     *  . Mostrar error de nombre de usuario
     *  . Mostrar/ocultar botón p/agregar perfil/cuenta
     *  . Mostrar/ocultar progreso
     *  . Habilitar/deshabilitar el ítem de menú p/guardar a un usario
     *  . Redireccionar a la actividad del Login
     *  . Redireccionar a la actividad p/crear/editar el/la perfil/cuenta
     *  . Mostrar error de creación de usuario fallida
     *  . Relacionar el presentador a la vista
     */
    interface View {

        /**
         * PRO: Muestra perfil de usuario
         */
        void showUserProfile(UserProfile userProfile);

        /**
         * PRO: Muestra cuenta de usuario
         */
        void showUserAccount(UserAccount userAccount);

        /**
         * PRO: Muestra error de ausencia de perfil
         * OBS: Avisando al usuario que no ha creado el perfil
         */
        void showUserProfileError();

        /**
         * PRO: Muestra error de ausencia de cuenta
         * OBS: Avisando al usuario que no ha creado la cuenta
         */
        void showUserAccountError();

        /**
         * PRO: Muestra error de nombre de usuario
         * OBS: El argumento puede ser por nombre de usuario usado o formato incorrecto
         */
        void showUserNameError(String userNameError);

        /**
         * PRO: Muestra/oculta el botón p/agregar el perfil de usuario
         * PRE: Se 'muestra' luego de borrar y se 'oculta' luego de agregar
         * OBS: Si tuviera una lista de perfiles NO habría que deshabilitar el botón de agregar perfil.
         */
        void showAddUserProfileButton(boolean show);

        /**
         * PRO: Muestra/oculta el botón p/agregar la cuenta de usuario
         * PRE: Se 'muestra' luego de borrar y se 'oculta' luego de agregar
         * OBS: Si tuviera una lista de perfiles NO habría que deshabilitar el botón de agregar cuenta.
         */
        void showAddUserAccountButton(boolean show);

        /**
         * PRO: Muestra/oculta progreso
         * OBS: App Products #2
         */
        void showLoadingIndicator(boolean show);

        /**
         * PRO: Habilita/deshabilita el ítem de menú para guardar a un usuario
         * OBS: Es útil mientras carga la petición
         */
        void enableMenuItem(boolean enable);

        /**
         * PRO: Redirecciona a la actividad del Login y muestra un Toast indicando una creación exitosa.
         */
        void showLoginScreen(String message);

        /**
         * PRO: Redirecciona a la actividad p/crear el perfil del usuario
         * OBS: Se inicia la nueva actividad con la petición: REQUEST_ADD_USER_PROFILE
         */
        void showAddUserProfileScreen();

        /**
         * PRO: Redirecciona a la actividad p/crear la cuenta del usuario
         * OBS: Se inicia la nueva actividad con la petición: REQUEST_ADD_USER_ACCOUNT
         */
        void showAddUserAccountScreen();

        /**
         * PRO: Redirecciona a la actividad p/editar el perfil del usuario
         * OBS: Se inicia la nueva actividad con la petición: REQUEST_EDIT_USER_PROFILE
         * @param userProfile: perfil de usuario creado a editar
         */
        void showEditUserProfileScreen(@NonNull UserProfile userProfile);

        /**
         * PRO: Redirecciona a la actividad p/editar la cuenta del usuario
         * OBS: Se inicia la nueva actividad con la petición: REQUEST_EDIT_USER_ACCOUNT
         * @param userAccount: cuenta de usuario creada a editar
         */
        void showEditUserAccountScreen(@NonNull UserAccount userAccount);

        /**
         * PRO: Redirecciona a la screen de edición de cuenta y enfoca el error en el email
         * @param emailError: error de email
         */
        void showEditUserAccountEmailErrorScreen(String emailError);

        /**
         * PRO: Muestra error de creación de usuario fallida.
         * OBS: Importante, guardar el error que va a pasarse como argumento en los recursos.
         */
        void showSaveError(String error);

        /**
         * PRO: Relaciona el presentador a la vista
         */
        void setPresenter(Presenter presenter);

    }

    /**
     * PRO: El presentador debe:
     *
     *  . Guardar usuario
     *  . Añadir/editar/eliminar perfil/cuenta de usuario
     *  . Manejar resultados ante una creación/edición de un perfil/cuenta exitosa
     *  . Cargar el perfil/cuenta del usuario
     *  . Restaurar el estado del fragmento
     */
    interface Presenter {

        /**
         * PRO: Guarda al usuario
         * OBS: Recibe c/u/de los atributos provenientes de las entradas sencillas del usuario (nombre)
         * @param userName: nombre de usuario
         */
        void saveUser(String userName);

        /**
         * PRO: Añade perfil de usuario.
         * OBS: Ordenando a la vista abrir la actividad de creación del perfil.
         */
        void addNewUserProfile();

        /**
         * PRO: Añade cuenta de usuario.
         * OBS: Ordenando a la vista abrir la actividad de creación de la cuenta.
         */
        void addNewUserAccount();

        /**
         * PRO: Edita perfil de usuario
         * OBS: Ordenando a la vista abrir la actividad de edición del perfil.
         * @param userProfile: perfil a editar
         */
        void editUserProfile(UserProfile userProfile);

        /**
         * PRO: Edita cuenta de usuario
         * OBS: Ordenando a la vista abrir la actividad de edición de la cuenta.
         * @param userAccount: cuenta a editar
         */
        void editUserAccount(UserAccount userAccount);

        /**
         * PRO: Edita cuenta de usuario por error de email
         * OBS: Ordenando a la vista abrir la actividad de edición de la cuenta
         */
//        void editUserAccountEmailError(String errorEmail);

        /**
         * PRO: Elimina perfil de usuario
         * OBS: Eliminando de la caché del perfil de usuario, el elemento existente
         */
        void deleteUserProfile();

        /**
         * PRO: Elimina cuenta de usuario
         * OBS: Eliminando el elemento existente de la caché de la cuenta de usuario
         */
        void deleteUserAccount();

        /**
         * PRO: Maneja resultados ante una creación de un perfil exitoso
         */
        void manageUserProfileAdditionResult();

        /**
         * PRO: Maneja resultados ante una creación de una cuenta exitosa
         */
        void manageUserAccountAdditionResult();

        /**
         * PRO: Maneja resultados ante una edición de un perfil exitoso
         */
        void manageUserProfileEditionResult();

        /**
         * PRO: Maneja resultados ante una edición de una cuenta exitosa
         */
        void manageUserAccountEditionResult();

        /**
         * PRO: Maneja resultados ante una edición de una cuenta por error de email
         * @param userName: nombre de usuario
         */
        void manageUserAccountEmailErrorEditionResult(String userName);

        /**
         * PRO: Carga perfil de usuario
         * OBS: Obteniendo el perfil existente de la caché y ordenando a la vista mostrarlo
         */
        void loadUserProfile();

        /**
         * PRO: Carga cuenta de usuario
         * OBS: Obteniendo la cuenta existente de la caché y ordenando a la vista mostrarla
         */
        void loadUserAccount();

    }
}
