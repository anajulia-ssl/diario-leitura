
package br.com.projetos.diarioleitura

import br.com.projetos.diarioleitura.dao.AvaliacaoDAO
import br.com.projetos.diarioleitura.dao.LivroDAO
import br.com.projetos.diarioleitura.dao.UsuarioDAO
import br.com.projetos.diarioleitura.service.DiarioLeituraService
import br.com.projetos.diarioleitura.util.JPAUtil

fun main() {
    val em = JPAUtil.getEntityManager()

    val usuarioDAO = UsuarioDAO(em)
    val livroDAO = LivroDAO(em)
    val avaliacaoDAO = AvaliacaoDAO(em)

    val service = DiarioLeituraService(livroDAO, usuarioDAO, avaliacaoDAO)

    println("=== Registro de Leituras ===")
    println("1 - Cadastrar usuário")
    println("2 - Cadastrar livro")
    println("3 - Avaliar livro")
    println("4 - Listar usuários")
    println("5 - Listar livros")
    println("6 - Listar avaliações")
    println("7 - Remover usuário")
    println("8 - Remover livro")
    println("9 - Remover avaliação")
    println("0 - Sair")
    println("=========================")

    try {
        while (true) {
            print("\nEscolha uma opção: ")
            val opcao = readln().toIntOrNull() ?: -1

            when (opcao) {
                1 -> cadastrarUsuario(service)
                2 -> cadastrarLivro(service)
                3 -> avaliarLivro(service)
                4 -> usuarioDAO.listar().forEach { println("\n$it") }
                5 -> livroDAO.listar().forEach { println("\n$it") }
                6 -> avaliacaoDAO.listar().forEach { println("\n$it") }
                7 -> removerUsuario(service)
                8 -> removerLivro(service)
                9 -> removerAvaliacao(service)
                0 -> {
                    println("Saindo...")
                    break
                }
                else -> println("Opção inválida. Tente novamente.")
            }
        }
    } catch (e: Exception) {
        println("Erro: ${e.message}")
    } finally {
        em.close()
        JPAUtil.close()
    }
}

private fun cadastrarUsuario(service: DiarioLeituraService) {
    print("Nome: ")
    val nome = readln()
    service.cadastrarUsuario(nome)
    println("✅ Usuário cadastrado com sucesso!")
}

private fun removerUsuario(service: DiarioLeituraService) {
    print("\nID do usuário a remover: ")
    val id = readln().toIntOrNull() ?: throw IllegalArgumentException("ID inválido")

    service.removerUsuario(id)
    println("🗑️ Usuário removido com sucesso!")
}

private fun cadastrarLivro(service: DiarioLeituraService) {
    print("ISBN: ")
    val isbn = readln()

    print("Título: ")
    val titulo = readln()

    print("Autor: ")
    val autor = readln()

    service.cadastrarLivro(isbn, titulo, autor)
    println("✅ Livro cadastrado com sucesso!")
}

private fun removerLivro(service: DiarioLeituraService) {
    print("\nISBN do livro a remover: ")
    val isbn = readlnOrNull() ?: throw IllegalArgumentException("ISBN inválido")

    service.removerLivro(isbn)
    println("🗑️ Livro removido com sucesso!")
}

private fun avaliarLivro(service: DiarioLeituraService) {
    print("ID do usuário: ")
    val usuarioId = readln().toIntOrNull() ?: throw IllegalArgumentException("ID do usuário inválido")

    print("ISBN do livro: ")
    val livroIsbn = readln()

    print("Nota (1 a 5): ")
    val nota = readln().toIntOrNull() ?: throw IllegalArgumentException("Nota inválida")

    print("Comentário: ")
    val comentario = readln()

    service.avaliarLivro(usuarioId, livroIsbn, nota, comentario)
    println("✅ Avaliação registrada com sucesso!")
}

private fun removerAvaliacao(service: DiarioLeituraService) {
    print("\nID da avaliação a remover: ")
    val id = readln().toIntOrNull() ?: throw IllegalArgumentException("ID inválido")

    service.removerAvaliacao(id)
    println("🗑️ Avaliacao removida com sucesso!")
}