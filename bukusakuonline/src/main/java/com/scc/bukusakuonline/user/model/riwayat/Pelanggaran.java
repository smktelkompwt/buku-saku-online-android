package com.scc.bukusakuonline.user.model.riwayat;

public class Pelanggaran{
	private String kategori;
	private int point;
	private String kode;

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

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
					",kode = '" + kode + '\''+
			"}";
		}
}
