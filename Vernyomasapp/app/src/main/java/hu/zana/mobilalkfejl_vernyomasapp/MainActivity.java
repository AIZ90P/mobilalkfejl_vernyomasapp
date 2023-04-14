package hu.zana.mobilalkfejl_vernyomasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText emailET;
    EditText passwordET;

    private static final int SECRET_KEY = 424;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailET = findViewById(R.id.email_input);
        passwordET = findViewById(R.id.password_input);
        mAuth = FirebaseAuth.getInstance();
    }

    public void appLogin(View view){
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Siker
                    startNaplo();
                }
                else{
                    //Sikertelen
                    Toast.makeText(MainActivity.this, "Nem sikerült -> "+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void appRegister(View view){
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Siker
                    startNaplo();
                }
                else{
                    //Sikertelen
                    Toast.makeText(MainActivity.this, "Nem sikerült -> "+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void startNaplo(){
        Intent intent = new Intent(this, VernyomasnaploLista.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }
}