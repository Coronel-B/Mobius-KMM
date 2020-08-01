package org.techdev.mobius.addedituserprofile.data;

import org.techdev.mobius.users.domain.entities.UserProfile;

import androidx.annotation.NonNull;

/**
 * 10/1/2019
 *
 * Singleton con la implementación de la interfaz
 */
public class CacheUserProfileStore implements ICacheUserProfileStore {

//    Variable p/el perfil
    private UserProfile cachedUserProfile;

    private static final CacheUserProfileStore ourInstance = new CacheUserProfileStore();

    public static CacheUserProfileStore getInstance() {
        return ourInstance;
    }

    private CacheUserProfileStore() {
    }

    @Override
    public UserProfile getUserProfile() {
        return getValue();
    }

    /**
     * PRO: Describe al perfil
     * OBS: El perfil se encuentra almacenado en la caché
     * @return UserProfile: perfil de usuario
     */
    @NonNull
    private UserProfile getValue() {
        return cachedUserProfile;
    }

    @Override
    public void getUserProfile(@NonNull final GetUserProfileCallback callback) {
        callback.onUserProfileLoaded(getUserProfile());
    }

    @Override
    public void getUserProfile(@NonNull final LoadUserProfileCallback callback) {
        callback.onUserProfileLoaded(getUserProfile());
    }


    @Override
    public void saveUserProfile(@NonNull UserProfile userProfile) {
        cachedUserProfile = userProfile;
    }

    /**
     * OBS: El uso de este método se aplica al salir de la creación/edición del usuario, pues
     * se necesita la fuente de datos del perfil limpia p/operar con un nuevo usuario.
     */
    @Override
    public void deleteUserProfile() {
        cachedUserProfile = null;
    }
}
