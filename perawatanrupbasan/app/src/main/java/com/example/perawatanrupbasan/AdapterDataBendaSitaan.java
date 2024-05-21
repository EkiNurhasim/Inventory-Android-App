package com.example.perawatanrupbasan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.perawatanrupbasan.model.bendasitaan.BendaSitaanData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDataBendaSitaan extends RecyclerView.Adapter<AdapterDataBendaSitaan.HolderData> {

    private Context ctx;
    private List<BendaSitaanData> listData;
    private String idBendaSitaan;

    public AdapterDataBendaSitaan(Context ctx, List<BendaSitaanData> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public AdapterDataBendaSitaan.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_benda_sitaan, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataBendaSitaan.HolderData holder, int position) {
        BendaSitaanData dm = listData.get(position);

        holder.tvId.setText(dm.getId());
        holder.tvNoRegis.setText( ": " + dm.getNoRegis());
        holder.tvNamaBarang.setText( ": " + dm.getNamaBarang());
        holder.tvTersangka.setText( ": " + dm.getTersangka());
        holder.tvInstansiPenitip.setText( ": " + dm.getInstansiPenitip());
        holder.tvTanggalMasuk.setText( ": " + dm.getTanggalMasuk());
        holder.tvGudang.setText( ": " + dm.getGudang());
        holder.tvJumlahBarang.setText( ": " + dm.getJumlahBarang());
        holder.tvKondisiBarang.setText( ": " + dm.getKondisiBarang());
        holder.tvLoginRegisId.setText(String.valueOf(dm.getLoginregisId()));
        holder.tvCreatedAt.setText(dm.getCreatedAt());

//        holder.tvLoginRegisId.setText(String.valueOf(dm.getLoginregisId()));

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvNoRegis, tvNamaBarang, tvTersangka, tvInstansiPenitip, tvTanggalMasuk, tvGudang, tvLoginRegisId, tvCreatedAt, tvJumlahBarang, tvKondisiBarang;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvId);
            tvNoRegis = itemView.findViewById(R.id.tvNoRegis);
            tvNamaBarang = itemView.findViewById(R.id.tvNamaBarang);
            tvTersangka = itemView.findViewById(R.id.tvTersangka);
            tvInstansiPenitip = itemView.findViewById(R.id.tvInstansiPenitip);
            tvTanggalMasuk = itemView.findViewById(R.id.tvTanggalMasuk);
            tvGudang = itemView.findViewById(R.id.tvGudang);
            tvLoginRegisId = itemView.findViewById(R.id.tvLoginRegisId);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvJumlahBarang = itemView.findViewById(R.id.tvJumlahBarang);
            tvKondisiBarang = itemView.findViewById(R.id.tvKondisiBarang);

            itemView.findViewById(R.id.btnView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    idBendaSitaan = tvId.getText().toString();
                    Intent intent = new Intent(ctx, MainActivity2.class);
                    intent.putExtra("BENDA_SITAAN_ID", idBendaSitaan);
                    ctx.startActivity(intent);
                }
            });

            itemView.findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogPesan  = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Are you sure want to delete this record ?");

                    idBendaSitaan = tvId.getText().toString();

                    dialogPesan.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteData();
                            dialog.dismiss();
                            ((MainActivity) ctx).retrieveData();
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
            Call<BendaSitaan> bendaSitaanCall = apiInterface.deleteBendaSitaanResponse(idBendaSitaan);

            bendaSitaanCall.enqueue(new Callback<BendaSitaan>() {
                @Override
                public void onResponse(Call<BendaSitaan> call, Response<BendaSitaan> response) {
                    if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                        Toast.makeText(ctx, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ctx, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BendaSitaan> call, Throwable t) {
                    Toast.makeText(ctx, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
