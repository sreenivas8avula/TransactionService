package com.sree.banking.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sree.banking.transaction.model.TransactionEntity;

/**
 * @author Sreenivasulu.Avula
 *
 */

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
