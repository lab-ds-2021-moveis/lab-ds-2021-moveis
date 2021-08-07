package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.StockRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRequestRepository extends JpaRepository <StockRequest, Long> {}
