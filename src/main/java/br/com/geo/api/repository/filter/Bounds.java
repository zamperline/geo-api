package br.com.geo.api.repository.filter;

public class Bounds {
	private String lat1;
	private String lat2;
	private String lng1;
	private String lng2;
	public String getLat1() {
		return lat1;
	}
	public void setLat1(String lat1) {
		this.lat1 = lat1;
	}
	public String getLat2() {
		return lat2;
	}
	public void setLat2(String lat2) {
		this.lat2 = lat2;
	}
	public String getLng1() {
		return lng1;
	}
	public void setLng1(String lng1) {
		this.lng1 = lng1;
	}
	public String getLng2() {
		return lng2;
	}
	public void setLng2(String lng2) {
		this.lng2 = lng2;
	}		
}