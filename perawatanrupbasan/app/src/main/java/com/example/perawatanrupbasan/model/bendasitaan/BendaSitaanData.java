package com.example.perawatanrupbasan.model.bendasitaan;

import com.google.gson.annotations.SerializedName;

public class BendaSitaanData {

	@SerializedName("loginregis_id")
	private String loginregisId;

	@SerializedName("gudang")
	private String gudang;

	@SerializedName("no_regis")
	private String noRegis;

	@SerializedName("instansi_penitip")
	private String instansiPenitip;

	@SerializedName("tanggal_masuk")
	private String tanggalMasuk;

	@SerializedName("tersangka")
	private String tersangka;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("jumlah_barang")
	private String jumlahBarang;

	@SerializedName("kondisi_barang")
	private String kondisiBarang;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("id")
	private String id;

	public void setLoginregisId(String loginregisId){
		this.loginregisId = loginregisId;
	}

	public String getLoginregisId(){
		return loginregisId;
	}

	public void setGudang(String gudang){
		this.gudang = gudang;
	}

	public String getGudang(){
		return gudang;
	}

	public void setNoRegis(String noRegis){
		this.noRegis = noRegis;
	}

	public String getNoRegis(){
		return noRegis;
	}

	public void setInstansiPenitip(String instansiPenitip){
		this.instansiPenitip = instansiPenitip;
	}

	public String getInstansiPenitip(){
		return instansiPenitip;
	}

	public void setTanggalMasuk(String tanggalMasuk){
		this.tanggalMasuk = tanggalMasuk;
	}

	public String getTanggalMasuk(){
		return tanggalMasuk;
	}

	public void setTersangka(String tersangka){
		this.tersangka = tersangka;
	}

	public String getTersangka(){
		return tersangka;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setJumlahBarang(String jumlahBarang){
		this.jumlahBarang = jumlahBarang;
	}

	public String getJumlahBarang(){
		return jumlahBarang;
	}

	public void setKondisiBarang(String kondisiBarang){
		this.kondisiBarang = kondisiBarang;
	}

	public String getKondisiBarang(){
		return kondisiBarang;
	}

	public void setNamaBarang(String namaBarang){
		this.namaBarang = namaBarang;
	}

	public String getNamaBarang(){
		return namaBarang;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}