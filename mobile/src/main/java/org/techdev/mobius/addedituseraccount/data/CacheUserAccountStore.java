package org.techdev.mobius.addedituseraccount.data;

import org.techdev.mobius.users.domain.entities.UserAccount;

import androidx.annotation.NonNull;

/**
 * 8/1/2019
 *
 * Singleton con la implementación de la interfaz
 */
public class CacheUserAccountStore implements ICacheUserAccountStore {

//    Variable para la cuenta
    private UserAccount cachedUserAccount;

    private static final CacheUserAccountStore ourInstance = new CacheUserAccountStore();

    public static CacheUserAccountStore getInstance() {
        return ourInstance;
    }

    private CacheUserAccountStore() {
    }

    @Override
    public UserAccount getUserAccount() {
        return getValue();
    }

    /**
     * PRO: Describe a la cuenta
     * OBS: La cuenta se encuentra almacenada en la caché
     * @return UserAccount: cuenta de usuario
     */
    @NonNull
    private UserAccount getValue() {
        return cachedUserAccount;
    }

    @Override
    public void getUserAccount(@NonNull final GetUserAccountCallback callback) {
        callback.onUserAccountLoaded(getUserAccount());
    }

    @Override
    public void getUserAccount(@NonNull final LoadUserAccountCallback callback) {
        callback.onUserAccountLoaded(getUserAccount());
    }

    @Override
    public void saveUserAccount(@NonNull UserAccount userAccount) {
        cachedUserAccount = userAccount;
    }

    /**
     * OBS: El uso de este método se aplica al salir de la creación/edición del usuario, pues
     * se necesita la fuente de datos de la cuenta limpia p/operar con un nuevo usuario.
     */
    @Override
    public void deleteUserAccount() {
        cachedUserAccount = null;
    }
}
