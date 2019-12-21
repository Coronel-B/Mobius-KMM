package org.itdevelopers.mobius.addedituserprofile.presentation;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.common.util.IOUtils;
import com.google.common.base.Preconditions;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.itdevelopers.mobius.R;
import org.itdevelopers.mobius.addedituserprofile.AddEditUserProfileActivity;
import org.itdevelopers.mobius.privacy.legal_and_policies.TermsOfService;
import org.itdevelopers.mobius.users.domain.entities.UserProfile;
import org.itdevelopers.mobius.util.LocalDateUtils;
import org.itdevelopers.mobius.util.TextWatcherLabelUtils;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * 26/1/2019
 */
public class AddEditUserProfileFragment extends Fragment implements
        AddEditUserProfileMvp.View,
        View.OnClickListener {

    public static final String TAG = AddEditUserProfileFragment.class.getSimpleName();

    private int stackLevel = 0;

    //    Campo de relación con el presentador
    private AddEditUserProfileMvp.Presenter presenter;

//    Referencias a widgets con los que el usuario podrá interactuar
    private TextInputLayout dniFieldWrapper, dniIdFieldWrapper, nameFieldWrapper, surnameFieldWrapper,
                            facebookUserFieldWrapper, originLocalityFieldWrapper, phoneFieldWrapper,
                            birthdateDayOfMonthFieldWrapper, birthdateMonthFieldWrapper, birthdateYearFieldWrapper,
                            genderFieldWrapper;
    private EditText dniField, dniIdField, nameField, surnameField, facebookUserField, phoneField, originLocalityField,
                     dayBirthdateField, monthBirthdateField, yearBirthdateField ;
    private ImageButton dniAcknowledgmentButton, dniIdAcknowledgmentButton, birthdateButton,
                        generateProfileAvatarButton, selectProfileAvatarButton, removeProfileAvatarButton;
    private TextView creationProfileBirthdateLabel, creationProfileAvatarLabel;

//    TODO: Usar AutoCompleteTextView https://developer.android.com/training/keyboard-input/style#java
    private Spinner nationalityMenu;

    private RadioButton femaleRadioBtn, maleRadioBtn;
    private ImageView avatarImage;

    private String originLocalityPicked;
    private Date birthdatePicked;
    private String nationalityPicked;
    private String currentAvatarPath;

    public AddEditUserProfileFragment() {
    }

    public static AddEditUserProfileFragment newInstance() {
        return new AddEditUserProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            stackLevel = savedInstanceState.getInt("level");
        }
        AndroidThreeTen.init(getContext());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("level", stackLevel);
    }

    /**
     * PRO: Configura vista de UI
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View uiRoot = inflater.inflate(R.layout.fragment_add_edit_user_profile, container, false);

//        Se toman las referencias de UI de los views que el usuario manipulará
        dniFieldWrapper = uiRoot.findViewById(R.id.float_label_dni);
        dniIdFieldWrapper = uiRoot.findViewById(R.id.float_label_dni_id);
        nameFieldWrapper = uiRoot.findViewById(R.id.float_label_profile_name);
        surnameFieldWrapper = uiRoot.findViewById(R.id.float_label_surname);
        facebookUserFieldWrapper = uiRoot.findViewById(R.id.float_label_facebook_user);
        originLocalityFieldWrapper = uiRoot.findViewById(R.id.float_label_origin_locality_selection);
        phoneFieldWrapper = uiRoot.findViewById(R.id.float_label_phone);
        creationProfileBirthdateLabel = uiRoot.findViewById(R.id.creation_profile_birthdate_label);
        creationProfileAvatarLabel = uiRoot.findViewById(R.id.creation_profile_avatar_label);
        birthdateDayOfMonthFieldWrapper = uiRoot.findViewById(R.id.float_label_birthdate_day_of_month);
        birthdateMonthFieldWrapper = uiRoot.findViewById(R.id.float_label_birthdate_month);
        birthdateYearFieldWrapper = uiRoot.findViewById(R.id.float_label_birthdate_year);
        genderFieldWrapper = uiRoot.findViewById(R.id.float_label_gender_selection);

        dniField = uiRoot.findViewById(R.id.text_field_dni);
        dniIdField = uiRoot.findViewById(R.id.text_field_dni_id);
        nameField = uiRoot.findViewById(R.id.text_field_profile_name);
        surnameField = uiRoot.findViewById(R.id.text_field_surname);
        facebookUserField = uiRoot.findViewById(R.id.text_field_facebook_user);
        phoneField = uiRoot.findViewById(R.id.text_field_phone);
        dayBirthdateField = uiRoot.findViewById(R.id.text_field_birthdate_day_of_month);
        monthBirthdateField = uiRoot.findViewById(R.id.text_field_birthdate_month);
        yearBirthdateField = uiRoot.findViewById(R.id.text_field_birthdate_year);
        dniAcknowledgmentButton = uiRoot.findViewById(R.id.dni_acknowledgment_button);
        dniIdAcknowledgmentButton = uiRoot.findViewById(R.id.dni_id_acknowledgment_button);
        birthdateButton = uiRoot.findViewById(R.id.birthdate_selection_button);
        generateProfileAvatarButton = uiRoot.findViewById(R.id.generate_profile_avatar_button);
        selectProfileAvatarButton = uiRoot.findViewById(R.id.select_profile_avatar_button);
        removeProfileAvatarButton = uiRoot.findViewById(R.id.remove_profile_avatar_button);
        originLocalityField = uiRoot.findViewById(R.id.text_field_selection_origin_locality);
        nationalityMenu = uiRoot.findViewById(R.id.nationality_menu);
        femaleRadioBtn = uiRoot.findViewById(R.id.female_radio_btn);
        maleRadioBtn = uiRoot.findViewById(R.id.male_radio_btn);
        avatarImage = uiRoot.findViewById(R.id.profile_avatar);

        /*
        Eventos de UI:
         . Cambios en los campos de textos
         . Click en:
              - botones de reconocimiento de dni e id del dni
              - texto de localidad de origen
              - boton de fecha fecha
              - botones de generar, seleccionar y borrar avatar
              - botones de radio de género
         . Selección de ítem en la nacionalidad
         . Action Bar
         */
        dniField.addTextChangedListener(new TextWatcherLabelUtils(dniFieldWrapper));
        dniIdField.addTextChangedListener(new TextWatcherLabelUtils(dniIdFieldWrapper));
        nameField.addTextChangedListener(new TextWatcherLabelUtils(nameFieldWrapper));
        surnameField.addTextChangedListener(new TextWatcherLabelUtils(surnameFieldWrapper));
        facebookUserField.addTextChangedListener(new TextWatcherLabelUtils(facebookUserFieldWrapper));
        originLocalityField.addTextChangedListener(new TextWatcherLabelUtils(originLocalityFieldWrapper));
        phoneField.addTextChangedListener(new TextWatcherLabelUtils(phoneFieldWrapper));
        dayBirthdateField.addTextChangedListener(new TextWatcherLabelUtils(birthdateDayOfMonthFieldWrapper));
        monthBirthdateField.addTextChangedListener(new TextWatcherLabelUtils(birthdateMonthFieldWrapper));
        yearBirthdateField.addTextChangedListener(new TextWatcherLabelUtils(birthdateYearFieldWrapper));
        femaleRadioBtn.addTextChangedListener(new TextWatcherLabelUtils(genderFieldWrapper));
        maleRadioBtn.addTextChangedListener(new TextWatcherLabelUtils(genderFieldWrapper));

        dniAcknowledgmentButton.setOnClickListener(this);
        dniIdAcknowledgmentButton.setOnClickListener(this);
        originLocalityField.setOnClickListener(this);
        birthdateButton.setOnClickListener(this);
        femaleRadioBtn.setOnClickListener(this);
        maleRadioBtn.setOnClickListener(this);
        generateProfileAvatarButton.setOnClickListener(this);
        selectProfileAvatarButton.setOnClickListener(this);
        removeProfileAvatarButton.setOnClickListener(this);

//        Se habilita la contribución a la action bar por parte del fragmento
        setHasOptionsMenu(true);

        return uiRoot;
    }

    /**
     * PRO: Establece la nacionalidad por defecto del Spinner
     */
    private void setNationalityByDefault() {
        ArrayAdapter arrayAdapter = (ArrayAdapter) nationalityMenu.getAdapter();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dni_acknowledgment_button:
                presenter.acknowledgeDni();
                break;
            case R.id.dni_id_acknowledgment_button:
                presenter.acknowledgeDniId();
                break;
            case R.id.text_field_selection_origin_locality:
                presenter.selectOriginLocality();
                break;
            case R.id.birthdate_selection_button:
                presenter.selectBirthdate();
                break;
            case R.id.female_radio_btn:
                showGender('F');
                break;
            case R.id.male_radio_btn:
                showGender('M');
                break;
            case R.id.generate_profile_avatar_button:
                presenter.captureAvatarFromCamera();
                break;
            case R.id.select_profile_avatar_button:
                presenter.selectAvatarFromGallery();
                break;
            case R.id.remove_profile_avatar_button:
                presenter.deleteProfileAvatar();
                break;
            default:
                break;
        }

    }

    /**
     * PRO: Carga perfil de usuario a editar
     * OBS: Cada vez que la actividad vuelva de un segundo plano
     */
    @Override
    public void onResume() {
        super.onResume();
        presenter.populateUserProfile();
    }

    /**
     * PRO: Maneja eventos de la Toolbar
     * OBS:
     *  . Una de las acciones a procesar es el guardado del perfil
     *  . Si paso selectedLocation en el perfil a guardar, en edición me llega null.
     *  Por ende obtengo el valor desde el campo de texto.
     *  . Si uso campos númericos o de selección (radioBtn), creo métodos adicionales p/tratar los errores.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* Tan solo consultaré al servidor para guardar un perfil.
         * Le paso al presentador la responsabilidad de la acción que realizaré con la fuente de datos.*/
        if (item.getItemId() == R.id.action_save_user_profile) {
            presenter.saveUserProfile(
                    new UserProfile(
                            getIntField(dniField.getText().toString()),
                            getLongField(dniIdField.getText().toString()),
                            nameField.getText().toString(),
                            surnameField.getText().toString(),
                            facebookUserField.getText().toString(),
                            originLocalityField.getText().toString(),
                            getLongField(phoneField.getText().toString()),
                            getBirthdate(
                                    getIntField(yearBirthdateField.getText().toString()),
                                    getIntField(monthBirthdateField.getText().toString()),
                                    getIntField(dayBirthdateField.getText().toString())
                            ),
                            getNationalityPicked(),
                            getGenderRadio(),
                            currentAvatarPath
                    )
            );
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * PRO: Describe el número entero del campo de texto u un 0
     * OBS: Acorde a si el usuario tipeo algún número
     * @return int: teléfono
     */
    private int getIntField(String field) {
        int intRet = 0;
        try {
            intRet = Integer.parseInt(field);
        } catch (NumberFormatException ex) {
            Log.d(TAG, String.valueOf(field));
        }
        return intRet;
    }

    /**
     * PRO: Describe el número long del campo de texto u un 0
     * OBS: Es long pues tiene más de 10 caracteres
     * @param field
     * @return
     */
    private long getLongField(String field) {
        long longRet = 0;
        try {
            longRet = Long.parseLong(field);
        } catch (NumberFormatException ex) {
            Log.d(TAG, String.valueOf(field));
        }
        return longRet;
    }

    /**
     * PRO: Describe una fecha de cumpleaños o una constante indicando campo/s vacío/s.
     * OBS: Acorde a si los parámetros son ceros.
     * Fuente:
     *  . https://stackoverflow.com/a/54799190/5279996
     *  . https://stackoverflow.com/a/54800223/5279996
     */
    private LocalDate getBirthdate(int year,
                                   int month,
                                   int dayOfMonth) {
        if (dayOfMonth == 0 && month == 0 && year == 0)
            return LocalDateUtils.EMPTY_FIELDS;
        if (dayOfMonth == 0)
            return LocalDateUtils.EMPTY_FIELD_DAY_OF_MONTH;
        if (month == 0)
            return LocalDateUtils.EMPTY_FIELD_MONTH;
        if (year == 0)
            return LocalDateUtils.EMPTY_FIELD_YEAR;
        return LocalDate.of(year, month, dayOfMonth);
    }

    /**
     * PRO: Describe la nacionalidad seleccionada del Spinner
     * Fuente: https://stackoverflow.com/a/5787902/5279996
     */
    private String getNationalityPicked() {
        return nationalityMenu.getSelectedItem().toString();
    }



    /**
     * PRO: Describe el género del campo de texto u otra cadena
     * OBS: Acorde a si se seleccionó un género
     * @return char: género
     */
    private char getGenderRadio() {
        char gender = 'X';
        if (femaleRadioBtn.isChecked()) {
            gender = 'F';
        } else if (maleRadioBtn.isChecked()) {
            gender = 'M';
        }
        return gender;
    }

    /**
     * PRO: Describe el avatar en un tipo File a partir de un path
     * OBS: Si es que el path existe
     * @return File: avatar del perfil
     */
    private File getAvatarImage() {
        File file = null;

        if (currentAvatarPath != null) {
            file = new File(currentAvatarPath);
        }
        return file;
    }

    @Override
    public void showDniError(boolean show) {
        if (!show) {
            dniIdFieldWrapper.setErrorEnabled(false);
            dniIdFieldWrapper.clearFocus();
        }
        else {
            dniFieldWrapper.setErrorEnabled(true);
            dniFieldWrapper.setError(getString(R.string.profile_dni_error));
            dniFieldWrapper.requestFocus();
        }
    }

    @Override
    public void showDniIdError(boolean show) {
        if (!show) {
            dniIdFieldWrapper.setErrorEnabled(false);
            dniIdFieldWrapper.clearFocus();
        }
        else {
            dniIdFieldWrapper.setErrorEnabled(true);
            dniIdFieldWrapper.setError(getString(R.string.profile_dni_id_error));
            dniIdFieldWrapper.requestFocus();
        }
    }

    @Override
    public void showNameError(boolean show) {
        if (!show) {
            nameFieldWrapper.setErrorEnabled(false);
            nameFieldWrapper.clearFocus();
        }
        else {
            nameFieldWrapper.setErrorEnabled(true);
            nameFieldWrapper.setError(getString(R.string.profile_name_error));
            nameFieldWrapper.requestFocus();
        }
    }

    @Override
    public void showSurnameError(boolean show) {
        if (!show) {
            surnameFieldWrapper.setErrorEnabled(false);
            surnameFieldWrapper.clearFocus();
        }
        else {
            surnameFieldWrapper.setErrorEnabled(true);
            surnameFieldWrapper.setError(getString(R.string.profile_surname_error));
            surnameFieldWrapper.requestFocus();
        }
    }

    @Override
    public void showFacebookUserNameError(boolean show) {
        if (!show) {
            facebookUserFieldWrapper.setErrorEnabled(false);
            facebookUserFieldWrapper.clearFocus();
        }
        else {
            facebookUserFieldWrapper.setErrorEnabled(true);
            facebookUserFieldWrapper.setError(getString(R.string.profile_facebook_user_error));
            facebookUserFieldWrapper.requestFocus();
        }
    }

    @Override
    public void showOriginLocalityNotSelectedError(boolean show) {
        if (!show) {
            originLocalityFieldWrapper.setErrorEnabled(false);
            originLocalityFieldWrapper.clearFocus();
        }
        else {
            originLocalityFieldWrapper.setErrorEnabled(true);
            originLocalityFieldWrapper.setError(getString(R.string.profile_origin_locality_not_selected_error));
            originLocalityFieldWrapper.requestFocus();
        }
    }

    /**
     * PRE: El TextView tiene los atributos focusable y focusableInTouchMode
     * Fuente: https://stackoverflow.com/a/15954372/5279996
     */
    @Override
    public void showBirthdateError(boolean show) {
        if (!show) {
            creationProfileBirthdateLabel.setError(null);
            creationProfileBirthdateLabel.clearFocus();
        }
        else {
            creationProfileBirthdateLabel.setError(getString(R.string.profile_birthdate_empty_fields_error));
            creationProfileBirthdateLabel.requestFocus();
        }
    }

    @Override
    public void showBirthdateDayOfMonthError(boolean show) {
        if (!show) {
            birthdateDayOfMonthFieldWrapper.setErrorEnabled(false);
            birthdateDayOfMonthFieldWrapper.clearFocus();
        }
        else {
            birthdateDayOfMonthFieldWrapper.setErrorEnabled(true);
            birthdateDayOfMonthFieldWrapper.setError(getString(R.string.profile_birthdate_day_of_month_error));
            birthdateDayOfMonthFieldWrapper.requestFocus();
        }
    }

    @Override
    public void showBirthdateMonthError(boolean show) {
        if (!show) {
            birthdateMonthFieldWrapper.setErrorEnabled(false);
            birthdateMonthFieldWrapper.clearFocus();
        }
        else {
            birthdateMonthFieldWrapper.setErrorEnabled(true);
            birthdateMonthFieldWrapper.setError(getString(R.string.profile_birthdate_month_error));
            birthdateMonthFieldWrapper.requestFocus();
        }
    }

    @Override
    public void showBirthdateYearError(boolean show) {
        if (!show) {
            birthdateYearFieldWrapper.setErrorEnabled(false);
            birthdateYearFieldWrapper.clearFocus();
        }
        else {
            birthdateYearFieldWrapper.setErrorEnabled(true);
            birthdateYearFieldWrapper.setError(getString(R.string.profile_birthdate_year_error));
            birthdateYearFieldWrapper.requestFocus();
        }
    }


    @Override
    public void showPhoneError(boolean show) {
        if (!show) {
            phoneFieldWrapper.setErrorEnabled(false);
            phoneFieldWrapper.clearFocus();
        }
        else {
            phoneFieldWrapper.setErrorEnabled(true);
            phoneFieldWrapper.setError(getString(R.string.profile_phone_error));
            phoneFieldWrapper.requestFocus();
        }
    }

    @Override
    public void showGenderNotSelectedError() {
        genderFieldWrapper.setErrorEnabled(true);
        genderFieldWrapper.setError(getString(R.string.profile_gender_not_selected_error));
        genderFieldWrapper.requestFocus();
    }

    /**
     * PRE: El TextView tiene los atributos focusable y focusableInTouchMode
     * Fuente: https://stackoverflow.com/a/15954372/5279996
     */
    @Override
    public void showAvatarError(boolean show) {
        if (!show) {
            creationProfileAvatarLabel.setError(null);
            creationProfileAvatarLabel.clearFocus();
        }
        else {
            creationProfileAvatarLabel.setError(getString(R.string.profile_avatar_empty_error));
            creationProfileAvatarLabel.requestFocus();
        }
    }

    /**
     * PRE: Los diálogos están listos
     * Fuente: https://stackoverflow.com/a/13733914/5279996
     * @param type
     */
    @Override
    public void showAcknowledgmentDialog(int type) {
        stackLevel++;

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        Fragment prevAcknowledgeDniDialog = getActivity().getSupportFragmentManager().findFragmentByTag("AcknowledgeDniDialog");
        Fragment prevAcknowledgeDniIdDialog = getActivity().getSupportFragmentManager().findFragmentByTag("AcknowledgeDniIdDialog");

        if (prevAcknowledgeDniDialog != null) {
            fragmentTransaction.remove(prevAcknowledgeDniDialog);
        }
        if (prevAcknowledgeDniIdDialog != null) {
            fragmentTransaction.remove(prevAcknowledgeDniIdDialog);

        }
        fragmentTransaction.addToBackStack(null);

        switch (type) {
            case AddEditUserProfileActivity.REQUEST_ACKNOWLEDGE_DNI_DIALOG_FRAGMENT:
                DialogFragment acknowledgeDniDialog = new AcknowledgeDniDialog();
                acknowledgeDniDialog.setTargetFragment(this, AddEditUserProfileActivity.REQUEST_ACKNOWLEDGE_DNI_DIALOG_FRAGMENT);
                acknowledgeDniDialog.show(getFragmentManager().beginTransaction(), "AcknowledgeDniDialog");
                break;
            case AddEditUserProfileActivity.REQUEST_ACKNOWLEDGE_DNI_ID_DIALOG_FRAGMENT:
                DialogFragment acknowledgeDniIdDialog = new AcknowledgeDniIdDialog();
                acknowledgeDniIdDialog.setTargetFragment(this, AddEditUserProfileActivity.REQUEST_ACKNOWLEDGE_DNI_ID_DIALOG_FRAGMENT);
                acknowledgeDniIdDialog.show(getFragmentManager().beginTransaction(), "AcknowledgeDniIdDialog");
                break;
        }
    }


//    TODO: Esto se lanza desde el framgneto de usuario con un check para aceptar las politicas
    @Override
    public void showTermsOfServiceScreen() {
        startActivity(TermsOfService.class);
    }

    /**
     * PRO: Muestra el picker
     * Fuentes:
     *  . SM #5
     *  . https://stackoverflow.com/a/20673756/5279996
     */
    @Override
    public void showBirthdatePickerDialog() {
        BirthdatePickerDialog dialog = new BirthdatePickerDialog();
        dialog.setCallback(onDateSetListener);
        dialog.show(getFragmentManager(), "birthdatePicker");
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        /**
         * PRO: Ejecuta las acciones de seteo de las fechas
         * OBS: Asignando los valores desde el DatePicker a los EditTexts.
         * Además se renombraron los parámetros.
         */
        @SuppressLint("SetTextI18n")
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            dayBirthdateField.setText(Integer.toString(dayOfMonth));
            monthBirthdateField.setText(Integer.toString(month));
            yearBirthdateField.setText(Integer.toString(year));
        }
    };

    /**
     * PRO: Inicia el fragmento del mapa.
     * OBS: En este caso el contexto del fragmento del mapa es ..LocationActivity.java
     * Aunque quizá podría ser el mismo fragmento del mapa.
     * Fuente:
     * https://stackoverflow.com/a/36496481/5279996
     */
    @Override
    public void showOriginLocalityPickerScreen() {
        Intent requestIntent = new Intent(getActivity(), OriginLocalityPickerMapActivity.class);
        requestIntent.setAction(OriginLocalityPickerMapActivity.ACTION_PICK_LOCATION);
        startActivityForResult(requestIntent, AddEditUserProfileActivity.REQUEST_PICK_LOCATION);
    }

    /**
     * PRO: Retorna a la edición de usuario
     */
    @Override
    public void showAddEditUserScreen() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    /**
     * PRO: Lanza un intento p/elegir un proveedor de contenido y seleccionar uno.
     * OBS: La app Documentos, es uno de los proveedores, permite que el usuario pueda usar cualquier
     * aplicación de galería que haya instalado.
     * El contenido es seleccionado a través de ACTION_GET_CONTENT
     * Fuentes:
     * . https://stackoverflow.com/a/5309217/5279996
     * . https://stackoverflow.com/a/5309965/5279996
     */
    @Override
    public void showGalleryApp() {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, AddEditUserProfileActivity.REQUEST_PICK_IMAGE_FROM_GALLERY);
    }

    /**
     * PRO: Crea un archivo desde un InputStream
     * PRE: Initialize the timezone information in your Application.onCreate() method
     * Fuentes:
     *  . https://stackoverflow.com/a/11501465/5279996
     *  . https://stackoverflow.com/a/38281881/5279996
     *
     */
    private File createAvatarFileFromGallery(InputStream inputStream) throws IOException {
        String timeStamp = LocalDateUtils.formatDateForFileName(LocalDateTime.now());
        String prefix = "JPEG_" + timeStamp + "_";

        File image = File.createTempFile(prefix, ".jpg");
        OutputStream outputStream = new FileOutputStream(image);
        IOUtils.copyStream(inputStream, outputStream);
        outputStream.close();

        return image;
    }

    /**
     * Fuente:
     *  . https://stackoverflow.com/a/48391446/5279996
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void showCameraApp() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

//        Comprueba la existencia de la app
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            Uri avatarURI = null;
            try {
                File avatarFile = createAvatarFileFromCamera();
                currentAvatarPath = avatarFile.getAbsolutePath();
                avatarURI = FileProvider.getUriForFile(
                        getActivity(),
                        getString(R.string.file_provider_authority),
                        avatarFile);
            } catch (IOException ex) {
                Log.d(TAG, "Error ocurrido cuando se estaba creando el archivo del avatar. Detalle: " +
                                 ex.getMessage());
            }

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, avatarURI);
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                takePictureIntent.setClipData(ClipData.newRawUri("", avatarURI));
                takePictureIntent.addFlags(
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION|
                        Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            startActivityForResult(takePictureIntent, AddEditUserProfileActivity.REQUEST_IMAGE_CAPTURE_FROM_CAMERA);
        }
    }

    /**
     * PRO: Crea un nuevo archivo en el directorio externo
     * PRE: Initialize the timezone information in your Application.onCreate() method
     * Fuente: https://stackoverflow.com/a/38281881/5279996
     * @return File: imagen
     */
    private File createAvatarFileFromCamera() throws IOException {
        String timeStamp = LocalDateUtils.formatDateForFileName(LocalDateTime.now());

        String prefix = "JPEG_" + timeStamp + "_";
        File directory = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                    prefix,
                    ".jpg",
                    directory);

        return image;
    }

    /**
     * PRO:
     *  . Muestra la foto en el widget
     *  . Procesar el resultado en el presentador pasandole la localidad de origen seleccionada
     *  . Procesa los eventos neutrales del reconocimiento de los diálogos
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case AddEditUserProfileActivity.REQUEST_PICK_LOCATION:
                if (resultCode == Activity.RESULT_OK) {
                    originLocalityPicked = data.getStringExtra(OriginLocalityPickerMapActivity.EXTRA_LOCATION);
                    presenter.manageOriginLocalityPickingResult(originLocalityPicked);
                }
                break;
            case AddEditUserProfileActivity.REQUEST_IMAGE_CAPTURE_FROM_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    presenter.manageAvatarFromCameraCaptureResult(getContext(), currentAvatarPath, avatarImage);
                }
                break;
            case AddEditUserProfileActivity.REQUEST_PICK_IMAGE_FROM_GALLERY:
                if (resultCode == Activity.RESULT_OK) {

                    if (data.getData() == null) {
                        Log.d(TAG, "Error: La imagen no pudo obtenerse");
                        return;
                    }

//                  Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
                    try {
                        InputStream inputStream = getContext().getContentResolver().openInputStream(data.getData());
                        File avatarFile = createAvatarFileFromGallery(inputStream);
                        currentAvatarPath = avatarFile.getAbsolutePath();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    presenter.manageAvatarFromGalleryPickingResult(getContext(), currentAvatarPath, avatarImage);

                }
                break;
            case AddEditUserProfileActivity.REQUEST_ACKNOWLEDGE_DNI_DIALOG_FRAGMENT:
                if (resultCode == Activity.RESULT_OK)
//                    No ejecuta acción alguna, pues se desea quedar en el fragmento con el estado actual y los valores en caché
                break;
            case AddEditUserProfileActivity.REQUEST_ACKNOWLEDGE_DNI_ID_DIALOG_FRAGMENT:
                if (resultCode == Activity.RESULT_OK)
                break;
            default:
                break;
        }
    }

    @Override
    public void showDni(int dni) {
//        Deshabilito el error del text input layout envolvente
        dniFieldWrapper.setErrorEnabled(false);
        dniField.setText(String.valueOf(dni));
    }

    @Override
    public void showDniId(long dniId) {
        dniIdFieldWrapper.setErrorEnabled(false);
        dniIdField.setText(String.valueOf(dniId));
    }

    @Override
    public void showName(String name) {
        nameFieldWrapper.setErrorEnabled(false);
        nameField.setText(name);
    }

    @Override
    public void showSurname(String surname) {
        surnameFieldWrapper.setErrorEnabled(false);
        surnameField.setText(surname);
    }

    @Override
    public void showFacebookUserName(String facebookUserName) {
        facebookUserFieldWrapper.setErrorEnabled(false);
        facebookUserField.setText(facebookUserName);
    }

    @Override
    public void showOriginLocality(String originLocality) {
        originLocalityFieldWrapper.setErrorEnabled(false);
        originLocalityField.setText(originLocality);
    }

    @Override
    public void showBirthdate(int year, int month, int dayOfMonth) {
        dayBirthdateField.setText(String.valueOf(dayOfMonth));
        monthBirthdateField.setText(String.valueOf(month));
        yearBirthdateField.setText(String.valueOf(year));
    }

    @Override
    public void showPhone(long phone) {
        phoneFieldWrapper.setErrorEnabled(false);
        phoneField.setText(String.valueOf(phone));
    }

    /**
     * PRE: Recibe solamente el caracter F o M
     * @param gender
     */
    @Override
    public void showGender(char gender) {
        genderFieldWrapper.setErrorEnabled(false);
        switch (gender) {
            case 'F':
                femaleRadioBtn.setChecked(true);
                maleRadioBtn.setChecked(false);
                break;
            case 'M':
                femaleRadioBtn.setChecked(false);
                maleRadioBtn.setChecked(true);
                break;
            default:
                Log.d(TAG, "Caracter de género inválido");
                break;
        }
    }

    /**
     * OBS: View.GONE es invisible y no ocupa espacios para fines de diseño
     * Fuente:
     *  . https://stackoverflow.com/a/47807985/5279996
     */
    @Override
    public void onRemoveProfileAvatarClick() {
        currentAvatarPath = null;
        showAvatarImage(View.GONE);
        showRemoveAvatarButton(View.INVISIBLE);
    }


    @Override
    public void showContentAvatarView(@NonNull String avatarPath) {
        Glide.with(getContext())
                .load(avatarPath)
                .apply(RequestOptions.centerCropTransform())
                .into(avatarImage);
        showAvatarImage(View.VISIBLE);
        showRemoveAvatarButton(View.VISIBLE);
    }

    @Override
    public void showAvatarImage(int property) {
        avatarImage.setVisibility(property);
    }

    @Override
    public void showRemoveAvatarButton(int property) {
        removeProfileAvatarButton.setVisibility(property);
    }


    /**
     * Asigno la instancia inyectada al miembro
     */
    @SuppressLint("RestrictedApi")
    @Override
    public void setPresenter(AddEditUserProfileMvp.Presenter presenter) {
        this.presenter = Preconditions.checkNotNull(presenter);
    }

    /**
     * PRE: La clase dada como argumento existe.
     */
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

}
