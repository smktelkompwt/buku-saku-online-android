package com.scc.bukusakuonline.user.model.riwayat;

public class DataItem {
	private Pelanggaran pelanggaran;
	private String image;
	private String createdDate;
	private int V;
	private Pelapor pelapor;
	private String id;
	private User user;

	public void setPelanggaran(Pelanggaran pelanggaran){
		this.pelanggaran = pelanggaran;
	}

	public Pelanggaran getPelanggaran(){
		return pelanggaran;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setPelapor(Pelapor pelapor){
		this.pelapor = pelapor;
	}

	public Pelapor getPelapor(){
		return pelapor;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"pelanggaran = '" + pelanggaran + '\'' + 
			",image = '" + image + '\'' + 
			",createdDate = '" + createdDate + '\'' + 
			",__v = '" + V + '\'' + 
			",pelapor = '" + pelapor + '\'' + 
			",_id = '" + id + '\'' + 
			",id = '" + id + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}


}
