package br.com.geo.api.repository.mapa;

import br.com.geo.api.repository.filter.MapaFilter;

public interface MapaRepositoryQuery {

	public Object getGeoJson(MapaFilter mapaFilter);
	
}