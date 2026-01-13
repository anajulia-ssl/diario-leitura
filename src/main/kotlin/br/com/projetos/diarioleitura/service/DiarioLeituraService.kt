package br.com.projetos.diarioleitura.service

import br.com.projetos.diarioleitura.dao.AvaliacaoDAO
import br.com.projetos.diarioleitura.dao.LivroDAO
import br.com.projetos.diarioleitura.dao.UsuarioDAO
import br.com.projetos.diarioleitura.model.Avaliacao
import br.com.projetos.diarioleitura.model.Livro
import br.com.projetos.diarioleitura.model.Usuario
import java.time.LocalDate

class DiarioLeituraService(
    private val livroDAO: LivroDAO,
    private val usuarioDAO: UsuarioDAO,
    private val avaliacaoDAO: AvaliacaoDAO
) {
    fun cadastrarUsuario(nome: String) {
        require(nome.trim().isNotEmpty()) { "Nome do usuário não pode ser vazio." }
        usuarioDAO.adicionar(Usuario(nome = nome.trim()))
    }

    fun cadastrarLivro(isbn: String, titulo: String, autor: String) {
        require(isbn.trim().isNotEmpty()) { "ISBN não pode ser vazio." }
        require(titulo.trim().isNotEmpty()) { "Título não pode ser vazio." }
        require(autor.trim().isNotEmpty()) { "Autor não pode ser vazio." }

        livroDAO.adicionar(Livro(isbn.trim(), titulo.trim(), autor.trim()))
    }

    fun avaliarLivro(usuarioId: Int, livroIsbn: String, nota: Int, comentario: String) {
        require(nota in 1..5) { "A nota deve estar entre 1 e 5." }

        val usuario = usuarioDAO.buscarPorId(usuarioId)
            ?: throw IllegalArgumentException("Usuário não encontrado")

        val livro = livroDAO.buscarPorIsbn(livroIsbn.trim())
            ?: throw IllegalArgumentException("Livro não encontrado")

        val avaliacaoExistente = avaliacaoDAO.buscarPorUsuarioELivro(usuarioId, livroIsbn)
        if (avaliacaoExistente != null)
            throw IllegalArgumentException("Já existe uma avaliação desse usuário para esse livro")

        val avaliacao = Avaliacao(
            usuario = usuario,
            livro = livro,
            nota = nota,
            comentario = comentario.trim(),
            data = LocalDate.now().toString(),
        )

        avaliacaoDAO.adicionar(avaliacao)
    }

    fun removerUsuario(id: Int) {
        val usuario = usuarioDAO.buscarPorId(id)
        usuarioDAO.remover(usuario)
    }

    fun removerLivro(isbn: String) {
        val livro = livroDAO.buscarPorIsbn(isbn)
        livroDAO.remover(livro)
    }

    fun removerAvaliacao(id: Int) {
        val avaliacao = avaliacaoDAO.buscarPorId(id)
        avaliacaoDAO.remover(avaliacao)
    }
}
