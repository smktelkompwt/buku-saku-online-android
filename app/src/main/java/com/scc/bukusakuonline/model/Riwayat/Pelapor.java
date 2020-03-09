package com.scc.bukusakuonline.model.Riwayat;

public class Pelapor{
	private String nama;
	private String id;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Pelapor{" + 
			"nama = '" + nama + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
