package com.example.finance.service

import com.example.finance.dto.UserDTO
import com.example.finance.mapper.toDTO
import com.example.finance.mapper.toEntity
import com.example.finance.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val transactionService: TransactionService) {

    fun getAll() = userRepository.findAllByOrderByNameAsc().map { it.toDTO()}

    fun getById(id: Long): UserDTO {
        val user = userRepository.findUserWithTransactions(id)
            ?: throw NoSuchElementException("Usuário com o ID $id não encontrado")

            if(user.transactions.isNotEmpty()){
                val _transactions = transactionService.loadTransactinsActives(user.transactions)
                return user.toDTO().copy(transactions = _transactions)
            }
        return user.toDTO()
    }

    fun createUser(user: UserDTO) = userRepository.save(user.toEntity()).toDTO()

    fun updateUser(id: Long, user: UserDTO): UserDTO {
        return if (userRepository.existsById(id)) {
            userRepository.save(user.toEntity()).toDTO()
        } else {
            throw NoSuchElementException("Cartão de crédito com o ID ${user.id} não encontrado")
        }
    }

    fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) {
            NoSuchElementException("Não foi possível deletar: Usuário $id inexistente")
        }
        userRepository.deleteById(id)
    }
}