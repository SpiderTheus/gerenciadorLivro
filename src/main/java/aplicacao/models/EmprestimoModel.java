package aplicacao.models;

import java.time.LocalDate;
import java.util.UUID;

public class EmprestimoModel {

    private String nomeResponsavel;
    private LivroModel livro;

    public EmprestimoModel() {
        //
    }


    public EmprestimoModel(LivroModel livro, String nomeResponsavel) {
        this.livro = livro;
        this.nomeResponsavel = nomeResponsavel;

    }

    public LivroModel getLivro() {
        return livro;
    }

    public void setLivro(LivroModel livro) {
        this.livro = livro;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }


    @Override
    public String toString() {
        return "EmprestimoModel{" +
                "nomeResponsavel='" + nomeResponsavel + '\'' +
                ", livro=" + livro +
                '}';
    }
}
