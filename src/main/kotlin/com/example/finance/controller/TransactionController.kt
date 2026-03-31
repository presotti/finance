package com.example.finance.controller

import com.example.finance.dto.TransactionDTO
import com.example.finance.model.Transaction
import com.example.finance.service.TransactionService
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
@RequestMapping("api/v1/transactions")
class TransactionController(private val transactionService: TransactionService) {

    @GetMapping
    fun allTransactions() = ResponseEntity.ok(transactionService.getAllTransactions())

    @GetMapping("/current-month")
    fun buyMonthCurrent() = ResponseEntity.ok(transactionService.getCurrentMonthTransactions())

    @PostMapping
    fun create(@RequestBody transaction: TransactionDTO) =
        ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createdTransaction(transaction))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long){
        transactionService.deleteTransaction(id).let {
            ResponseEntity.noContent().build<Unit>()
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody transaction: TransactionDTO) =
        ResponseEntity.ok(transactionService.updateTransaction(id, transaction))


}