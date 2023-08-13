package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NovaRespostaForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.RespostaService
import br.com.alura.forum.service.TopicoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class RespostaFormMapper(
    private var usuarioService: UsuarioService

): Mapper<Resposta, RespostaView> {
    override fun map(r: Resposta): RespostaView {
        return RespostaView(
            mensagem = r.mensagem,
            id = r.id,
            dataCriacao = r.dataCriacao,
            solucao = r.solucao
        )
    }
}
