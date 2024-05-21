package com.example.perawatanrupbasan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perawatanrupbasan.api.ApiClient;
import com.example.perawatanrupbasan.api.ApiInterface;
import com.example.perawatanrupbasan.model.bendasitaan.BendaSitaan;
import com.example.perawatanrupbasan.model.pemeliharaan.Pemeliharaan;
import com.example.perawatanrupbasan.model.pemeliharaan.PemeliharaanData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDataPemeliharaan extends RecyclerView.Adapter<AdapterDataPemeliharaan.HolderData> {

    private Context ctx;
    private List<PemeliharaanData> listData;
    private String idPemeliharaan;

    public AdapterDataPemeliharaan(Context ctx, List<PemeliharaanData> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public AdapterDataPemeliharaan.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_pemeliharaan, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPemeliharaan.HolderData holder, int position) {
        PemeliharaanData dm = listData.get(position);

        holder.tvIdPemeliharaan.setText(dm.getId());
        holder.tvTanggal.setText( ": " + dm.getTanggal());
        holder.tvPelaksana.setText( ": " + dm.getPelaksana());
        holder.tvKegiatan.setText( ": " + dm.getKegiatan());
        holder.tvAlatBahan.setText( ": " + dm.getAlatBahan());
        holder.tvNamaPetugas.setText( ": " + dm.getNamaPetugas());
        holder.tvBendaSitaanId.setText( ": " + dm.getBendasitaanId());
        holder.tvEksternal.setText(": " + dm.getEksternal());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvIdPemeliharaan, tvTanggal, tvPelaksana, tvKegiatan, tvAlatBahan, tvNamaPetugas, tvBendaSitaanId, tvEksternal;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvIdPemeliharaan = itemView.findViewById(R.id.tvIdPemeliharaan);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvPelaksana = itemView.findViewById(R.id.tvPelaksana);
            tvKegiatan = itemView.findViewById(R.id.tvKegiatan);
            tvAlatBahan = itemView.findViewById(R.id.tvAlatBahan);
            tvNamaPetugas = itemView.findViewById(R.id.tvNamaPetugas);
            tvBendaSitaanId = itemView.findViewById(R.id.tvBendaSitaanId);
            tvEksternal = itemView.findViewById(R.id.tvEksternal);


            itemView.findViewById(R.id.btnDeletePemeliharaan).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogPesan  = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Are you sure want to delete this record ?");

                    idPemeliharaan = tvIdPemeliharaan.getText().toString();

                    dialogPesan.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteData();
                            dialog.dismiss();
                            ((MainActivity2) ctx).retrieveData();
                        }
                    });

                    dialogPesan.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    dialogPesan.show();
                }
            });
        }

        private void deleteData(){
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Pemeliharaan> bendaSitaanCall = apiInterface.deletePemeliharaanResponse(idPemeliharaan);

            bendaSitaanCall.enqueue(new Callback<Pemeliharaan>() {
                @Override
                public void onResponse(Call<Pemeliharaan> call, Response<Pemeliharaan> response) {
                    if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                        Toast.makeText(ctx, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ctx, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Pemeliharaan> call, Throwable t) {
                    Toast.makeText(ctx, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
