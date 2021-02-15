package com.enigma.test7maret.Repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.enigma.test7maret.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	@Query(nativeQuery = true)
	public List<Transaction> findAllByFromAndDateSQL(@Param("froms")Long account,@Param("date")Date date);
}
