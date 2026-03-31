package com.example.finance.repository

import com.example.finance.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.transactions t WHERE u.id = :id ORDER BY t.date DESC")
    fun findUserWithTransactions(id: Long): User?

    fun findAllByOrderByNameAsc(): MutableList<User>
}