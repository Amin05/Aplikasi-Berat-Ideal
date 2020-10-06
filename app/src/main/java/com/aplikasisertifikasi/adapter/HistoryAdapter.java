package com.aplikasisertifikasi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aplikasisertifikasi.R;
import com.aplikasisertifikasi.db.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ListViewHolder> {
    private ArrayList<History> daftarHistory;

    public HistoryAdapter(ArrayList<History> historyArrayList) {
        daftarHistory = historyArrayList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.tvTanggal.setText(daftarHistory.get(position).getTanggal());
        holder.tvNama.setText(daftarHistory.get(position).getNama());
        holder.tvJenisKelamin.setText(daftarHistory.get(position).getJenis_kelamin());
        holder.tvTinggi.setText(daftarHistory.get(position).getTinggi());
        holder.tvBerat.setText(daftarHistory.get(position).getBerat());
        holder.tvKeterangan.setText(daftarHistory.get(position).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return daftarHistory.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvTanggal, tvNama, tvJenisKelamin, tvTinggi, tvBerat, tvKeterangan;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvJenisKelamin = itemView.findViewById(R.id.tvJenisKelamin);
            tvTinggi = itemView.findViewById(R.id.tvTinggi);
            tvBerat = itemView.findViewById(R.id.tvBerat);
            tvKeterangan = itemView.findViewById(R.id.tvKeterangan);
        }
    }
}
