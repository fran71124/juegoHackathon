package maws.fran.juegodecartas.identification;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import maws.fran.juegodecartas.R;
import maws.fran.juegodecartas.utils.DatabaseUtils;

public class Identification extends AppCompatActivity {

    private EditText mInputEmail;
    private EditText mInputPassword;
    private Button mButtonSignIn;
    private Button mButtonSignUp;
    private ProgressDialog mProgressDialog;
    private TextInputLayout mLayoutInputPassowrd;
    private TextInputLayout mLayoutInputEmail;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        initComponents();
        initToolbar();
        initProgressDialog();
        initButtons();

    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Identificación");
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Cargando...");
        mProgressDialog.setMessage("Intentando identificar...");
    }

    private void initButtons() {
        mButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkFields()) return;
                showProgressDialog();
                FirebaseAuth.getInstance().signInWithEmailAndPassword(mInputEmail.getText().toString(), mInputPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) DatabaseUtils.loadCardsStaticData();
                                dismissProgressDialog();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Identification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                dismissProgressDialog();
                            }
                        });
            }
        });
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkFields()) return;
                showProgressDialog();
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(mInputEmail.getText().toString(), mInputPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) DatabaseUtils.loadCardsStaticData();
                                dismissProgressDialog();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Identification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                dismissProgressDialog();
                            }
                        });

            }
        });
    }


    private boolean checkFields() {
        boolean allFieldsAreOk = true;

        if (mInputEmail.getText().toString().isEmpty()) {
            mLayoutInputEmail.setError("Este campo no puede estar vacío");
            allFieldsAreOk = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(mInputEmail.getText().toString()).matches()) {
            mLayoutInputEmail.setError("El email introducido no es correcto");
            allFieldsAreOk = false;
        } else mLayoutInputEmail.setError(null);

        if (mInputPassword.getText().toString().isEmpty()) {
            mLayoutInputPassowrd.setError("Este campo no puede estar vacío");
            allFieldsAreOk = false;
        } else mLayoutInputPassowrd.setError(null);

        return allFieldsAreOk;
    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null) mProgressDialog.dismiss();
    }

    private void showProgressDialog() {
        dismissProgressDialog();
        mProgressDialog.show();
    }

    private void initComponents() {
        mLayoutInputPassowrd = (TextInputLayout) findViewById(R.id.identification_layout_input_password);
        mLayoutInputEmail = (TextInputLayout) findViewById(R.id.identification_layout_input_email);
        mInputPassword = (EditText) findViewById(R.id.identification_input_password);
        mButtonSignIn = (Button) findViewById(R.id.identification_button_sign_in);
        mButtonSignUp = (Button) findViewById(R.id.identification_button_sign_up);
        mInputEmail = (EditText) findViewById(R.id.identification_input_email);
        mToolbar = (Toolbar) findViewById(R.id.identification_toolbar);
    }
}
