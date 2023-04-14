package hu.zana.mobilalkfejl_vernyomasapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MeresItemAdapter extends RecyclerView.Adapter<MeresItemAdapter.VH>{

    private ArrayList<MeresItem> meresData;
    private ArrayList<MeresItem> meresItemDataAll;
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
    }

    @Override
    public int getItemCount() {return meresData.size();}

    class VH extends RecyclerView.ViewHolder{
        private TextView SYSTOLE;
        private TextView DIASTOLE;
        private TextView PULSE;
        public VH(@NonNull View itemView) {
            super(itemView);

            SYSTOLE = itemView.findViewById(R.id.SYSTOLE_DATA);
            DIASTOLE = itemView.findViewById(R.id.DIASTOLE_DATA);
            PULSE = itemView.findViewById(R.id.PULSE_DATA);
        }

        public void bindTo(MeresItem current) {
            SYSTOLE.setText(current.getSys());
            DIASTOLE.setText(current.getDia());
            PULSE.setText(current.getPulse());

        }
    }
}
