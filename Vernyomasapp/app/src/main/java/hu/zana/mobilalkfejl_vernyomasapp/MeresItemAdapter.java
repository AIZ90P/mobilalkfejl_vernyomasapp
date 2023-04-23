package hu.zana.mobilalkfejl_vernyomasapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MeresItemAdapter extends RecyclerView.Adapter<MeresItemAdapter.VH>{

    private ArrayList<MeresItem> meresData;
    private ArrayList<MeresItem> meresItemDataAll;
    private int SECRET_KEY = 424;
    private Context Context_;
    private int LastPos = -1;

    public MeresItemAdapter(Context context, ArrayList<MeresItem> meresData) {
        this.meresData = meresData;
        this.meresItemDataAll = meresData;
        this.Context_ = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(Context_).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        MeresItem current = meresData.get(position);
        holder.bindTo(current);
        holder.MOD_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String DOCID = holder.ROWID;
                Intent intent = new Intent(v.getContext(), MeresModositas.class);
                intent.putExtra("SECRET_KEY", SECRET_KEY);
                intent.putExtra("DOCID",DOCID);
                v.getContext().startActivity(intent);
            }
        });

        holder.DEL_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String DOCID = holder.ROWID;
                Intent intent = new Intent(v.getContext(), MeresTorles.class);
                intent.putExtra("SECRET_KEY", SECRET_KEY);
                intent.putExtra("DOCID",DOCID);
                intent.putExtra("SYS",holder.SYSTOLE.getText());
                intent.putExtra("DIA",holder.DIASTOLE.getText());
                intent.putExtra("PULSE",holder.PULSE.getText());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {return meresData.size();}

    class VH extends RecyclerView.ViewHolder{
        private TextView SYSTOLE;
        private TextView DIASTOLE;
        private TextView PULSE;
        private TextView DATUM;
        private Button MOD_BTN;
        private Button DEL_BTN;
        private String ROWID;
        public VH(@NonNull View itemView) {
            super(itemView);

            SYSTOLE = itemView.findViewById(R.id.SYSTOLE_DATA);
            DIASTOLE = itemView.findViewById(R.id.DIASTOLE_DATA);
            PULSE = itemView.findViewById(R.id.PULSE_DATA);
            DATUM = itemView.findViewById(R.id.datum_label);
            MOD_BTN = itemView.findViewById(R.id.item_modositas);
            DEL_BTN = itemView.findViewById(R.id.item_torles);
        }

        public void bindTo(MeresItem current) {
            SYSTOLE.setText(current.getSys());
            DIASTOLE.setText(current.getDia());
            PULSE.setText(current.getPulse());
            DATUM.setText(current.getDate());
            ROWID = current.getDocid();
        }
    }
}
