package br.com.geo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.geo.api.model.Mapa;
import br.com.geo.api.repository.mapa.MapaRepositoryQuery;

public interface MapaRepository extends JpaRepository<Mapa, Long>, MapaRepositoryQuery {

}