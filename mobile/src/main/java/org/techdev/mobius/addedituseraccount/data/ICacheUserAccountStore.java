package org.techdev.mobius.addedituseraccount.data;

import org.techdev.mobius.users.domain.entities.UserAccount;

import androidx.annotation.NonNull;

/**
 * 8/1/2019
 *
 * Caché de cuenta de usuario
 *
 * PRO:
 *  . Obtener cuenta de usuario
 *  . Obtener cuenta de usuario con callback
 *  . Guardar cuenta de usuario
 *  . Borrar cuenta de usuario
 * OBS: Escribo las acciones anteriores como métodos.
 * Pongo 2 interfaces load y get. Porque quizá en un futuro tenga que cargar varias cuentas.
 */
public interface ICacheUserAccountStore {

    /**
     * PRO: Abstrae la carga del objeto UserAccount en la preview de la creación del usuario
     */
    interface LoadUserAccountCallback {
        /**
         * PRO: Carga al objeto UserAccount en la preview de la creación del usuario.
         * OBS: Este método se inicializa en AddEditUserPresenter
         * @param userAccount: cuenta de usuario
         */
        void onUserAccountLoaded(UserAccount userAccount);
    }

    /**
     * PRO: Abstrae la obtención del objeto UserAccount para la edición de la cuenta
     */
    interface GetUserAccountCallback {
        void onUserAccountLoaded(UserAccount userAccount);
    }

    /**
     * PRO: Obtiene cuenta de usuario
     * @return UserAccount: cuenta de usuario
     */
    UserAccount getUserAccount();

    void getUserAccount(@NonNull GetUserAccountCallback callback);

    /**
     * PRO: Obtiene la cuenta de usuario
     * OBS: En la preview de la screen del registro de usuarios aparecerán solo algunos atributos
     * del objeto UserProfile
     * @param callback: llamada para la carga del objeto UserAccount
     */
    void getUserAccount(@NonNull LoadUserAccountCallback callback);

    /**
     * PRO: Guarda la cuenta de usuario
     * @param userAccount: cuenta a guardar
     */
    void saveUserAccount(@NonNull UserAccount userAccount);

    /**
     * PRO: Borra la cuenta de usuario limpiando la caché
     */
    void deleteUserAccount();

}
