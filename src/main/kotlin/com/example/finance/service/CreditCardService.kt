package com.example.finance.service

import com.example.finance.model.CreditCard
import com.example.finance.repository.CreditCardRepository
import org.springframework.stereotype.Service

@Service
class CreditCardService(private val creditCardRepository: CreditCardRepository) {

    fun getAllCreditCards() = creditCardRepository.findAll()

    fun getOneCreditCard(id: Long): CreditCard {
        return creditCardRepository.findById(id).orElseThrow {
            NoSuchElementException("Cartao de credito com o id $id nao encontrado")
        }
    }

    fun createdCreditCard(card: CreditCard) = creditCardRepository.save(card)

    fun updateCreditCard(id: Long, card: CreditCard): CreditCard {
        return if (creditCardRepository.existsById(id)) {
            creditCardRepository.save(card)
        } else {
            throw NoSuchElementException("Cartão de crédito com o ID ${card.id} não encontrado")
        }
    }

    fun deleteCreditCard(id: Long){
             if (!creditCardRepository.existsById(id)) {
                 throw NoSuchElementException("Cartão de crédito com o ID $id não encontrado")
             }
             creditCardRepository.deleteById(id)
        }

}