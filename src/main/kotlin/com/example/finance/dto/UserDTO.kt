package com.example.finance.dto

data class UserDTO(
    val id: Long? = null,
    val name: String,
    // Você pode incluir uma lista de TransactionDTOs aqui
    val transactions: List<TransactionDTO> = emptyList()
)
