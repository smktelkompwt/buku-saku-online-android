package com.scc.bukusakuonline.model.dashboard;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("countPelanggaran")
	private int countPelanggaran;

	@SerializedName("countSiswa")
	private int countSiswa;

	public void setCountPelanggaran(int countPelanggaran){
		this.countPelanggaran = countPelanggaran;
	}

	public int getCountPelanggaran(){
		return countPelanggaran;
	}

	public void setCountSiswa(int countSiswa){
		this.countSiswa = countSiswa;
	}

	public int getCountSiswa(){
		return countSiswa;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"countPelanggaran = '" + countPelanggaran + '\'' + 
			",countSiswa = '" + countSiswa + '\'' + 
			"}";
		}
}