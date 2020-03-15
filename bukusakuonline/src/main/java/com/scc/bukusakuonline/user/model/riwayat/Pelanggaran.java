package com.scc.bukusakuonline.user.model.riwayat;

public class Pelanggaran{
	private String kategori;
	private int point;

	public void setKategori(String kategori){
		this.kategori = kategori;
	}

	public String getKategori(){
		return kategori;
	}

	public void setPoint(int point){
		this.point = point;
	}

	public int getPoint(){
		return point;
	}

	@Override
 	public String toString(){
		return 
			"Pelanggaran{" + 
			"kategori = '" + kategori + '\'' + 
			",point = '" + point + '\'' + 
			"}";
		}
}
