package com.example.perawatanrupbasan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.perawatanrupbasan.api.ApiClient;
import com.example.perawatanrupbasan.api.ApiInterface;
import com.example.perawatanrupbasan.model.pemeliharaan.Pemeliharaan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePemeliharaanActivity extends AppCompatActivity {

    private EditText etPelaksana, etKegiatan, etAlatBahan, etNamaPetugas, etEksternal;
    private Button btnSavePemeliharaan;
    private String pelaksana, kegiatan, alatBahan, namaPetugas, eksternal;
    String idBendaSitaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pemeliharaan);

        etPelaksana = findViewById(R.id.etPelaksana);
        etKegiatan = findViewById(R.id.etKegiatan);
        etAlatBahan = findViewById(R.id.etAlatBahan);
        etNamaPetugas = findViewById(R.id.etNamaPetugas);
        etEksternal = findViewById(R.id.etEksternal);
        btnSavePemeliharaan = findViewById(R.id.btnSavePerawatan);

        Intent intent = getIntent();
        idBendaSitaan = intent.getStringExtra("ID_BENDA_SITAAN").toString();

        btnSavePemeliharaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pelaksana = etPelaksana.getText().toString();
                kegiatan =  etKegiatan.getText().toString();
                alatBahan = etAlatBahan.getText().toString();
                namaPetugas = etNamaPetugas.getText().toString();
                eksternal = etEksternal.getText().toString();

                if(pelaksana.trim().equals("")){
                    etPelaksana.setError("Pelaksana is Empty");
                }else if(kegiatan.trim().equals("")){
                    etKegiatan.setError("Kegiatan is Empty");
                }else if(alatBahan.trim().equals("")){
                    etAlatBahan.setError("Alat dan Bahan is Empty");
                }else if(namaPetugas.trim().equals("")){
                    etNamaPetugas.setError("Nama Petugas Pengawas");
                } else if(eksternal.trim().equals("")){
                    etEksternal.setError("Nama Petugas Eksternal");
                } else{
                    createData();
                }
            }
        });
    }

    private void createData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Pemeliharaan> pemeliharaanCall = apiInterface.createPemeliharaanResponse(pelaksana, kegiatan, alatBahan, namaPetugas, idBendaSitaan, eksternal);
        pemeliharaanCall.enqueue(new Callback<Pemeliharaan>() {
            @Override
            public void onResponse(Call<Pemeliharaan> call, Response<Pemeliharaan> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(CreatePemeliharaanActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(CreatePemeliharaanActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }
            }

            @Override
            public void onFailure(Call<Pemeliharaan> call, Throwable t) {
                Toast.makeText(CreatePemeliharaanActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
