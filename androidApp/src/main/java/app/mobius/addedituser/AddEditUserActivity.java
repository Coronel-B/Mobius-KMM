package app.mobius.addedituser;

import android.os.Bundle;
import android.view.Menu;

import app.mobius.R;
import app.mobius.addedituser.presentation.AddEditUserFragment;
import app.mobius.addedituser.presentation.AddEditUserPresenter;
import app.mobius.di.DependencyProvider;
import app.mobius.util.DiscardChangesDialog;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

/**
 * PRO: Actua como controlador general de la funcionalidad del Sign Up
 * OBS: Es el punto de entrada de los componentes que gestionan el flujo
 */
public class AddEditUserActivity extends AppCompatActivity implements
        DiscardChangesDialog.DiscardChangesDialogListener {

    public static final int REQUEST_ADD_USER_ACCOUNT = 1;
    public static final int REQUEST_ADD_USER_PROFILE = 2;
    public static final int REQUEST_EDIT_USER_ACCOUNT = 3;
    public static final int REQUEST_EDIT_USER_PROFILE = 4;
    public static final int REQUEST_EDIT_USER_ACCOUNT_ERROR_EMAIL = 5;

    public static final String EXTRA_USER_SAVING_MESSAGE = "app.mobius.EXTRA_USER_SAVING_MESSAGE";
    public static final String EXTRA_USER_ACCOUNT = "app.mobius.EXTRA_USER_ACCOUNT";
    public static final String EXTRA_USER_PROFILE = "app.mobius.EXTRA_USER_PROFILE";
    public static final String EXTRA_USER_ACCOUNT_ERROR_EMAIL = "app.mobius.EXTRA_USER_ACCOUNT_ERROR_EMAIL";

    /**
     * OBS: Dado que el presentador se relaciona con la vista, el fragmento es un buen contexto para
     * crearlo.
     * Obtener el repositorio desde el proveedor externo elimina la responsabilidad del presentador
     * de saber como crearlo; de esta forma se inyecta la dependencia ensamblando los componentes.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_user);

        Toolbar toolbar = findViewById(R.id.toolbar_user);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

//        Transacción del fragmento
        AddEditUserFragment view = (AddEditUserFragment) getSupportFragmentManager()
                .findFragmentById(R.id.add_edit_user_container);
        if (view == null) {
            view = AddEditUserFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_edit_user_container, view)
                    .commit();
        }

        /*
        * Obtengo el ID del usuario que se fuese a editar desde la pantalla de detalle.
        * El valor será null pues en este #1 no se cubrió el detalle de usuario, por lo que no habrá
        * un identificador del extra.
        * */
        String userId = getIntent().getStringExtra("");

        setToolbarTitle(userId);

//        Establecer dependencias entre vista-presentador
        AddEditUserPresenter presenter = new AddEditUserPresenter(
                view,
                DependencyProvider.provideSaveUser(this),
                DependencyProvider.provideCacheUserProfileStore(),
                DependencyProvider.provideCacheUserAccountStore(),
                getResources()
        );
        view.setPresenter(presenter);

//        Ahora se pueden probar los eventos de UI de la app vs los puntos de interacción-reacción del boceto
    }

    /**
     * PRO: Cambia el título de la Toolbar
     * PRE: El valor del argumento es Añadir o Editar Usuario
     * OBS: @param userId
     */
    private void setToolbarTitle(String userId) {
        if (userId == null)
            setTitle(R.string.add_user);
        else
            setTitle(R.string.edit_user);
    }

    /**
     * PRO: Infla las opciones de Menu p/que se muestre en la screen de la actividad
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_user_menu, menu);
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
     * PRO: Borra la caché de perfil y cuenta; y cierra la actividad.
     * PRE: El contexto tiene que heredar de una actividad.
     * OBS: Controlador del evento positivo
     */
    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment) {
        resetUserProfileCache();
        resetUserAccountCache();
        finish();
    }

    /**
     * PRO: Limpia la caché del perfil de usuario
     */
    private void resetUserProfileCache() {
        DependencyProvider.provideCacheUserProfileStore().deleteUserProfile();
    }

    /**
     * PRO: Limpia la caché de la cuenta de usuario
     */
    private void resetUserAccountCache() {
        DependencyProvider.provideCacheUserAccountStore().deleteUserAccount();
    }

    /**
     * PRO: No ejecuta acción alguna, pues se desea quedar en la actividad con el estado actual
     * y los valores en caché.
     * OBS: Controlador del evento negativo
     */
    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {}

}
