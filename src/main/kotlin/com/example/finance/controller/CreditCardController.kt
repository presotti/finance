package com.example.finance.controller

import com.example.finance.model.CreditCard
import com.example.finance.model.User
import com.example.finance.service.CreditCardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/credit-card")
class CreditCardController(private val creditCardService: CreditCardService) {

    @GetMapping
    fun getAll() = creditCardService.getAllCreditCards()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = ResponseEntity.ok(creditCardService.getOneCreditCard(id))

    @PostMapping
    fun create(@RequestBody creditCard: CreditCard) =
        ResponseEntity.status(HttpStatus.CREATED).body(creditCardService.createdCreditCard(creditCard))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        creditCardService.deleteCreditCard(id).let {
            ResponseEntity.noContent().build<Unit>()
        }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody creditCard: CreditCard): ResponseEntity<CreditCard> {
        return ResponseEntity.ok(creditCardService.updateCreditCard(id, creditCard))
    }
}