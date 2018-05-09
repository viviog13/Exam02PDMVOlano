package com.example.alumfial1.exam02_pdm_volano;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText email, pass;
    private Button btnsignin;
    private TextView registrate;

    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        email = findViewById(R.id.ed_email);
        pass = findViewById(R.id.ed_password);
        btnsignin = findViewById(R.id.btn_signin);
        registrate = findViewById(R.id.tvregistrate);

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails = email.getText().toString();
                String passwords = pass.getText().toString();
                SignIn(emails, passwords);
                Intent intent = new Intent(MainActivity.this, Principal.class);
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

        registrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent (MainActivity.this, RegistrarVendedor.class);
                startActivity(intent1);
            }
        });

    }
    private void SignIn(String emails, String passwords) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(emails, passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Sesion iniciada", Toast.LENGTH_SHORT).show();
                } else {
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }

}
