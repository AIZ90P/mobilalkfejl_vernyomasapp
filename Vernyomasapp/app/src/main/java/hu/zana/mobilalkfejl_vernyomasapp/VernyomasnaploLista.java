package hu.zana.mobilalkfejl_vernyomasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class VernyomasnaploLista extends AppCompatActivity {

    private FirebaseUser usr;

    private RecyclerView mRecyclerView;
    private ArrayList<MeresItem> mItemList;
    private MeresItemAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vernyomasnaplo_lista);

        usr = FirebaseAuth.getInstance().getCurrentUser();
        if(usr == null){
            finish();
        }
    }
}