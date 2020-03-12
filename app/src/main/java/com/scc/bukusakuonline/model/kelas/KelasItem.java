package com.scc.bukusakuonline.model.kelas;

public class KelasItem {
	private String waliKelas;
	private String createdDate;
	private String kelas;
	private int V;
	private String jurusan;
	private String id;


	public void setWaliKelas(String waliKelas){
		this.waliKelas = waliKelas;
	}

	public String getWaliKelas(){
		return waliKelas;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setKelas(String kelas){
		this.kelas = kelas;
	}

	public String getKelas(){
		return kelas;
	}

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setJurusan(String jurusan){
		this.jurusan = jurusan;
	}

	public String getJurusan(){
		return jurusan;
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
			"DataItem{" + 
			"wali_kelas = '" + waliKelas + '\'' + 
			",createdDate = '" + createdDate + '\'' + 
			",kelas = '" + kelas + '\'' + 
			",__v = '" + V + '\'' + 
			",jurusan = '" + jurusan + '\'' + 
			",_id = '" + id + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
