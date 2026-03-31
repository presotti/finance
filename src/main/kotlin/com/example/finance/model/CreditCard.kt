package com.example.finance.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "credit_card")
class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    val name: String = ""

    @Column(name = "credit_limit")
    val creditLimit: BigDecimal = BigDecimal.ZERO

    @Column(name = "last_four_numbers")
    val lastFourNumbers: String = ""

    @OneToMany(mappedBy = "creditCard", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var transactions: List<Transaction> = mutableListOf()

}