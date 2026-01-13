package br.com.projetos.diarioleitura.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "livros")
class Livro(
    @Id
    var isbn: String = "",
    var titulo: String = "",
    var autor: String = "",
) {
    override fun toString(): String {
        return "Livro" +
                "\nIsbn: '$isbn'" +
                "\nTitulo: '$titulo'" +
                "\nAutor: '$autor'"
    }
}