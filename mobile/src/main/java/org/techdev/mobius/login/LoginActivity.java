package org.techdev.mobius.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.techdev.mobius.R;
import org.techdev.mobius.addedituser.AddEditUserActivity;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_USER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView signUp = findViewById(R.id.sign_up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(AddEditUserActivity.class, REQUEST_ADD_USER);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ADD_USER: {
                if (resultCode == RESULT_OK) {
                    String message = getIntent().getStringExtra(AddEditUserActivity.EXTRA_USER_SAVING_MESSAGE);
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                }
            }
            break;
        }
    }

    /**
     * PRE: La clase dada como argumento existe.
     * @param cls: Clase a abrir
     * @param requestCode: Con una determinada acción
     * Fuente: https://stackoverflow.com/a/1124988/5279996
     */
    public void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }



}
