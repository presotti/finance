package com.example.finance.dto

import com.example.finance.model.TypeTransaction
import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDTO(
    val id: Long? = null,
    val typeTransaction: TypeTransaction, // O seu Enum
    val amount: BigDecimal,
    val date: LocalDateTime? = LocalDateTime.now(),
    val productName: String,
    val location: String,
    val paymentMethod: String,
    val installments: Int,
    val active: Boolean = true,
    val userId: Long? = null // Passamos apenas o ID do usuário para simplificar
)
