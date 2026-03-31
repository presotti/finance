package com.example.finance.repository

import com.example.finance.model.CreditCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditCardRepository: JpaRepository<CreditCard, Long> {
}