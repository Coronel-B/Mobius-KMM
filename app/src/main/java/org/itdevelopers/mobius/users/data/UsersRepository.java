package org.itdevelopers.mobius.users.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

import org.itdevelopers.mobius.R;
import org.itdevelopers.mobius.users.data.cloud.ICloudUsersDataSource;
import org.itdevelopers.mobius.users.domain.entities.User;

/**
 * 6/1/2019
 *
 * PRO: Implementa concretamente al repositorio de usuarios
 */
public class UsersRepository implements IUsersRepository {

//    Relación de composición: fuente de dato especificada en el diagrama de clases
    private final ICloudUsersDataSource userService;

    private final Context context;

    private static UsersRepository INSTANCE = null;

    /**
     * @param userService: fuente de datos de usuarios remota
     * @param context: instancia del contexto p/revisar la conexión a internet
     */
    @SuppressLint("RestrictedApi")
    public UsersRepository(ICloudUsersDataSource userService, Context context) {
        this.userService = Preconditions.checkNotNull(userService);
        this.context = Preconditions.checkNotNull(context);
    }

    public static UsersRepository getInstance(
            @NonNull ICloudUsersDataSource userService,
            @NonNull Context controllerContext) {
        if (INSTANCE == null) {
            INSTANCE = new UsersRepository(userService, controllerContext);
        }
        return INSTANCE;
    }

    /**
     * Lógica de este método:
     * 1. Comprobar si existe conexión a la red
     * 2. Invocar al método de guardado del usuario de la fuente remota
     * 3. Procesar resultados
     *      a. Positivo: Invocar a addUser() e informar al interactor
     *      b. Negativo: Informar al interactor del error de:
     *              . Email confirmado
     *              . Nombre de usuario en uso
     *              . Otro
     * @param user: usuario a guardar
     * @param callback: el reporte de usuarios desde la fuente de datos remota requiere una callback
     */
    @SuppressLint("RestrictedApi")
    @Override
    public void saveUser(@NonNull User user, final OnSaveUserListener callback) {
        if (!isNetworkAvailable()) {
            callback.onError(context.getString(R.string.error_network));
            return;
        }

        Preconditions.checkNotNull(user, "user no puede ser null");

        userService.addUser(
                user,
                new ICloudUsersDataSource.UserServiceCallback() {
                    @Override
                    public void onAddUserFinished(String message) {
                        callback.onSuccess(message);
                    }

                    @Override
                    public void onAddUserError(String error) {
                        callback.onError(error);
                    }

                    @Override
                    public void onAddUserErrorAccountEmail(String errorAccountEmail) {
                        callback.onErrorAccountEmail(errorAccountEmail);
                    }

                    @Override
                    public void onAddUserErrorName(String errorUserName) {
                        callback.onErrorUserName(errorUserName);
                    }
                }
        );


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

}
