package br.com.projetos.diarioleitura.model;

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "usuarios")
class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var nome: String = "",
) {
    override fun toString(): String {
        return "Usuario" +
                "\nid: $id " +
                "\nnome: '$nome'"
    }
}
