package com.example.perawatanrupbasan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perawatanrupbasan.api.ApiClient;
import com.example.perawatanrupbasan.api.ApiInterface;
import com.example.perawatanrupbasan.model.bendasitaan.BendaSitaan;
import com.example.perawatanrupbasan.model.bendasitaan.BendaSitaanData;
import com.example.perawatanrupbasan.model.pemeliharaan.Pemeliharaan;
import com.example.perawatanrupbasan.model.pemeliharaan.PemeliharaanData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<PemeliharaanData> listData = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;
    private FloatingActionButton fabTambah;

    private String idBendaSitaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        idBendaSitaan = intent.getStringExtra("BENDA_SITAAN_ID").toString();

        rvData = findViewById(R.id.rv_dataPemeliharaan);
        srlData = findViewById(R.id.srl_dataPemeliharaan);
        pbData = findViewById(R.id.pb_dataPemeliharaan);
        fabTambah = findViewById(R.id.fab_tambahPemeliharaan);

        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

        retrieveData();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                retrieveData();
                srlData.setRefreshing(false);
            }
        });

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, CreatePemeliharaanActivity.class);
                intent.putExtra("ID_BENDA_SITAAN", idBendaSitaan);
                startActivity(intent);
            }
        });
    }

    public void retrieveData() {

        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Pemeliharaan> pemeliharaanCall = apiInterface.pemeliharaanResponse(idBendaSitaan);

            pemeliharaanCall.enqueue(new Callback<Pemeliharaan>() {
                @Override
                public void onResponse(Call<Pemeliharaan> call, Response<Pemeliharaan> response) {
                    if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                        listData = response.body().getData();
                        adData = new AdapterDataPemeliharaan(MainActivity2.this, listData);
                        rvData.setAdapter(adData);
                        adData.notifyDataSetChanged();
                        pbData.setVisibility(View.INVISIBLE);
                    }else{
                        Toast.makeText(MainActivity2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Pemeliharaan> call, Throwable t) {
                    pbData.setVisibility(View.INVISIBLE);
                }
            });

        }catch (Exception e){
            Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}
