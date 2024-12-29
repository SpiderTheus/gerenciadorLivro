package aplicacao.models;

import java.util.UUID;

public class EmprestimoModel {

    private String nomeResponsavel;
    private UUID livroId;

    public void emprestar(){
        // fazer o "disponivel" do livro model ficar false;
    }

    public void devolver(){
        // fazer o "disponivel" do livro model ficar true;
    }
}
