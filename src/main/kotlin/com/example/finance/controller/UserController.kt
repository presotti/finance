package com.example.finance.controller

import com.example.finance.dto.UserDTO
import com.example.finance.mapper.toDTO
import com.example.finance.model.Transaction
import com.example.finance.model.User
import com.example.finance.service.UserService
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
@RequestMapping("api/v1/users")
class UserController(private val userService: UserService){

    @GetMapping
    fun getAll() = userService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = ResponseEntity.ok(userService.getById(id))

    @PostMapping
    fun create(@RequestBody user: UserDTO) =
        ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        userService.deleteUser(id).let {
            ResponseEntity.noContent().build<Unit>()
        }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody user: UserDTO) =
        ResponseEntity.ok(userService.updateUser(id, user))

}