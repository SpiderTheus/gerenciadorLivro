package aplicacao;

import aplicacao.dao.EmprestimoDao;
import aplicacao.models.EmprestimoModel;
import aplicacao.models.LivroModel;
import aplicacao.dao.LivroDao;
import aplicacao.services.ApiLivrosService;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello, World!");

        ApiLivrosService apiLivro = new ApiLivrosService();
        LivroDao livroDao = new LivroDao();
        EmprestimoDao emprestimoDao = new EmprestimoDao();


        //System.out.println(apiLivro.getLivroModel("9788582852477"));

        //livroDao.excluirLivro(2);

        //emprestimoDao.emprestarLivro(0, "Matheus");
        List<LivroModel> livros = livroDao.lerLivros();
        List<EmprestimoModel> empretismos = emprestimoDao.lerEmprestimos();

        int cont = 0;
        System.out.println("Livros");
        for(LivroModel livro : livros ){
            System.out.println(cont + " - " +livro);
            cont++;
        }

        int contE = 0;
        System.out.println("Emprestimos");
        for(EmprestimoModel emprestimo : empretismos ){
            System.out.println(contE + " - " +emprestimo);
            contE++;
        }

    }
}