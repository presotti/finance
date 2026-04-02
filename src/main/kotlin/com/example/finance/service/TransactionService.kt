package com.example.finance.service

import com.example.finance.dto.TransactionDTO
import com.example.finance.mapper.toDTO
import com.example.finance.mapper.toEntity
import com.example.finance.model.Transaction
import com.example.finance.repository.TransactionRepository
import com.example.finance.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class TransactionService(private val transactionRepository: TransactionRepository, private val userRepository: UserRepository) {

    fun getAllTransactions(): List<TransactionDTO> {
        val transactions = transactionRepository.findAll()
        return loadTransactinsActives(transactions)
    }

    fun getCurrentMonthTransactions(): List<TransactionDTO> {
        val now = LocalDateTime.now()
        val transactions = transactionRepository.findAllBuyFromMonth(now.monthValue, now.year)
        return loadTransactinsActives(transactions)
    }

    fun createdTransaction(dto: TransactionDTO): TransactionDTO{
        val user = userRepository.findById(dto.userId ?: throw IllegalArgumentException("ID do usuário é obrigatório"))
            .orElseThrow { NoSuchElementException("Usuário não encontrado") }

        val entity = toEntity(dto, user)
        val savedEntity = transactionRepository.save(entity)

        return savedEntity.toDTO()
    }

    fun updateTransaction(id: Long, dto: TransactionDTO): TransactionDTO{
        if(transactionRepository.existsById(id)){
            val user = userRepository.findById(dto.userId ?: throw IllegalArgumentException("ID do usuário é obrigatório"))
                .orElseThrow { NoSuchElementException("Usuário não encontrado") }

            val entity = toEntity(dto, user)
            return transactionRepository.save(entity).toDTO()
        } else{
            throw NoSuchElementException("transicao nao encontrada")
        }
    }

    fun deleteTransaction(id: Long){
        if(!transactionRepository.existsById(id)){
            throw NoSuchElementException("transicao nao encontrada")
        }
        transactionRepository.deleteById(id)
    }

    fun loadTransactinsActives(transactions: MutableList<Transaction>): List<TransactionDTO>{
        val now = LocalDateTime.now()
        val transactionsToUpdate = mutableListOf<Transaction>()

        transactions.forEach { transaction ->
            if (transaction.active) {
                // Calcula a diferença real de meses entre a data da transação e hoje
                val monthsPassed = ChronoUnit.MONTHS.between(
                    transaction.date.withDayOfMonth(1),
                    now.withDayOfMonth(1)
                ).toInt()

                // Se os meses passados superarem o número de parcelas, desativa
                if (monthsPassed <= transaction.installments) {
                    transaction.active = true
                    transactionsToUpdate.add(transaction)
                }

                if (monthsPassed > transaction.installments) {
                    transaction.active = false
                    transactionsToUpdate.add(transaction)
                }
            }
        }

        // Salva tudo de uma vez (Batch Save) - Muito mais performático
        if (transactionsToUpdate.isNotEmpty()) {
            transactionRepository.saveAll(transactionsToUpdate)
        }

        return transactions.filter { it.active }.map { it.toDTO()}
    }
}

