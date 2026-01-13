package br.com.projetos.diarioleitura.dao

import br.com.projetos.diarioleitura.model.Avaliacao
import br.com.projetos.diarioleitura.util.transactional
import jakarta.persistence.EntityManager

class AvaliacaoDAO(private val manager: EntityManager) {

    fun adicionar(avaliacao: Avaliacao) = transactional(manager) { manager.persist(avaliacao) }

    fun remover(avaliacao: Avaliacao?) = transactional(manager) { manager.remove(avaliacao) }

    fun listar(): List<Avaliacao> {
        return manager.createQuery("SELECT u FROM Avaliacao u", Avaliacao::class.java).resultList
    }

    fun buscarPorId(id: Int): Avaliacao? {
        return manager.find(Avaliacao::class.java, id)
    }

    fun buscarPorUsuarioELivro(usuarioId: Int, livroIsbn: String): Avaliacao? {

        val resultados = manager.createQuery(
            "SELECT a FROM Avaliacao a WHERE a.usuario.id = :usuarioId AND a.livro.isbn = :livroIsbn".trimIndent(),
            Avaliacao::class.java
        ).setParameter("usuarioId", usuarioId)
         .setParameter("livroIsbn", livroIsbn)
         .resultList

        return resultados.firstOrNull()
    }
}