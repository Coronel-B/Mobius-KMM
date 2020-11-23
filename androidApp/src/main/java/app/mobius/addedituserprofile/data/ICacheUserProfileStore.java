package app.mobius.addedituserprofile.data;

import app.mobius.users.domain.entities.UserProfile;

import androidx.annotation.NonNull;

/**
 * 10/1/2019
 *
 * Caché de perfil de usuario
 *
 * PRO:
 *  . Obtener perfil de usuario
 *  . Obtener perfil de usuario con callback
 *  . Guardar perfil de usuario
 *  . Borrar perfil de usuario
 * OBS: Escribo las acciones anteriores como métodos.
 * Pongo 2 interfaces load y get. Porque quizá en un futuro se tengan que cargar en la preview varios perfiles
 */
public interface ICacheUserProfileStore {

    /**
     * PRO: Abstrae la carga del objeto UserProfile en la preview de la creación del usuario
     */
    interface LoadUserProfileCallback {
        /**
         * PRO: Carga al objeto UserProfile en la preview de la creación del usuario
         * OBS: Este método se inicializa en AddEditUserPresenter
         */
        void onUserProfileLoaded(UserProfile userProfile);
    }

    interface GetUserProfileCallback {
        void onUserProfileLoaded(UserProfile userProfile);
    }

    /**
     * PRO: Obtiene perfil de usuario
     * @return UserProfile: perfil de usuario
     */
    UserProfile getUserProfile();

    void getUserProfile(@NonNull GetUserProfileCallback callback);

    /**
     * PRO: Obtiene el perfil de usuario
     * OBS: En la preview de la screen del registro de usuarios aparecerán solo algunos atributos
     * del objeto UserProfile
     * @param callback: llamada para la carga del objeto UserProfile
     */
    void getUserProfile(@NonNull LoadUserProfileCallback callback);

    /**
     * PRO: Guarda el perfil de usuario
     * @param userProfile: perfil a guardar
     */
    void saveUserProfile(@NonNull UserProfile userProfile);

    /**
     * PRO: Borra el perfil de usuario limpiando la caché
     */
    void deleteUserProfile();

}
