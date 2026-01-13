package br.com.projetos.diarioleitura.util

import jakarta.persistence.EntityManager

fun <T> transactional(manager: EntityManager, block: () -> T): T {
    val transactionManager = manager.transaction
    try {
        transactionManager.begin()
        val result = block()
        transactionManager.commit()
        return result
    } catch (e: Exception) {
        if (transactionManager.isActive) transactionManager.rollback()
        throw e
    }
}
