package hu.zana.mobilalkfejl_vernyomasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MeresBevitel extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText SYS_DATA;
    EditText DIA_DATA;
    EditText PULSE_DATA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meres_bevitel);
        SYS_DATA = findViewById(R.id.SYS_DATA);
        DIA_DATA = findViewById(R.id.DIA_DATA);
        PULSE_DATA = findViewById(R.id.PULSE_DATA);
    }

    public void MeresBevitele(View view){
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate= DateFor.format(date);
        String SYS_STR = SYS_DATA.getText().toString();
        String DIA_STR = DIA_DATA.getText().toString();
        String PULSE_STR = PULSE_DATA.getText().toString();
        MeresItem mi = new MeresItem(SYS_STR,DIA_STR,PULSE_STR,stringDate);

        db.collection("Items")
                .add(mi)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(MeresBevitel.this, "Sikeres adatbevitel!", Toast.LENGTH_LONG).show();
                    SYS_DATA.setText("");
                    DIA_DATA.setText("");
                    PULSE_DATA.setText("");
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(MeresBevitel.this, "Adatbevitel nem sikerült! (Hiba: "+ e.getMessage() +" )", Toast.LENGTH_LONG).show());
    }
}