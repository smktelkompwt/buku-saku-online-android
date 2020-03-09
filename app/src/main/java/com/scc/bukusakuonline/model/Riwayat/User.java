package com.scc.bukusakuonline.model.Riwayat;

public class User{
	private String nama;
	private String kelas;
	private long nis;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setKelas(String kelas){
		this.kelas = kelas;
	}

	public String getKelas(){
		return kelas;
	}

	public void setNis(long nis){
		this.nis = nis;
	}

	public long getNis(){
		return nis;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"nama = '" + nama + '\'' + 
			",kelas = '" + kelas + '\'' + 
			",nis = '" + nis + '\'' + 
			"}";
		}
}
