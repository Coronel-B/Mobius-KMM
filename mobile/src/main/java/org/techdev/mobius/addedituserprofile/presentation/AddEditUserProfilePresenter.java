package org.techdev.mobius.addedituserprofile.presentation;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.common.util.Strings;
import com.google.common.base.Preconditions;

import org.techdev.mobius.addedituserprofile.AddEditUserProfileActivity;
import org.techdev.mobius.addedituserprofile.data.ICacheUserProfileStore;
import org.techdev.mobius.shared.util.LocalDateUtils;
import org.techdev.mobius.users.domain.entities.UserProfile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 12/2/2019
 *
 * OBS: Presentador de Añadir/Editar cuenta de usuario
 */
public class AddEditUserProfilePresenter implements AddEditUserProfileMvp.Presenter {

    public static final String TAG = AddEditUserProfilePresenter.class.getSimpleName();

    /*
    Campos de relación:

     . La vista
     . La caché del perfil de usuario
     . Perfil de usuario a editar si fuera el caso
     . La fuente de datos de recursos de Android. De utilidad p/obtener algunos textos.
     */
    private final AddEditUserProfileMvp.View view;
    private final ICacheUserProfileStore iCacheUserProfileStore;
    private UserProfile userProfile;
    private final Resources resources;

    private String currentAvatarPath;

    //    Tomo las referencias de los campos
    public AddEditUserProfilePresenter(
            @Nullable UserProfile userProfile,
            @NonNull AddEditUserProfileMvp.View view,
            @NonNull ICacheUserProfileStore iCacheUserProfileStore,
            @NonNull Resources resources) {
        this.userProfile = userProfile;
        this.view = Preconditions.checkNotNull(view);
        this.iCacheUserProfileStore = Preconditions.checkNotNull(iCacheUserProfileStore);
        this.resources = Preconditions.checkNotNull(resources);
    }


    @Override
    public void acknowledgeDni() {
        view.showAcknowledgmentDialog(AddEditUserProfileActivity.REQUEST_ACKNOWLEDGE_DNI_DIALOG_FRAGMENT);
    }

    @Override
    public void acknowledgeDniId() {
        view.showAcknowledgmentDialog(AddEditUserProfileActivity.REQUEST_ACKNOWLEDGE_DNI_ID_DIALOG_FRAGMENT);
    }

    /**
     * PRO: Ordena a la vista mostrar el widget de fecha de nacimiento
     */
    @Override
    public void selectBirthdate() {
        view.showBirthdatePickerDialog();
    }

    @Override
    public void selectOriginLocality() {
        view.showOriginLocalityPickerScreen();
    }

    @Override
    public void selectAvatarFromGallery() {
        view.showGalleryApp();
    }

    @Override
    public void captureAvatarFromCamera() {
        view.showCameraApp();
    }

    /**
     * PRO: Setea la fecha de nacimiento
     */
    @Override
    public void manageDatePickingResult() {

    }


    /**
     * PRO: Setea la localidad de origen
     * @param originLocality
     */
    @Override
    public void manageOriginLocalityPickingResult(String originLocality) {
        if (Strings.isEmptyOrWhitespace(originLocality)) {
            view.showOriginLocalityNotSelectedError(true);
            return;
        }
        view.showOriginLocality(originLocality);
    }

    /**
     * PRO: Se asegura de que la imagen pueda obtenerse y así asignarla al widget
     */
    @Override
    public void manageAvatarFromGalleryPickingResult(Context context,
                                                     String currentAvatarPath,
                                                     ImageView avatarImage) {
        if (currentAvatarPath != null) {
            setAvatarFrom(context, currentAvatarPath, avatarImage);
        }
    }

    /**
     * PRO: Se asegura de que exista una ruta actual p/el archivo del avatar y así asignarlo al widget.
     */
    @Override
    public void manageAvatarFromCameraCaptureResult(Context context,
                                                    String currentAvatarPath,
                                                    ImageView avatarImage) {
        if (currentAvatarPath != null) {
            setAvatarFrom(context, currentAvatarPath, avatarImage);
        }
    }

    /**
     * PRO: Asigna el contenido del avatar al view
     * OBS: Se usa la librería Glide
     */
    private void setAvatarFrom(Context context,
                               String currentAvatarPath,
                               ImageView avatarImage) {
        Glide.with(context)
                .load(currentAvatarPath)
                .apply(RequestOptions.centerCropTransform())
                .into(avatarImage);
        view.showAvatarImage(View.VISIBLE);
        view.showRemoveAvatarButton(View.VISIBLE);
        view.showAvatarError(false);
    }

//    TODO: Borrar el avatar generado en la caché
    @Override
    public void deleteProfileAvatar() {
        view.onRemoveProfileAvatarClick();
    }

    /**
     * PRO: Carga el perfil de usuario a editar
     * OBS: En el caso de que el usuario haya optado por una edición, cargo algunos valores.
     * Luego asigno el resultado a la vista.
     */
    @Override
    public void populateUserProfile() {
        if (isEdition()) {
            iCacheUserProfileStore.getUserProfile(new ICacheUserProfileStore.GetUserProfileCallback() {
                @Override
                public void onUserProfileLoaded(UserProfile userProfile) {
                    showProfile(userProfile);
                }
            });
        }
    }

    /**
     * PRO: Valida la edición comprobando que el perfil del usuario no sea nulo
     */
    private boolean isEdition() {
       return userProfile != null;
    }

    /**
     * PRO: Simplifica la muestra de los atributos del perfil
     * OBS: Los campos son autocompletados
     */
    private void showProfile(UserProfile userProfile) {
        view.showDni(userProfile.getDni());
        view.showDniId(userProfile.getDniId());
        view.showName(userProfile.getName());
        view.showSurname(userProfile.getSurname());
        view.showFacebookUserName(userProfile.getFacebookUserName());
        view.showOriginLocality(userProfile.getOriginLocality());
        view.showBirthdate(
                userProfile.getBirthdate().getYear(),
                userProfile.getBirthdate().getMonthValue(),
                userProfile.getBirthdate().getDayOfMonth()
        );
        view.showPhone(userProfile.getPhone());
        view.showGender(userProfile.getGender());
        view.showContentAvatarView(userProfile.getAvatarPath());
    }

    /**
     * PRO: Guarda el perfil del usuario
     * PRE: Antes de hacer las validaciones checkear que tipos de datos y valores estoy recibiendo
     * OBS: Guardar el perfil del usuario significaría hacer algunos controles:
     *  4. Validar los campos
     *  5. Determinar si el perfil aún sigue siendo el de la de edición o si
     *     viene por una creación.
     *  8. Crear un objeto Profile
     *  9. Guardar el objeto en la caché
     *  10. Volver a la actividad de creación del usuario
     */
    @Override
    public void saveUserProfile(UserProfile profile) {
//        Validación de dni
        if (String.valueOf(profile.getDni()).length() != 8) {
            view.showDniError(true);
            return;
        }
        else
            view.showDniError(false);


//        Validación del número de trámite
        if (String.valueOf(profile.getDniId()).length() != 15) {
            view.showDniIdError(true);
            return;
        }
        else
            view.showDniIdError(false);


//        Validación del nombre
        if (TextUtils.isEmpty(profile.getName()) || containsNumbers(profile.getName())) {
            view.showNameError(true);
            return;
        }
        else
            view.showNameError(false);

//        Validación del apellido
        if (TextUtils.isEmpty(profile.getSurname()) || containsNumbers(profile.getSurname())) {
            view.showSurnameError(true);
            return;
        }
        else
            view.showSurnameError(false);

//        Validación del usuario de facebook
        if (TextUtils.isEmpty(profile.getFacebookUserName())) {
            view.showFacebookUserNameError(true);
            return;
        }
        else
            view.showFacebookUserNameError(false);

//        Validación de la localidad de procedencia. En edición profile.getOriginLocality() es null
        if (TextUtils.isEmpty(profile.getOriginLocality())) {
            view.showOriginLocalityNotSelectedError(true);
            return;
        }
        else
            view.showOriginLocalityNotSelectedError(false);

//        Validación del celular
        if (!validatePhone(profile.getPhone())) {
            view.showPhoneError(true);
            return;
        }
        else
            view.showPhoneError(false);

//        Validación de la fecha de cumpleaños
        if (profile.getBirthdate() == LocalDateUtils.EMPTY_FIELDS) {
            view.showBirthdateError(true);
            return;
        }
        else {
            view.showBirthdateError(false);
            view.showBirthdateDayOfMonthError(false);
            view.showBirthdateMonthError(false);
            view.showBirthdateYearError(false);
        }

//        Validación del día de la fecha de cumpleaños
        if (profile.getBirthdate() == LocalDateUtils.EMPTY_FIELD_DAY_OF_MONTH ||
            !containsOnlyNumbers(String.valueOf(profile.getBirthdate().getDayOfMonth())) ||
            !(String.valueOf(profile.getBirthdate().getDayOfMonth()).length() <= 2)) {
            view.showBirthdateDayOfMonthError(true);
            return;
        }
        else
            view.showBirthdateDayOfMonthError(false);

//        Validación del mes de la fecha de cumpleaños
        if (profile.getBirthdate() == LocalDateUtils.EMPTY_FIELD_MONTH ||
            !containsOnlyNumbers(String.valueOf(profile.getBirthdate().getMonthValue())) ||
            !(String.valueOf(profile.getBirthdate().getMonthValue()).length() <= 2)) {
            view.showBirthdateMonthError(true);
            return;
        }
        else
            view.showBirthdateMonthError(false);

//        Validación del año de la fecha de cumpleaños
        if (profile.getBirthdate() == LocalDateUtils.EMPTY_FIELD_YEAR ||
            !containsOnlyNumbers(String.valueOf(profile.getBirthdate().getYear())) ||
            !(String.valueOf(profile.getBirthdate().getYear()).length() == 4)) {
            view.showBirthdateYearError(true);
            return;
        }
        else
            view.showBirthdateYearError(false);

//        Validación de género
        if (!String.valueOf(profile.getGender()).equals("M") &&
            !String.valueOf(profile.getGender()).equals("F")) {
            view.showGenderNotSelectedError();
            return;
        }

//        Validación de avatar
        if (profile.getAvatarPath() == null) {
            view.showAvatarError(true);
            return;
        }
        else
            view.showAvatarError(false);

        Log.d(TAG, "Usuario exitoso");

        iCacheUserProfileStore.saveUserProfile(profile);
        view.showAddEditUserScreen();
        }

    /**
     * PRO: Describe si una cadena contiene números
     */
    private static boolean containsNumbers(String str){
        for(char ch : str.toCharArray()){
            if(Character.isDigit(ch)){
                return true;
            }
        }
        return false;
    }

    /**
     * PRO: Indica si una cadena contiene solamente números
     * Fuente: https://stackoverflow.com/a/10575676/5279996
     */
    private static boolean containsOnlyNumbers(String str) {
        return str.matches("[0-9]+");
    }

    /**
     * PRO: Describe si un teléfono es válido
     * OBS: Prefijo nacional + 9 + prefjo ciudad
     * Ejemplo La Plata: 54 9 221 1555067943
     */
    private static boolean validatePhone(long phone) {
        return String.valueOf(phone).length() >= 10;
    }

}
