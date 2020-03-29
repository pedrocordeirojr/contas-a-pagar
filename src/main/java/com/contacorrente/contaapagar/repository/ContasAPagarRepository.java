package com.contacorrente.contaapagar.repository;

import com.contacorrente.contaapagar.model.ContaAPagarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContasAPagarRepository extends JpaRepository<ContaAPagarEntity, Integer> {
}
