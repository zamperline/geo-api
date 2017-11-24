package br.com.geo.api.repository.filter;

public class MapaFilter {
	
	private Bounds bounds;
	
	private String latCenter;
	private String lngCenter;
	
	private Integer opcao;
	
	public Bounds getBounds() {
		return bounds;
	}
	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}
	public String getLatCenter() {
		return latCenter;
	}
	public void setLatCenter(String latCenter) {
		this.latCenter = latCenter;
	}
	public String getLngCenter() {
		return lngCenter;
	}
	public void setLngCenter(String lngCenter) {
		this.lngCenter = lngCenter;
	}
	public Integer getOpcao() {
		return opcao;
	}
	public void setOpcao(Integer opcao) {
		this.opcao = opcao;
	}
}