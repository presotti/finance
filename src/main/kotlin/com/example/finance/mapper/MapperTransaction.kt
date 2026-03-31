package com.example.finance.mapper

import com.example.finance.dto.TransactionDTO
import com.example.finance.model.Transaction
import com.example.finance.model.User

fun Transaction.toDTO() = TransactionDTO(
    id = this.id,
    typeTransaction = this.typeTransaction,
    amount = this.amount,
    date = this.date,
    productName = this.productName,
    location = this.location,
    paymentMethod = this.paymentMethod,
    installments = this.installments,
    active = this.active,
    userId = this.user?.id
)

// No seu Service para salvar (Entrada)
fun toEntity(dto: TransactionDTO, user: User): Transaction {
    val entity = Transaction()
    entity.typeTransaction = dto.typeTransaction
    entity.amount = dto.amount
    entity.productName = dto.productName
    entity.date = dto.date!!
    entity.location = dto.location
    entity.paymentMethod = dto.paymentMethod
    entity.installments = dto.installments
    entity.user = user
    return entity
}