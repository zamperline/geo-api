package br.com.geo.api.repository.projection;

public class Mapa {
	private Object geoJson;
	

	public Mapa() {
		super();
	}

	public Mapa(Object geoJson) {
		super();
		this.geoJson = geoJson;
	}

	public Object getGeoJson() {
		return geoJson;
	}

	public void setGeoJson(Object geoJson) {
		this.geoJson = geoJson;
	}

}
