package com.example.finance.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "transaction")
class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name= "type_transaction")
    var typeTransaction : TypeTransaction = TypeTransaction.DESPESA

    var amount: BigDecimal = BigDecimal.ZERO

    // date: Date -> Traduzido para 'date' (ou 'createdAt')
    var date: LocalDateTime = LocalDateTime.now()

    // nameProduct: String -> Traduzido para 'productName'
    @Column(name="product_name")
    var productName: String = ""

    // ondeFoiGasto: String -> Traduzido para 'merchant' ou 'location'
    var location: String = ""

    // formaPagamento: String -> Traduzido para 'paymentMethod'
    @Column(name="payment_method")
    var paymentMethod: String = ""

    // numberParcelas: String -> Traduzido para 'installments'
    var installments: Int = 1

    var active : Boolean = true

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_card_id")
    var creditCard: CreditCard? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("transactions")
    var user: User? = null
}