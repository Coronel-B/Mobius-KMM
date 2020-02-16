package org.techdev.mobius.users.data;

import org.jetbrains.annotations.NotNull;
import org.techdev.mobius.users.domain.entities.User;

/**
 * 5/11/2019
 * Dependencia Del Repositorio De Usuarios
 *
 * PRO: Estandariza las operaciones (CRUD) sobre usuarios.
 *
 * Las acciones o misiones que tiene el repositorio son:
 *  . Guardar usuario
 *
 * OBS: El repositorio de usuarios se basará de una única fuente de datos: el servicio REST.
 */
public interface IUsersRepository {

    /**
     * PRO: Informa al interactor los resultados del proceso de guardado de usuario
     * OBS: Los contratos de los métodos de la interfaz son los mismos que en ISaveUser
     */
    interface OnSaveUserListener {

        void onSuccess(String message);
        void onError(String error);
        void onErrorAccountEmail(String errorAccountEmail);
        void onErrorUserName(String errorUserName);

    }

    void saveUser(@NotNull User user, OnSaveUserListener callback);

}
