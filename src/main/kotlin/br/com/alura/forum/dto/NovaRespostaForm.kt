package br.com.alura.forum.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class NovaRespostaForm (
    @NotEmpty val mensagem: String,
    @NotNull val idAutor: Long
)