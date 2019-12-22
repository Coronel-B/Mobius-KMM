package org.itdevelopers.mobius.di;

import android.content.Context;
import androidx.annotation.NonNull;

import org.itdevelopers.mobius.addedituseraccount.data.CacheUserAccountStore;
import org.itdevelopers.mobius.addedituserprofile.data.CacheUserProfileStore;
import org.itdevelopers.mobius.users.data.UsersRepository;
import org.itdevelopers.mobius.users.data.cloud.CloudUsersDataSource;
import org.itdevelopers.mobius.users.domain.usecases.SaveUser;

/**
 * PRO: Genera dominio y datos, es decir, crea las fuentes de datos, el repositorio y el interactor.
 * OBS: Eliminando la responsabilidad de saber como operar con los repositorios de recursos.
 */
public class DependencyProvider {

    private static UsersRepository provideUsersRepository(@NonNull Context context) {
        return UsersRepository.getInstance(CloudUsersDataSource.getInstance(), context);
    }

    /**
     * PRO: Provee el caso de uso p/guardar usuarios
     */
    public static SaveUser provideSaveUser(@NonNull Context context) {
        return new SaveUser(provideUsersRepository(context));
    }

    /**
     * OBS: Al usar getInstance no uso el operador new al retornar la instancia
     */
    public static CacheUserProfileStore provideCacheUserProfileStore() {
        return CacheUserProfileStore.getInstance();
    }

    public static CacheUserAccountStore provideCacheUserAccountStore() {
        return CacheUserAccountStore.getInstance();
    }



}
