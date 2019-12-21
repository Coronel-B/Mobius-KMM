package org.itdevelopers.mobius.users.data.cloud;

import org.itdevelopers.mobius.users.domain.entities.User;

/**
 * 6/1/2019
 *
 * Representación de dependencia e/el interactor del sign up y el controlador de la API
 *
 * PRO: Estandariza las operaciones estándares en la nube sobre los usuarios
 * OBS: El único método que realiza una petición al servidor es addUser.
 */
public interface ICloudUsersDataSource {

    /**
     * PRO: Realiza la petición al servidor p/agregar al usuario
     */
    void addUser(User user, UserServiceCallback callback);

    /**
     * PRO: Callback para reportar resultados e/la fuente y el repositorio
     */
    interface UserServiceCallback {
        void onAddUserFinished(String message);
        void onAddUserError(String error);
        void onAddUserErrorAccountEmail(String errorAccountEmail);
        void onAddUserErrorName(String errorUserName);
    }

}
