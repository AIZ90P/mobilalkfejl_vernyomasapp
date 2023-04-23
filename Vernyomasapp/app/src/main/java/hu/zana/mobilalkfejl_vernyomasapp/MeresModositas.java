package hu.zana.mobilalkfejl_vernyomasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MeresModositas extends AppCompatActivity {

    private FirebaseUser usr;
    private FirebaseFirestore mFirestore;

    TextView SYSTOLE;
    TextView DIASTOLE;
    TextView PULSE;

    MeresItem mi;
    CollectionReference cr;
    FirebaseFirestore db;
    String DOCID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meres_modositas);

        Intent intent = getIntent();
        DOCID = intent.getStringExtra("DOCID");

        SYSTOLE = findViewById(R.id.SYS_DATA);
        DIASTOLE = findViewById(R.id.DIA_DATA);
        PULSE = findViewById(R.id.PULSE_DATA);

        db = FirebaseFirestore.getInstance();
        cr = db.collection("Items");
        cr.whereEqualTo(FieldPath.documentId(),DOCID).limit(1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        mi = document.toObject(MeresItem.class);
                        SYSTOLE.setText(mi.getSys());
                        DIASTOLE.setText(mi.getDia());
                        PULSE.setText(mi.getPulse());
                    }
                }
            }
        });
    }


    public void MeresModositasa(View view){
        if(mi.getSys().equals(SYSTOLE.getText()) && mi.getDia().equals(DIASTOLE.getText()) && mi.getPulse().equals(PULSE.getText())){
            finish();
        }

        Map<String,Object> updates = new HashMap<>();
        updates.put("dia",DIASTOLE);
        updates.put("pulse",PULSE);
        updates.put("sys",SYSTOLE);

        cr.document(DOCID).update("sys",SYSTOLE.getText().toString(),"dia",DIASTOLE.getText().toString(),"pulse",PULSE.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MeresModositas.this, "Sikeres adatmódosítás!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}