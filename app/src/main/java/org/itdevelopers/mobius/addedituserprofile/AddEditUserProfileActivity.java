package org.itdevelopers.mobius.addedituserprofile;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

import org.itdevelopers.mobius.R;
import org.itdevelopers.mobius.addedituser.AddEditUserActivity;
import org.itdevelopers.mobius.addedituserprofile.presentation.AddEditUserProfileFragment;
import org.itdevelopers.mobius.addedituserprofile.presentation.AddEditUserProfilePresenter;
import org.itdevelopers.mobius.di.DependencyProvider;
import org.itdevelopers.mobius.users.domain.entities.UserProfile;
import org.itdevelopers.mobius.util.DiscardChangesDialog;

public class AddEditUserProfileActivity extends AppCompatActivity implements
        DiscardChangesDialog.DiscardChangesDialogListener {

    //    Identifica la petición hacia el contexto (fragmento) del mapa
    public final static int REQUEST_PICK_LOCATION = 1;

    //    Sirve p/reconocer cuando el usuario vuelve de la app galería o cámara
    public static final int REQUEST_PICK_IMAGE_FROM_GALLERY = 2;
    public static final int REQUEST_IMAGE_CAPTURE_FROM_CAMERA = 3;
    public static final int REQUEST_ACKNOWLEDGE_DNI_DIALOG_FRAGMENT = 4;
    public static final int REQUEST_ACKNOWLEDGE_DNI_ID_DIALOG_FRAGMENT = 5;


    private AddEditUserProfileFragment view;

    /**
     * PRO: Añade presentación
     * OBS:
     * . Creando las instancias del fragmento y el presentador se le dá funcionalidad total a la característica.
     * . El perfil de usuario que se fuese a editar se lo obtiene como extra.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_user_profile);
        setUpToolbar();

//        Providing up navigation
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        runAddEditUserProfileFragment();

//        Permite cambiar el título de la actividad e/edición o creación
        UserProfile userProfile = getIntent().getParcelableExtra(AddEditUserActivity.EXTRA_USER_PROFILE);

        setToolbarTitle(userProfile);

        AddEditUserProfilePresenter presenter = new AddEditUserProfilePresenter(
                userProfile,
                view,
                DependencyProvider.provideCacheUserProfileStore(),
                getResources()
        );
        view.setPresenter(presenter);
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_user_profile);
        setSupportActionBar(toolbar);
    }

    private void runAddEditUserProfileFragment() {
        view = (AddEditUserProfileFragment) getSupportFragmentManager()
                .findFragmentById(R.id.add_edit_user_profile_container);
        if (view == null) {
            view = AddEditUserProfileFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_edit_user_profile_container, view)
                    .commit();
        }
    }

    /**
     * PRO: Cambia el título de la Toolbar de la Actividad
     * PRE: El valor del título es Añadir o Editar Perfil
     */
    private void setToolbarTitle(UserProfile userProfile) {
        if (userProfile == null)
            setTitle(R.string.add_user_profile);
        else
            setTitle(R.string.edit_user_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_user_profile_menu, menu);
        return true;
    }

    /**
     * PRO: Maneja eventos de Up Button: genera el mismo comportamiento que el Back Button
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * PRO: Maneja eventos de Back Button: lanza un diálogo
     * PRE: El diálogo esta listo y el contexto implementa a la interfaz de DiscardChangesDialog
     */
    @Override
    public void onBackPressed() {
        DiscardChangesDialog dialog = new DiscardChangesDialog();
        dialog.show(getSupportFragmentManager(), "DiscardChangesDialog");
    }

    /**
     * PRO: Cierra la actividad.
     * PRE: El contexto tiene que heredar de una actividad.
     * OBS: Controlador del evento positivo
     */
    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment) {
        finish();
    }

    /**
     * PRO: No ejecuta acción alguna, pues se desea quedar en la actividad con el estado actual
     * OBS: Controlador del evento negativo
     */
    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {
    }

}
