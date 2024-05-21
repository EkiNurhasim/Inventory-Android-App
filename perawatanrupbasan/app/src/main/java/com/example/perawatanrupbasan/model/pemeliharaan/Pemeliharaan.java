package com.example.perawatanrupbasan.model.pemeliharaan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Pemeliharaan{

	@SerializedName("data")
	private List<PemeliharaanData> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<PemeliharaanData> data){
		this.data = data;
	}

	public List<PemeliharaanData> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}