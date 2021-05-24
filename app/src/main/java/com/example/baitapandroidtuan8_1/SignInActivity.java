package com.example.baitapandroidtuan8_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtRegisterInSignin;
    private ImageButton btnSign;
    private EditText tvEmail, tvPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        auth = FirebaseAuth.getInstance();

        txtRegisterInSignin = findViewById(R.id.txtRegisterInSignin);
        btnSign = findViewById(R.id.btnSignin_Signin);
        tvEmail = findViewById(R.id.txtEmail);
        tvPassword = findViewById(R.id.txtPassword);

        txtRegisterInSignin.setOnClickListener(this);
        btnSign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtRegisterInSignin: {
                Intent intent = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnSignin_Signin: {
                String email = tvEmail.getText().toString().trim();
                String password = tvPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignInActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        tvPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(SignInActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(SignInActivity.this, DoneActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        }
    }
}