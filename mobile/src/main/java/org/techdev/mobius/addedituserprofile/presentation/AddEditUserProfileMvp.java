package org.techdev.mobius.addedituserprofile.presentation;

import android.content.Context;
import android.widget.ImageView;

import org.techdev.mobius.users.domain.entities.UserProfile;

import androidx.annotation.NonNull;

/**
 * 24/1/2019
 *
 *  * PRO: Define Microinteracciones MVP y crea contrato MVP
 *  * OBS: Solventando la cadena de eventos desembocados desde la vista hacia el presentador y viceversa.
 *  * 'Definir microinteracciones' es escribir un resumen de los propósitos y 'crear contrato' es escribir
 *  * los métodos de ambas interfaces considerando las acciones de las microinteracciones.
 */
public interface AddEditUserProfileMvp {

    /**
     * PRO: La vista define las reacciones de UI acorde al boceto
     *
     *  . Mostrar/ocultar error de DNI
     *  . Mostrar/ocultar error de número de trámite
     *  . Mostrar/ocultar error de nombre
     *  . Mostrar/ocultar error de apellido
     *  . Mostrar/ocultar error de usuario de Facebook
     *  . Mostrar/ocultar error de localidad de procedencia
     *  . Mostrar/ocultar error de la fecha de nacimiento
     *  . Mostrar/ocultar error del día de la fecha de nacimiento
     *  . Mostrar/ocultar error del mes de la fecha de nacimiento
     *  . Mostrar/ocultar error del año de la fecha de nacimiento
     *  . Mostrar/ocultar error de celular
     *  . Mostrar/ocultar error de género
     *  . Mostrar/ocultar error de avatar
     *
     *  . Redirigir al usuario a un diálogo p/reconocer al DNI
     *  . Redirigir al usuario a un diálogo p/reconocer el Id del DNI
     *  . Redirigir al usuario a la pantalla de la muestra del id del dni
     *  . Redirigir al usuario a la pantalla de las políticas de privacidad
     *  . Redirigir al usaurio a un diálogo p/seleccionar la fecha de cumpleaños
     *  . Redirigir al usuario a un pantalla p/seleccionar la localidad de procedencia
     *  . Redirigir al usuario a la pantalla de añadir/editar usuario
     *  . Redirigir al usuario a la cámara p/generar un avatar
     *  . Redirigir al usuario a la galería p/generar un avatar
     *
     *  En caso de edición:
     *
     *  . Mostrar el DNI
     *  . Mostrar el número de trámite
     *  . Mostrar el nombre
     *  . Mostrar el apellido
     *  . Mostrar el usuario de Facebook
     *  . Mostrar la localidad de procedencia
     *  . Mostrar la fecha de nacimiento
     *  . Mostrar el celular
     *  . Mostrar el género
     *  . Mostrar el avatar
     *
     *  . Establecer presentador
     *
     * OBS: Los parámetros serán aquellos datos necesarios para que el presentador realice alguna acción
     * o para que afecte a la vista.
     * Además de mostrar los errores, parametrizo p/asegurarme de ocultarlos cuando no los muestro.
     */
    interface View {

        void showDniError(boolean show);
        void showDniIdError(boolean show);
        void showNameError(boolean show);
        void showSurnameError(boolean show);
        void showFacebookUserNameError(boolean show);
        void showOriginLocalityNotSelectedError(boolean show);

        /**
         * PRO: Muestra el error de la fecha de nacimiento
         */
        void showBirthdateError(boolean show);
        void showBirthdateDayOfMonthError(boolean show);
        void showBirthdateMonthError(boolean show);
        void showBirthdateYearError(boolean show);
        void showPhoneError(boolean show);
        void showGenderNotSelectedError();

        /**
         * PRO: Muestra el error del avatar
         */
        void showAvatarError(boolean show);

        /**
         * PRO: Redirige al usuario a un diálogo de reconocimiento
         * @param type
         */
        void showAcknowledgmentDialog(int type);

        /**
         * PRO: Redirige al usuario a la pantalla de la muestra del id del dni
         * OBS: EL id del dni es el número de trámite.
         * El icono desde donde se lanza este método es un signo de pregunta
         */
//        void showDniIdSampleScreen();

        /**
         * PRO: Redirige al usuario a la pantalla de las políticas de privacidad
         */
        void showTermsOfServiceScreen();

        /**
         * PRO: Redirige al usuario al diálogo para seleccionar la fecha de cumpleaños
         */
        void showBirthdatePickerDialog();

        /**
         * PRO: Redirige al usuario a la pantalla para seleccionar la localidad de procedencia
         */
        void showOriginLocalityPickerScreen();

        /**
         * PRO: Redirige al usuario a la pantalla de añadir/editar usuario
         * OBS: La edición del usuario se realizará en otra versión de la app
         */
        void showAddEditUserScreen();

        /**
         * PRO: Redirige al usuario a la cámara p/generar un avatar
         */
        void showCameraApp();

        /**
         * PRO: Redirige al usuario a la galería para seleccionar un avatar
         */
        void showGalleryApp();

        /**
         * PRO: Elimina el avatar del perfil
         */
        void onRemoveProfileAvatarClick();

        void showDni(int dni);
        void showDniId(long dniId);
        void showName(String name);
        void showSurname(String surname);
        void showFacebookUserName(String facebookUserName);
        void showOriginLocality(String originLocality);
        void showBirthdate(int year, int monthValue, int dayOfMonth);
        void showPhone(long phone);

        /**
         * PRO: Habilita o deshablita un género según @param gender
         */
        void showGender(char gender);

        /**
         * PRO: Asigna el contenido del avatar al view
         * OBS: Se usa la librería Glide
         */
        void showContentAvatarView(@NonNull String avatarPath);

        /**
         * PRO: Muestra/oculta la visibilidad del avatar
         * PRE: Las propiedades pueden ser visible, invisible o gone
         */
        void showAvatarImage(int property);

        /**
         * PRO: Muestra/oculta la visibilidad del botón p/remover el avatar
         * PRE: Las propiedades pueden ser visible, invisible o gone
         */
        void showRemoveAvatarButton(int property);

        void setPresenter(Presenter presenter);
    }


    /**
     * Las reacciones por parte del presentador son:
     *
     *  . Procesar el resultado de la interacción e/screens p/setear la fecha de nacimiento
     *  . Procesar el resultado de la interacción e/screens p/setear la localidad de procedencia
     *  . Procesar el resultado de la interacción e/screens p/setear el avatar desde galería
     *  . Procesar el resultado de la interacción e/screens p/setear el avatar desde cámara
     *  . Manejar el resultado de la selección de la fecha de nacimineto
     *  . Manejar el resultado de la selección de la localidad de procedencia
     *  . Manejar el resultado de la selección de un avatar desde galería
     *  . Manejar el resultado de la toma de un avatar desde la cámara
     *  . Remover el avatar del perfil
     *  . Poblar el perfil de usuario
     *  . Guardar el perfil de usuario
     */
    interface Presenter {

        /**
         * PRO: Procesa el resultado de la interacción e/screens p/reconocer el dni
         * OBS: Las screens son el fragmento del fill in del perfil y el dialog del reconocimiento del dni
         */
        void acknowledgeDni();

        /**
         * PRO: Procesa el resultado de la interacción e/screens p/reconocer el id del dni
         * OBS: Las screens son el fragmento del fill in del perfil y el dialog del reconocimiento del id del dni
         */
        void acknowledgeDniId();

        /**
         * PRO: Procesa el resultado de la interacción e/screens p/seleccionar la fecha de nacimiento
         * OBS: Las screens son el fragmento del fill in del perfil y el dialog del date picker.
         */
        void selectBirthdate();

        /**
         * PRO: Procesa el resultado de la interacción e/screens p/seleccionar la localidad de procedencia
         * OBS: Las screens son el fragmento del fill in del perfil y el fragmento de maps.
         */
        void selectOriginLocality();

        /**
         * PRO: Procesa el resultado de la interacción e/screens p/seleccionar el avatar desde galería
         * OBS: Las screens son el fragmento del fill in del perfil y la app de galería
         */
        void selectAvatarFromGallery();

        /**
         * PRO: Procesa el resultado de la interacción e/screens p/generar el avatar desde cámara
         * OBS: Las screens son el fragmento de fill in del perfil y la app de cámara
         */
        void captureAvatarFromCamera();

        void manageDatePickingResult();

        /**
         * PRO: Maneja el resultado de la selección de la localidad de procedencia
         */
        void manageOriginLocalityPickingResult(String originLocality);

        /**
         * PRO: Maneja el resultado de la selección del avatar desde galería
         */
        void manageAvatarFromGalleryPickingResult(Context context,
                                                  String currentAvatarPath,
                                                  ImageView avatarImage);

        /**
         * PRO: Maneja el resultado de la generación del avatar desde cámara
         */
        void manageAvatarFromCameraCaptureResult(Context context,
                                                 String currentAvatarPath,
                                                 ImageView avatarImage);

        /**
         * PRO: Borra el avatar del perfil
         */
        void deleteProfileAvatar();

        /**
         * PRO: Pobla el perfil de usuario
         * OBS: Si es una edición se debe cargar el perfil a editar sobre la interfaz.
         */
        void populateUserProfile();

        /**
         * PRO: Guarda el perfil del usuario
         * OBS: A la ubicación seleccionada la paso dentro del objeto de @param profile
         */
        void saveUserProfile(UserProfile userProfile);


    }

}
