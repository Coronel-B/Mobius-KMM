package org.techdev.mobius.addedituserprofile.presentation;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.techdev.mobius.R;

public class OriginLocalityPickerMapActivity extends AppCompatActivity {

    /*
     * Las siguientes ctes son para la acción de selección y el extra que será devuelto (ubicación).
     * OBS: Estas ctes van en el contexto que lanza el fragmento del mapa*/
    public final static String ACTION_PICK_LOCATION =
            "org.techdev.action.ACTION_PICK_LOCATION";
    public final static String EXTRA_LOCATION =
            "org.techdev.EXTRA_LOCATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origin_locality_picker_map);

        Toolbar toolbar = findViewById(R.id.toolbar_origin_locality_picker_map);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        boolean actionPick = isActionPick();
        setTitle(actionPick ? R.string.select_origin_locality : R.string.text_field_origin_locality);

        OriginLocalityPickerMap view = (OriginLocalityPickerMap) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_origin_locality_picker_map_container);

        if (view == null) {
            view = OriginLocalityPickerMap.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_origin_locality_picker_map_container, view)
                    .commit();
        }
    }

    private boolean isActionPick() {
        return ACTION_PICK_LOCATION.equals(getIntent().getAction());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
