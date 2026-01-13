package br.com.projetos.diarioleitura.dao

import br.com.projetos.diarioleitura.model.Usuario
import br.com.projetos.diarioleitura.util.transactional
import jakarta.persistence.EntityManager

class UsuarioDAO(private val manager: EntityManager) {

    fun adicionar(usuario: Usuario) = transactional(manager) { manager.persist(usuario) }

    fun remover(usuario: Usuario?) = transactional(manager) { manager.remove(usuario) }

    fun listar(): List<Usuario> {
        return manager.createQuery("SELECT u FROM Usuario u", Usuario::class.java).resultList
    }

    fun buscarPorId(id: Int): Usuario? {
        return manager.find(Usuario::class.java, id)
    }
}