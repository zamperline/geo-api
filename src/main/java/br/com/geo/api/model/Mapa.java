package br.com.geo.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "mapa")
@SequenceGenerator(name = "mapa_id_seq", sequenceName = "mapa_id_seq", initialValue = 1, allocationSize = 1)
public class Mapa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mapa_id_seq")
	private Long id;

	private String camada;
	
	@Transient
	private Object geoJson;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCamada() {
		return camada;
	}

	public void setCamada(String camada) {
		this.camada = camada;
	}

	public Object getGeoJson() {
		return geoJson;
	}

	public void setGeoJson(Object geoJson) {
		this.geoJson = geoJson;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mapa other = (Mapa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}