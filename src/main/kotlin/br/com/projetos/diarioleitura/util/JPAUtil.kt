package br.com.projetos.diarioleitura.util

import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence

object JPAUtil {
    private val emf = Persistence.createEntityManagerFactory("diario-leitura")

    fun getEntityManager(): EntityManager {
        return emf.createEntityManager()
    }

    fun close(){
        if (emf.isOpen) emf.close()
    }
}