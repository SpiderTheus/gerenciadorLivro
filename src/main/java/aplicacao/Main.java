package aplicacao;

import aplicacao.dao.EmprestimoDao;
import aplicacao.menus.MenuMain;
import aplicacao.models.EmprestimoModel;
import aplicacao.models.LivroModel;
import aplicacao.dao.LivroDao;
import aplicacao.services.ApiLivrosService;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello, World!");
        ApiLivrosService apiLivrosService = new ApiLivrosService();
        LivroDao livroDao = new LivroDao();
        EmprestimoDao emprestimoDao = new EmprestimoDao();
        MenuMain menuMain = new MenuMain();

        List<LivroModel> livros = livroDao.lerLivros();


        //emprestimoDao.emprestarLivro(livros.get(1), "Matheus");
        //emprestimoDao.devolverLivro(0);
        //apiLivrosService.getLivroModel("As vantagens de ser invisivel");
        //livroDao.lerLivros().forEach(System.out::println);


        System.out.println("- Gerenciador de Livros -");
        menuMain.menu();
    }
}