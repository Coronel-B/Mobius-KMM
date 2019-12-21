package org.itdevelopers.mobius.users.data.cloud;

import org.itdevelopers.mobius.users.domain.entities.User;

/**
 * 6/1/2018
 *
 * Implementación concreta de la fuente de datos remota
 *
 * PRO: Modela la fuente de datos remota de los usuarios
 * OBS: Es una clase Singleton.
 */
public class CloudUsersDataSource implements ICloudUsersDataSource {


    private static final CloudUsersDataSource ourInstance = new CloudUsersDataSource();

    public static CloudUsersDataSource getInstance() {
        return ourInstance;
    }

    private CloudUsersDataSource() {
    }


    /**
     * TODO: Implementar petición real
     * PRO: Agrega a un usuario de manera ficticia
     * 1. Simular delay con un subproceso de trabajo
     * 2. Registrar al usuario
     * 3. Enviar respuesta al interactor dependiendo del código de API
     * OBS: Luego de codificar este método termino la comunicación con el interactor.
     * @param user: Usuario a registrar
     * @param callback: Caso a reportar
     */
    @Override
    public void addUser(final User user, final UserServiceCallback callback) {
        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        String apiCode =
//                                "username";
//                                "email";
                                "other";

                        switch (apiCode) {
                            case "email":
                                callback.onAddUserErrorAccountEmail("El email ya está confirmado");
                                break;
                            case "username":
                                callback.onAddUserErrorName("El nombre de usuario ya está en uso");
                                break;
                            default:
//                        response.isSuccessful
                                if (true) {
                                    callback.onAddUserFinished("Usuario registrado");
                                } else {
                                    callback.onAddUserError("Usuario no registrado por error en el server.");
                                }
                                break;
                        }


                    }
                },
                2000    //retraso de ejecución
        );

    }

}
