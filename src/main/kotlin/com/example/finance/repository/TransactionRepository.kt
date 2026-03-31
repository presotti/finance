package com.example.finance.repository

import com.example.finance.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: JpaRepository<Transaction, Long> {

    @Query("""
        SELECT t FROM Transaction t 
        JOIN t.user 
        WHERE MONTH(t.date) = :month 
        AND YEAR(t.date) = :year
        ORDER BY t.date DESC
    """)
    fun findAllBuyFromMonth(month: Int, year: Int): MutableList<Transaction>
}