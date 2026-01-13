package br.com.projetos.diarioleitura.dao

import br.com.projetos.diarioleitura.model.Livro
import br.com.projetos.diarioleitura.util.transactional
import jakarta.persistence.EntityManager

class LivroDAO(private val manager: EntityManager) {
    fun adicionar(livro: Livro) = transactional(manager) { manager.persist(livro) }

    fun remover(livro: Livro?) = transactional(manager) { manager.persist(livro) }

    fun listar(): List<Livro> {
        return manager.createQuery("SELECT u FROM Livro u", Livro::class.java).resultList
    }

    fun buscarPorIsbn(livroIsbn: String): Livro? {
        return manager.find(Livro::class.java, livroIsbn)
    }
}