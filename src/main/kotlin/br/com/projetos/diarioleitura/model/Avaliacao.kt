package br.com.projetos.diarioleitura.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "avaliacoes")
class Avaliacao(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    val usuario: Usuario,

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)

    val livro: Livro,
    val nota: Int,
    val comentario: String,
    val data: String,
) {

    override fun toString(): String {
        return "Avaliacao" +
                "\nId: ${id}" +
                "\nUsuario: '${usuario.nome}'" +
                "\nLivro: '${livro.titulo}'" +
                "\nNota: '${nota}'" +
                "\nComentario: '${comentario}'" +
                "\nData: '${data}'"
    }
}