package aplicacao.menus;

import aplicacao.dao.EmprestimoDao;
import aplicacao.dao.LivroDao;
import aplicacao.models.LivroModel;
import aplicacao.services.ApiLivrosService;

import java.util.List;

public interface menuDao {
    LivroDao livroDao = new LivroDao();
    EmprestimoDao emprestimoDao = new EmprestimoDao();
    ApiLivrosService livrosControl = new ApiLivrosService();

    void registrarLivro();
    List<LivroModel> mostrarLivros();
    void apagarLivro();
    void emprestarLivro();
    void devolverLivro();
}
