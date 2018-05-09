package com.example.alumfial1.exam02_pdm_volano;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarVendedor extends AppCompatActivity {
    private EditText email, password, name, lastname;
    private Button btnregitrar;
    FirebaseAuth firebaseAuth;

    FirebaseAuth.AuthStateListener mAuthListener;


    DatabaseReference databaseVendedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_vendedor);
        getSupportActionBar().hide();

        email = findViewById(R.id.ed_emailR);
        password = findViewById(R.id.ed_passwordR);
        name = findViewById(R.id.ed_nameR);
        lastname = findViewById(R.id.ed_lastnameR);
        btnregitrar = findViewById(R.id.btn_addVend);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseVendedor = FirebaseDatabase.getInstance().getReference("Vendedor");

        btnregitrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailR = email.getText().toString();
                String passwordR = password.getText().toString();

                CreateEmailAndPass(emailR, passwordR);
                addVendedor();
                Intent intent = new Intent(RegistrarVendedor.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Log.i("SESION", "SESION INICIADA");
                }
            }
        };
    }

    public void addVendedor() {
        String correo = email.getText().toString().trim();
        String contra = password.getText().toString().trim();
        String nombre = name.getText().toString().trim();
        String apellido = lastname.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(correo) && !TextUtils.isEmpty(contra)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseVendedor.push().getKey();

            //creating an Artist Object
            Vendedor vend = new Vendedor(id, correo, contra, nombre, apellido);

            //Saving the Artist
            databaseVendedor.child(id).setValue(vend);

            //setting edittext to blank again
            email.setText("");
            password.setText("");
            name.setText("");
            lastname.setText("");

            //displaying a success toast
            Toast.makeText(this, "Vendedor agregado", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Ingrese su correo", Toast.LENGTH_LONG).show();
        }

    }

    private void CreateEmailAndPass(String email, String password) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegistrarVendedor.this, "Agregado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(mAuthListener);
    }


}



