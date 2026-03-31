package com.example.finance.model

import com.fasterxml.jackson.annotation.JsonProperty

enum class TypeTransaction {
    @JsonProperty("despesa")
    DESPESA,

    @JsonProperty("receita")
    RECEITA
}