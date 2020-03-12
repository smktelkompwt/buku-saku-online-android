package com.scc.bukusakuonline.model.kelas;

import java.util.List;

public class KelasResponse{
	private int code;
	private List<KelasItem> data;
	private String success;
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(List<KelasItem> data){
		this.data = data;
	}

	public List<KelasItem> getData(){
		return data;
	}

	public void setSuccess(String success){
		this.success = success;
	}

	public String getSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"KelasResponse{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}