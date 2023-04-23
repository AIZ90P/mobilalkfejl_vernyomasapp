package hu.zana.mobilalkfejl_vernyomasapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MeresTorles extends AppCompatActivity {

    String DOCID;

    TextView SYSTOLE;
    TextView DIASTOLE;
    TextView PULSE;
    CollectionReference cr;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meres_torles);
        Intent intent = getIntent();
        DOCID = intent.getStringExtra("DOCID");

        SYSTOLE = findViewById(R.id.SYS_DATA_DELETE);
        DIASTOLE = findViewById(R.id.DIA_DATA_DELETE);
        PULSE = findViewById(R.id.PULSE_DATA_DELETE);

        SYSTOLE.setText(intent.getStringExtra("SYS"));
        DIASTOLE.setText(intent.getStringExtra("DIA"));
        PULSE.setText(intent.getStringExtra("PULSE"));

        db = FirebaseFirestore.getInstance();
        cr = db.collection("Items");
    }

    public void MeresTorlese(View view){
        AlertDialog.Builder bldr = new AlertDialog.Builder(this);
        bldr.setMessage(R.string.MeresTorles_message).setTitle(R.string.MeresTorles_title);

        bldr.setPositiveButton(R.string.POSITIVE_ANS, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference cr = db.collection("Items");
                cr.document(DOCID).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MeresTorles.this,"Sikeres adattörlés!",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(MeresTorles.this,"Sikertelen adattörlés! Hiba: "+e.getMessage(),Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }
        });

        bldr.setNegativeButton(R.string.NEGATIVE_ANS, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MeresTorles.this,"Adattörlés megszakítva a felhasználó által!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        AlertDialog dialog = bldr.create();
        dialog.show();
    }

    /*


     */
}