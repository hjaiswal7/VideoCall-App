package com.example.vcall;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore database;
    EditText emailBox, passwordBox, nameBox;
    Button loginBtn, signupBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        emailBox = findViewById(R.id.email);
        nameBox = findViewById(R.id.nameBox);
        passwordBox = findViewById(R.id.password);

        loginBtn = findViewById(R.id.loginbox);
        signupBtn = findViewById(R.id.createbox);

        signupBtn.setOnClickListener(v -> {
            String email, password ,name;
            email = emailBox.getText().toString();
            password = passwordBox.getText().toString();
            name = nameBox.getText().toString();

            final User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       database.collection("Users")
                               .document().set(user).addOnSuccessListener(aVoid -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));
                       Toast.makeText(SignupActivity.this, "Account is Created", Toast.LENGTH_SHORT).show();
                   } else {
                       Toast.makeText(SignupActivity.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                   }
                }
            });
        });

    }
}