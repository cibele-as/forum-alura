package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizarRespostaForm
import br.com.alura.forum.dto.NovaRespostaForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.mapper.RespostaFormMapper
import br.com.alura.forum.model.*
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Collector
import java.util.stream.Collectors

@Service
class RespostaService(
    private var respostas: List <Resposta>,
    private var respostaFormMapper: RespostaFormMapper,
    private var topicoService: TopicoService,
    private var usuarioService: UsuarioService
) {

    init {
            val curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programacao"
            )
            val autor = Usuario(
                id = 1,
                nome = "Ana da Silva",
                email = "ana@email.com"
            )
            val topico = Topico(
            id = 1,
            titulo = "Duvida Kotlin",
            mensagem = "Variaveis no Kotlin",
            curso = curso,
            autor = autor

        )
            val resposta = Resposta(
                id = 1,
                mensagem = "Resposta 1",
                autor = autor,
                topico = topico,
                solucao = false
            )
        val resposta2 = Resposta(
            id = 2,
            mensagem = "Resposta 2",
            autor = autor,
            topico = topico,
            solucao = false
        )

        respostas = Arrays.asList(resposta, resposta2)


    }

    fun listar (idTopico: Long): List<RespostaView>{
        return respostas.stream().filter { r ->
            r.topico?.id == idTopico
        }
            .map { resposta -> respostaFormMapper.map(resposta) }
            .collect(Collectors.toList())
    }

    fun cadastrar(form: NovaRespostaForm, idTopico: Long) : RespostaView{

        val topico = topicoService.buscarTopicoPorId(idTopico)
        val usuario = usuarioService.buscarPorId(form.idAutor)
        val novaResposta = Resposta(
            id= respostas.size.toLong() + 1,
            mensagem = form.mensagem,
            autor = usuario,
            topico = topico,
            solucao = false

        )
        respostas = respostas.plus(novaResposta)
        return  respostaFormMapper.map(novaResposta)


    }

    fun atualizar(form: AtualizarRespostaForm) : RespostaView{
        var resposta = respostas.stream().filter { r ->
            r.id == form.id
        }.findFirst().get()
        resposta.mensagem = form.mensagem
        respostas = respostas.minus(resposta).plus(resposta)
        return respostaFormMapper.map(resposta)
    }

    fun deletar(id: Long) {
        val resposta = respostas.stream().filter { r ->
            r.id == r.id
        }.findFirst().get()
        respostas = respostas.minus(resposta)
    }
}