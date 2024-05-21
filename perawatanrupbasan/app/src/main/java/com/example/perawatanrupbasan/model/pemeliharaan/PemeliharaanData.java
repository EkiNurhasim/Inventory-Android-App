package com.example.perawatanrupbasan.model.pemeliharaan;

import com.google.gson.annotations.SerializedName;

public class PemeliharaanData {

	@SerializedName("alat_bahan")
	private String alatBahan;

	@SerializedName("pelaksana")
	private String pelaksana;

	@SerializedName("bendasitaan_id")
	private String bendasitaanId;

	@SerializedName("kegiatan")
	private String kegiatan;

	@SerializedName("nama_petugas")
	private String namaPetugas;

	@SerializedName("eksternal")
	private String eksternal;

	@SerializedName("id")
	private String id;

	@SerializedName("tanggal")
	private String tanggal;

	public void setAlatBahan(String alatBahan){
		this.alatBahan = alatBahan;
	}

	public String getAlatBahan(){
		return alatBahan;
	}

	public void setPelaksana(String pelaksana){
		this.pelaksana = pelaksana;
	}

	public String getPelaksana(){
		return pelaksana;
	}

	public void setBendasitaanId(String bendasitaanId){
		this.bendasitaanId = bendasitaanId;
	}

	public String getBendasitaanId(){
		return bendasitaanId;
	}

	public void setKegiatan(String kegiatan){
		this.kegiatan = kegiatan;
	}

	public String getKegiatan(){
		return kegiatan;
	}

	public void setNamaPetugas(String namaPetugas){
		this.namaPetugas = namaPetugas;
	}

	public String getNamaPetugas(){
		return namaPetugas;
	}

	public void setEksternal(String eksternal){
		this.eksternal = eksternal;
	}

	public String getEksternal(){
		return eksternal;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}
}