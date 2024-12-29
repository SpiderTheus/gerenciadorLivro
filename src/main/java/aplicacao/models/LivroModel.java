package aplicacao.models;

/*
 - isbn: String   |
| - titulo: String |
| - autor: String  |
| - genero: String |
| - ano: int       |
| - disponivel: boolean |
| - emprestimos: int    |
 */


import java.util.UUID;

public class LivroModel {
    //randomUUID()
    private UUID livroId;
    private String isbn;
    private String autor;
    private String genero;
    private String data_lancamento;
    private boolean disponivel;

    public LivroModel(String isbn, String autor, String genero, String data_lancamento, boolean disponivel) {
        this.livroId = UUID.randomUUID();
        this.isbn = isbn;
        this.autor = autor;
        this.genero = genero;
        this.data_lancamento = data_lancamento;
        this.disponivel = disponivel;
    }

    public UUID getLivroId() {
        return livroId;
    }

    public void setLivroId(UUID livroId) {
        this.livroId = livroId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(String data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
