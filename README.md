# Diário de Leitura 📚

Um projeto **simples** em Kotlin + JPA para registrar usuários, livros e avaliações (nota/comentário), via **console**.

## Funcionalidades

- Cadastrar **usuário**
- Cadastrar **livro** (ISBN como chave primária)
- Avaliar **livro** (nota de 1 a 5 e comentário)
- Listar usuários, livros e avaliações
- Prevenir avaliação duplicada do mesmo usuário para o mesmo livro

---

## 🛠Tecnologias e Conceitos

- **Kotlin**: linguagem principal.
- **JPA (Jakarta Persistence)**: ORM para mapear classes para tabelas, gerenciar entidades e transações.
- **EntityManager**: objeto central do JPA para persistir, buscar e remover entidades.
- **DAO (Data Access Object)**: camada de acesso a dados, isolando o `EntityManager`.
- **Service**: regras de negócio (validações, composição de entidades).
- **Console App**: interface simples com `readln()`.

### Entidades

- `Usuario`: tabela `usuarios` (id auto increment).
- `Livro`: tabela `livros` (usa `isbn` como `@Id`).
- `Avaliacao`: tabela `avaliacoes` com `@ManyToOne` para `Usuario` e `Livro`.

---

## Estrutura de Pastas

```
src/
 └─ main/
    ├─ kotlin/
    │   └─ br/com/projetos/diarioleitura/
    │       ├─ Main.kt
    │       ├─ util/JPAUtil.kt
    │       ├─ util/TransactionUtil.kt
    │       ├─ dao/
    │       │   ├─ UsuarioDAO.kt
    │       │   ├─ LivroDAO.kt
    │       │   └─ AvaliacaoDAO.kt
    │       ├─ model/
    │       │   ├─ Usuario.kt
    │       │   ├─ Livro.kt
    │       │   └─ Avaliacao.kt
    │       └─ service/
    │           └─ service/DiarioLeituraService.kt
    └─ resources/
        └─ META-INF/persistence.xml
```

---

## Configuração do Banco

Por padrão este projeto usa **H2 em memória** para rodar rapidamente (sem instalar banco). Se preferir MySQL/PostgreSQL, troque as propriedades no `persistence.xml`.

---

## Exemplos de uso

- Cadastrar usuário ➜ digite `1`, depois informe o `nome`.
- Cadastrar livro ➜ digite `2`, informe `ISBN`, `Título`, `Autor`.
- Avaliar livro ➜ digite `3`, informe `ID do usuário`, `ISBN`, `Nota (1–5)`, `Comentário`.
- Listar ➜ digite `4`, `5` ou `6`.
- Remover usuário ➜ digite `7`, depois informe o `id`.
- Remover livro ➜ digite `8`, depois informe o `ISBN`.
- Remover avaliação ➜ digite `9`, depois informe o `id`.

---

## Conceitos (explicação simples)

- **Entidade (JPA)**: classe com `@Entity` que vira uma tabela. Ex.: `Usuario`, `Livro`, `Avaliacao`.
- **Chave primária (`@Id`)**: identificador único. No `Livro`, usamos `isbn`; no `Usuario` e `Avaliacao`, `@GeneratedValue`.
- **Relacionamentos (`@ManyToOne`)**: `Avaliacao` pertence a um `Usuario` e a um `Livro`. Cria FKs (`usuario_id`, `livro_isbn`).
- **EntityManager**: faz `persist`, `find`, `remove` e queries.
- **Transação**: bloco atômico (begin/commit/rollback). Há utilitário `transactional` para simplificar.
- **DAO**: centraliza acesso ao banco.
- **Service**: validações e regras (nota 1–5, evitar avaliação duplicada, etc.).
