package aplicacao.menus;

import aplicacao.dao.EmprestimoDao;
import aplicacao.dao.LivroDao;
import aplicacao.services.ApiLivrosService;


public interface menuDao {
    LivroDao livroDao = new LivroDao();
    EmprestimoDao emprestimoDao = new EmprestimoDao();
    ApiLivrosService livrosControl = new ApiLivrosService();

    void registrarLivro();
    void mostrarLivros();
    void apagarLivro();
    void emprestarLivro();
    void devolverLivro();
}
