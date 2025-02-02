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
        ApiLivrosService apiLivrosService = new ApiLivrosService();
        LivroDao livroDao = new LivroDao();


        //apiLivrosService.getLivroModel("os sofrimentos do jovem werther");
        livroDao.lerLivros().forEach(System.out::println);


    }
}