package br.com.alura.forum.controller

import br.com.alura.forum.dto.*
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.service.RespostaService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping ("/topicos/{id}/respostas")
class RespostasController(private val service: RespostaService) {

    @GetMapping
    fun listar(@PathVariable id: Long): List<RespostaView>{
        return service.listar(id)
    }

    @PostMapping
    fun cadastrar(@PathVariable id: Long, @RequestBody @Valid novaRespostaForm: NovaRespostaForm) : RespostaView{
        return service.cadastrar(novaRespostaForm, id)
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid form: AtualizarRespostaForm) : ResponseEntity<RespostaView> {
        val novarespostaForm = service.atualizar(form)
        return ResponseEntity.ok(novarespostaForm)
    }

    @DeleteMapping( "/{respostaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable respostaId:Long) {
        return service.deletar(respostaId)
    }

    }