package hu.zana.mobilalkfejl_vernyomasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VernyomasnaploLista extends AppCompatActivity {

    private FirebaseUser usr;
    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;

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
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1));

        mItemList = new ArrayList<>();
        mAdapter = new MeresItemAdapter(this,mItemList);

        mRecyclerView.setAdapter(mAdapter);

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Items");

        QueryData();
        //InitalizeData();

    }

    private void QueryData(){
        mItemList.clear();

        mItems.get().addOnSuccessListener(queryDocumentSnapshots ->  {
            for(QueryDocumentSnapshot doc : queryDocumentSnapshots){
                MeresItem item = doc.toObject(MeresItem.class);
                mItemList.add(item);
            }
            mAdapter.notifyDataSetChanged();
        });
    }

    private void InitalizeData(){
        String[] SYS = getResources().getStringArray(R.array.SYS);
        String[] DIA = getResources().getStringArray(R.array.DIA);
        String[] PULSE = getResources().getStringArray(R.array.PULSE);
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate= DateFor.format(date);
        
        //mItemList.clear();

        for (int i = 0; i < SYS.length; i++) {
            //mItems.add(new MeresItem(SYS[i],DIA[i],PULSE[i],stringDate));
            mItemList.add(new MeresItem(SYS[i],DIA[i],PULSE[i],stringDate));
        }

        //mAdapter.notifyDataSetChanged();
    }
}