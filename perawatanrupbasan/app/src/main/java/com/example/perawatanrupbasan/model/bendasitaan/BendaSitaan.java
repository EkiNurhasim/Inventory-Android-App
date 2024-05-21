package com.example.perawatanrupbasan.model.bendasitaan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BendaSitaan{

	@SerializedName("data")
	private List<BendaSitaanData> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<BendaSitaanData> data){
		this.data = data;
	}

	public List<BendaSitaanData> getData(){
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