package org.itdevelopers.mobius.addedituseraccount;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

import org.itdevelopers.mobius.R;
import org.itdevelopers.mobius.addedituser.AddEditUserActivity;
import org.itdevelopers.mobius.addedituseraccount.presentation.AddEditUserAccountFragment;
import org.itdevelopers.mobius.addedituseraccount.presentation.AddEditUserAccountPresenter;
import org.itdevelopers.mobius.di.DependencyProvider;
import org.itdevelopers.mobius.users.domain.entities.UserAccount;

/**
 * 23/1/2019
 */
public class AddEditUserAccountActivity extends AppCompatActivity {

    private AddEditUserAccountFragment view;

    /**
     * PRO: Añade presentación
     * OBS:
     *  . Creando las instancias del fragmento y el presentador se le dá funcionalidad total a la característica.
     *  . La cuenta de usuario que se fuese a editar se lo obtiene como extra.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_user_account);

        setUpToolbar();

//        Providing up navigation
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        runAddEditUserAccountFragment();

//        Permite cambiar el título de la actividad e/edición o creación
        UserAccount userAccount = getIntent().getParcelableExtra(AddEditUserActivity.EXTRA_USER_ACCOUNT);

        setToolbarTitle(userAccount);

        AddEditUserAccountPresenter presenter = new AddEditUserAccountPresenter(
                userAccount,
                view,
                DependencyProvider.provideCacheUserAccountStore(),
                getResources()
        );
        view.setPresenter(presenter);
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_user_account);
        setSupportActionBar(toolbar);
    }


    private void runAddEditUserAccountFragment() {
        view = (AddEditUserAccountFragment) getSupportFragmentManager()
                .findFragmentById(R.id.add_edit_user_account_container);
        if (view == null) {
            view = AddEditUserAccountFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_edit_user_account_container, view)
                    .commit();
        }
    }

    /**
     * PRO: Cambia el título de la Toolbar de la Actividad
     * PRE: El valor del título es Añadir o Editar Cuenta
     */
    private void setToolbarTitle(UserAccount userAccount) {
        if (userAccount == null)
            setTitle(R.string.add_user_account);
        else
            setTitle(R.string.edit_user_account);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_user_account_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
