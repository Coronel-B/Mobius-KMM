package org.techdev.mobius.addedituseraccount.presentation;

import org.techdev.mobius.users.domain.entities.UserAccount;

/**
 * PRO: Define Microinteracciones MVP y crea contrato MVP
 * OBS: Solventando la cadena de eventos desembocados desde la vista hacia el presentador y viceversa.
 * 'Definir microinteracciones' es escribir un resumen de los propósitos y 'crear contrato' es escribir
 * los métodos de ambas interfaces considerando las acciones de las microinteracciones.
 */
public interface AddEditUserAccountMvp {

    /**
     * PRO: La vista define las reacciones de UI acorde al boceto
     *
     *  . Mostrar error de correo inválido
     *  . Mostrar error de contraseña inválida
     *  . Mostrar error de confirmación de contraseña inválida
     *  . Redirigir al usuario a la pantalla de añadir/editar usuario
     *
     *  En caso de edición:
     *
     *  . Mostrar el email del usuario
     *
     * OBS: Los parámetros serán aquellos datos necesarios para que el presentador realice alguna acción
     * o para que afecte a la vista.
     */
    interface View {

        /**
         * PRO: Muestra error de correo inválido
         * OBS: El error puede ser por el formato o porque está usado.
         */
        void showEmailError(boolean show, String emailError);

        /**
         * PRO: Muestra error de contraseña inválida
         */
        void showPasswordError(boolean show);

        /**
         * PRO: Muestra error de confirmación de contraseña inválida
         */
        void showPasswordConfirmationError(boolean show);

        /**
         * PRO: Redirige al futuro usuario a la pantalla de añadir/editar usuario
         */
        void showAddEditUserScreen();

        /**
         * PRO: Muestra el email de usuario
         * PRE: El caso es de edición.
         * OBS: Las contraseñas se limpian.
         */
        void showEmail(String email);

        void setPresenter(Presenter presenter);

    }

    /**
     * Las reacciones por parte del presentador son:
     *
     *  . Guardar la cuenta de usuario
     *  . Poblar la cuenta de usuario
     */
    interface Presenter {

        /**
         * PRO: Pobla la cuenta de usuario
         * OBS: Si es una edición, se debe cargar la cuenta a editar sobre la interfaz.
         */
        void populateUserAccount();

        /**
         * PRO: Guarda la cuenta de usuario
         * PRE: El constructor de @param userAccount tiene que tener solo el email y la pw
         */
        void saveUserAccount(UserAccount userAccount, String confirmationPassword);



    }
}
