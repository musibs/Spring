package com.codefountain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codefountain.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{

}
