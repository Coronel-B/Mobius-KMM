package org.techdev.mobius.users.domain.usecases;


import org.jetbrains.annotations.NotNull;
import org.techdev.mobius.users.domain.entities.User;

/**
 * 5/1/2019
 * Interactor Para Añadir Usuarios
 *
 * Representación del caso de uso para añadir usuarios.
 * Antes de codificar se determinan las entradas, salidas y procesos a la hora de contactar a la capa de datos.
 *
 * ¿Entradas?
 * 1. Una nueva instancia User construida en el formulario de creación de usuarios.
 * 2. Parámetros adicionales para afectar la lógica de las fuentes de datos.
 *
 * ¿Procesos?
 * . Hacer llamada de la operación de guardado en el repositorio de datos con los insumos de la entrada
 * . Aplicar restricciones de lógica de negocios y validaciones necesarias.
 *
 * ¿Salidas?
 * Éxito: Se retorna la misma instancia entrante como símbolo de integridad.
 * Fallas: . Nombre de usuario en uso
 *         . Email confirmado
 *         . Un mensaje de error para el usuario (latencias o códigos de error generados por server o db)
 *
 * OBS: También se podría aplicar otro caso de uso para evaluar previamente si hay fallas: IEmailRegistrable.
 */
public interface ISaveUser {

    /**
     * PRO: Describe una callback auxiliar que contiene los casos posibles a reportar
     * PRE: Conocer el significado de los códigos de la API p/saber los casos a implementar.
     */
    interface ExecuteCallback {

        /**
         * PRO: Denota un mensaje.
         * OBS: Se reporta al resultar bien el sign up, es decir, que se haya creado el usuario.
         * El significado de una petición exitosa es la creación del usuario (http 201)
         * @param message: proviene de Response del ws
         */
        void onSuccess(String message);

        /**
         * PRO: Denota un mensaje de error
         * OBS: Se reporta al fallar la petición del sign up
         * @param error: proviene de ApiException del ws
         */
        void onError(String error);

        /**
         * PRO: Se hace énfasis en la reescritura del email y se le pasa el error al EditText
         * OBS: Se reporta si ya existe el email confirmado (http 200)
         * @param errorEmail: proviene de ApiException del ws
         */
        void onErrorEmail(String errorEmail);

        /**
         * PRO: Se hace énfasis en la reescritura del nombre de usuario y se le pasa el error al EditText
         * OBS: Se porta si ya existe el nombre de usuario (http 200)
         * @param errorUserName: proviene de ApiException del ws
         */
        void onErrorUserName(String errorUserName);

    }

    /**
     * @param user: usuario a guardar
     * @param executeCallback: instancia de la callback interna p/determinar el momento en que se termina la operación
     */
    void execute(@NotNull User user, ExecuteCallback executeCallback);

}
