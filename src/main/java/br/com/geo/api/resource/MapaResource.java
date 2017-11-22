package br.com.geo.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.geo.api.repository.MapaRepository;
import br.com.geo.api.repository.filter.MapaFilter;

@RestController
@RequestMapping("/geo")
public class MapaResource {

	@Autowired
	private MapaRepository mapaRepository;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MAPA') and #oauth2.hasScope('read')")
	public ResponseEntity<Object> getGeoJson(MapaFilter mapaFilter) {
		Object geoJson = mapaRepository.getGeoJson(mapaFilter);
		return geoJson != null ? ResponseEntity.ok(geoJson) : ResponseEntity.notFound().build();
	}
}