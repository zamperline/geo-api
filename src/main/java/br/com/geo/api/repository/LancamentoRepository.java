package br.com.geo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.geo.api.model.Lancamento;
import br.com.geo.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}