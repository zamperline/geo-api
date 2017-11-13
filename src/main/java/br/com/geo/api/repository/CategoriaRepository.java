package br.com.geo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.geo.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
