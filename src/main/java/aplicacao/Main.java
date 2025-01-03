package aplicacao;

import aplicacao.models.LivroModel;
import aplicacao.dao.LivroDao;
import aplicacao.services.ApiLivrosService;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello, World!");

        ApiLivrosService apiLivro = new ApiLivrosService();
        LivroDao livroDao = new LivroDao();


        System.out.println(apiLivro.getLivroModel("9786555601787"));

        //livroDao.excluirLivro(1);
        List<LivroModel> livros = livroDao.lerLivros();

        int cont = 0;
        for(LivroModel livro : livros ){
            System.out.println(cont + " - " +livro);
            cont++;
        }


    }
}