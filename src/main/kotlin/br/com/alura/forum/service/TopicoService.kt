package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizarTopicoForm
import br.com.alura.forum.dto.NovaRespostaForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.RespostaFormMapper
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicoService (
    private var repository: TopicoRepository,
    private var topicoViewMapper: TopicoViewMapper,
    private var topicoFormMapper: TopicoFormMapper,
    private var notFoundMessage: String =" Topico nao encontrado!"

){


    fun listar(): List<TopicoView> {
        return repository.findAll().stream().map {
                t -> topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id : Long): TopicoView {
        val topico = repository.findById(id).stream().filter ({
                t -> t.id == id
        }).findFirst().orElseThrow{NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico)
    }

// Criado para buscar o topico do usuario para resolver o problema da classe Resposta Service
    fun buscarTopicoPorId(id : Long): Topico {
        val topico = repository.findById(id).stream().filter ({
                t -> t.id == id
        }).findFirst().orElseThrow{NotFoundException(notFoundMessage)}
        return topico
    }


    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)

    }

    fun atualizar(form: AtualizarTopicoForm) : TopicoView{
        val topico = repository.findById(form.id).stream().filter ({
                t -> t.id == form.id
        }).findFirst().orElseThrow{NotFoundException(notFoundMessage)}

        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
       repository.deleteById(id)
    }

}
