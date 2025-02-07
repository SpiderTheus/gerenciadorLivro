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
        MenuMain menuMain = new MenuMain();

        System.out.println("- Gerenciador de Livros -");
        menuMain.menu();
    }
}