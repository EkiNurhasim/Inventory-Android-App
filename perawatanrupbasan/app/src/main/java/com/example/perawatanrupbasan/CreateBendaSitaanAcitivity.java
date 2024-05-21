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
import com.example.perawatanrupbasan.model.bendasitaan.BendaSitaan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateBendaSitaanAcitivity extends AppCompatActivity {

    private EditText etNoRegis, etNamaBarang, etTersangka, etInstansiPenitip, etTanggalMasuk, etGudang, etJumlahBarang, etKondisiBarang;
    private Button btnSave;
    private String noRegis, namaBarang, tersangka, instansiPenitip, tanggalMasuk, gudang, jumlahBarang, kondisiBarang;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_benda_sitaan_acitivity);

        etNoRegis = findViewById(R.id.etNoRegis);
        etNamaBarang = findViewById(R.id.etNamaBarang);
        etTersangka = findViewById(R.id.etTersangka);
        etInstansiPenitip = findViewById(R.id.etInstansiPenitip);
        etTanggalMasuk = findViewById(R.id.etTanggalMasuk);
        etGudang = findViewById(R.id.etGudang);
        etJumlahBarang = findViewById(R.id.etJumlahBarang);
        etKondisiBarang = findViewById(R.id.etKondisiBarang);
        btnSave = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        userId = intent.getStringExtra("USER_ID");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noRegis = etNoRegis.getText().toString();
                namaBarang =  etNamaBarang.getText().toString();
                tersangka = etTersangka.getText().toString();
                instansiPenitip = etInstansiPenitip.getText().toString();
                tanggalMasuk = etTanggalMasuk.getText().toString();
                gudang = etGudang.getText().toString();
                jumlahBarang = etJumlahBarang.getText().toString();
                kondisiBarang = etKondisiBarang.getText().toString();

                if(noRegis.trim().equals("")){
                    etNoRegis.setError("No Registrasi is Empty");
                }else if(namaBarang.trim().equals("")){
                    etNamaBarang.setError("Nama Barang is Empty");
                }else if(tersangka.trim().equals("")){
                    etTersangka.setError("Tersangka is Empty");
                }else if(instansiPenitip.trim().equals("")){
                    etInstansiPenitip.setError("Instansi Penitip is Empty");
                }else if(tanggalMasuk.trim().equals("")){
                    etTanggalMasuk.setError("Tanggal Masuk is Empty");
                }else if(gudang.trim().equals("")){
                    etGudang.setError("Gudang is Empty");
                }else if(jumlahBarang.trim().equals("")){
                    etJumlahBarang.setError("Jumlah Barang is Empty");
                }else if(kondisiBarang.trim().equals("")){
                    etKondisiBarang.setError("Kondisi Barang is Empty");
                }else{
                    createData();
                }
            }
        });
    }

    private void createData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<BendaSitaan> bendaSitaanCall = apiInterface.createBendaSitaanResponse(noRegis, namaBarang, tersangka, instansiPenitip, tanggalMasuk, gudang, userId, jumlahBarang, kondisiBarang);
        bendaSitaanCall.enqueue(new Callback<BendaSitaan>() {
            @Override
            public void onResponse(Call<BendaSitaan> call, Response<BendaSitaan> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(CreateBendaSitaanAcitivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(CreateBendaSitaanAcitivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BendaSitaan> call, Throwable t) {
                Toast.makeText(CreateBendaSitaanAcitivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
