package org.techdev.mobius.users.domain.usecases;

import android.annotation.SuppressLint;

import org.techdev.mobius.users.data.IUsersRepository;
import org.techdev.mobius.users.domain.entities.User;

import androidx.annotation.NonNull;

import static androidx.core.util.Preconditions.checkNotNull;

/**
 * 5/11/2019
 *
 * Implementación de "Guardar usuario"
 */
public class SaveUser implements ISaveUser {

//    Relación de composición
    private final IUsersRepository usersRepository;

    @SuppressLint("RestrictedApi")
    public SaveUser(IUsersRepository usersRepository) {
        this.usersRepository = checkNotNull(usersRepository);
    }

    /**
     * PRO: Inserta un usuario sino existen errores.
     * OBS: Como el interactor representa el proceso del caso de uso del guardado de usuario, es necesario incluir la
     * lógica de la app en este método.
     * Se agregan las validaciones correspondientes p/el usuario, se lo envía al repositorio y
     * al final se retorna una salida acorde al caso reportado.
     * Con el repositorio genero una interfaz de comunicación que retorna las rtas al interactor.
     * @param user: usuario a guardar
     * @param executeCallback: instancia de la callback interna p/determinar el momento en que se termina la operación
     */
    @SuppressLint("RestrictedApi")
    @Override
    public void execute(@NonNull User user, final ExecuteCallback executeCallback) {
        checkNotNull(user, "user no puede ser null");
        usersRepository.saveUser(
                user,
//                Escucha anónima p/saveUser que maneja las respuetas del repositorio
                new IUsersRepository.OnSaveUserListener() {

                    /**
                     * PRO: Transfiere el resultado, de la creación de un usuario en el servidor remoto,
                     * a la callback que se relaciona con el presentador.
                     * @param message: mensaje de creación de usuario
                     */
                    @Override
                    public void onSuccess(String message) {
                        executeCallback.onSuccess(message);
                    }

                    /**
                     * PRO: Transfiere el resultado, de latencias o códigos de error en el server,
                     * a la callback que se relaciona con el presentador.
                     * @param error: mensaje de error
                     */
                    @Override
                    public void onError(String error) {
                        executeCallback.onError(error);
                    }

                    /**
                     * PRO: Transfiere el resultado, de que existe un email confirmado en el server,
                     * a la callback que se relaciona con el presentador.
                     * @param errorEmail: mensaje de error de email
                     */
                    @Override
                    public void onErrorAccountEmail(String errorEmail) {
                        executeCallback.onErrorEmail(errorEmail);
                    }

                    /**
                     * PRO: Transfiere el resultado, de que el nombre de usuario está usado en el server,
                     * a la callback que se relaciona con el presentador.
                     * @param errorUserName: mensaje de error de nombre de usuario
                     */
                    @Override
                    public void onErrorUserName(String errorUserName) {
                        executeCallback.onErrorUserName(errorUserName);
                    }
                }
        );
    }
}
