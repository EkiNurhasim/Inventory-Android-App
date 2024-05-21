package com.example.perawatanrupbasan.api;

import com.example.perawatanrupbasan.model.bendasitaan.BendaSitaan;
import com.example.perawatanrupbasan.model.login.Login;
import com.example.perawatanrupbasan.model.pemeliharaan.Pemeliharaan;
import com.example.perawatanrupbasan.model.register.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("retrieveBendaSitaan.php")
    Call<BendaSitaan> bendaSitaanResponse(
            @Field("loginregis_id") String loginregis_id
    );

    @FormUrlEncoded
    @POST("retrievePemeliharaan.php")
    Call<Pemeliharaan> pemeliharaanResponse(
            @Field("bendasitaan_id") String bendasitaan_id
    );

    @FormUrlEncoded
    @POST("createBendaSitaan.php")
    Call<BendaSitaan> createBendaSitaanResponse(
            @Field("no_regis") String noRegis,
            @Field("nama_barang") String namaBarang,
            @Field("tersangka") String tersangka,
            @Field("instansi_penitip") String instansiPenitip,
            @Field("tanggal_masuk") String tanggalMasuk,
            @Field("gudang") String gudang,
            @Field("loginregis_id") String loginregisId,
            @Field("jumlah_barang") String jumlah_barang,
            @Field("kondisi_barang") String kondisi_barang
    );

    @FormUrlEncoded
    @POST("createPemeliharaan.php")
    Call<Pemeliharaan> createPemeliharaanResponse(
            @Field("pelaksana") String pelaksana,
            @Field("kegiatan") String kegiatan,
            @Field("alat_bahan") String alat_bahan,
            @Field("nama_petugas") String nama_petugas,
            @Field("bendasitaan_id") String bendasitaan_id,
            @Field("eksternal") String eksternal
    );

    @FormUrlEncoded
    @POST("deleteBendaSitaan.php")
    Call<BendaSitaan> deleteBendaSitaanResponse(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("deletePemeliharaan.php")
    Call<Pemeliharaan> deletePemeliharaanResponse(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

}
