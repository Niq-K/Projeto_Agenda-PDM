package com.example.trabalho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText loginEdit, loginPass;
    private Button loginBtn;
    private TextView loginQn;
    private FirebaseAuth mAuth;
    private ProgressDialog loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.loginToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
        mAuth = FirebaseAuth.getInstance();

        loader = new ProgressDialog(this);

        loginEdit = findViewById(R.id.loginEmail);
        loginPass = findViewById(R.id.loginSenha);
        loginBtn = findViewById(R.id.loginButton);
        loginQn = findViewById(R.id.loginPageQuestion);

        loginQn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEdit.getText().toString().trim();
                String password = loginPass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    loginEdit.setError("Obrigatório Email");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    loginPass.setError("Senha Obrigatória");
                    return;
                } else {
                    loader.setMessage("Login em progresso");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                                loader.dismiss();
                            } else {
                                String error = task.getException().toString();
                                Toast.makeText(LoginActivity.this, "Falha ao realizar Login" + error, Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });
                }
            }
        });
    }
}