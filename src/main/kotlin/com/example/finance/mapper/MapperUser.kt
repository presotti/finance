package com.example.finance.mapper

import com.example.finance.dto.UserDTO
import com.example.finance.model.User

// Entity -> DTO
fun User.toDTO(): UserDTO {
    return UserDTO(
        id = this.id,
        name = this.name,
        // Convertemos cada transação da lista original para DTO
        transactions = this.transactions.map { it.toDTO() }
    )
}

// DTO -> Entity (Para criação de novos usuários)
fun UserDTO.toEntity(): User {
    val user = User()
    user.name = this.name
    return user
}