package com.npu_app.npu_application.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.npu_app.npu_application.R;
import com.npu_app.npu_application.controller.DatabaseAccess;
import com.npu_app.npu_application.model.Login;
import com.npu_app.npu_application.shared_preferences.SharedPreferencesManager;

public class LoginActivity extends AppCompatActivity {

    EditText etUserName;
    EditText etPassword;
    DatabaseAccess databaseAccess;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        auth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(validate()){
                    String student_id = databaseAccess.checkCredentials(etUserName.getText().toString(), etPassword.getText().toString());
                    if(!student_id.equals("")) {
                        SharedPreferencesManager.setStudentID(LoginActivity.this, student_id);
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(), "Please check your credentials", Toast.LENGTH_SHORT).show();
                    }
                }*/

                if (TextUtils.isEmpty(etUserName.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())){
                    Toast.makeText(LoginActivity.this, "All fileds are required", Toast.LENGTH_SHORT).show();
                } else {

                    auth.signInWithEmailAndPassword(etUserName.getText().toString(), etPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        String student_id = databaseAccess.checkCredentials(etUserName.getText().toString(), etPassword.getText().toString());
                                        if(!student_id.equals("")) {
                                            SharedPreferencesManager.setStudentID(LoginActivity.this, student_id);
                                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(i);
                                        }else{
                                            Toast.makeText(getApplicationContext(), "Please check your credentials", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                }
            }
        });

        findViewById(R.id.txt_forget_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initViews() {
        etUserName = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_pass);
        databaseAccess = DatabaseAccess.getInstance(LoginActivity.this);
        databaseAccess.Open();
    }

    public boolean validate() {
        boolean valid = false;

        String Username = etUserName.getText().toString();
        String Password = etPassword.getText().toString();

        if(Username.isEmpty()) {
            valid = false;
            etUserName.setError("Please enter valid Username!");
        }else {
            valid = true;
            etUserName.setError(null);
        }

        if (Password.isEmpty()) {
            valid = false;
            etPassword.setError("Please enter valid Password!");
        } else if (Password.length() < 4) {
            valid = false;
            etPassword.setError("Password is to short!");
        } else {
            valid = true;
        }
        return valid;
    }
}