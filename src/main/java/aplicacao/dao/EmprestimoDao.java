package aplicacao.dao;

import aplicacao.models.EmprestimoModel;
import aplicacao.models.LivroModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmprestimoDao {
    private static final String ARQUIVO_JSON = "/home/spider/study/java/gerenciadorLivros/src/data/livros_emprestados.json";
    private final ObjectMapper objectMapper;
    LivroDao livroDao = new LivroDao();

    public EmprestimoDao(){
        this.objectMapper = new ObjectMapper();
    }

    public void emprestarLivro(LivroModel livro, String responsavel){
        try {
            if (!livro.isEmprestado()){
                EmprestimoModel emprestimo = new EmprestimoModel(livro, responsavel);
                List<EmprestimoModel> emprestimos = lerEmprestimos();
                emprestimo.getLivro().setEmprestado(true);
                emprestimos.add(emprestimo);
                objectMapper.writeValue(new File(ARQUIVO_JSON), emprestimos);
                livroDao.atualizarLivroEmprestimo(livro.getTitle(), true);
                System.out.println(livro.getTitle() + " Emprestado para " + obterResponsavel(livro.getTitle())+".");
            } else {
                System.out.println("Livro: "+ livro.getTitle()+", já foi emprestado para: " + obterResponsavel(livro.getTitle()));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String obterResponsavel(String title) {
        List<EmprestimoModel> livrosEmprestados = lerEmprestimos();
        Optional<String> nomeResponsavel = livrosEmprestados.stream()
                .filter(e -> e.getLivro().getTitle().equalsIgnoreCase(title))
                .map(EmprestimoModel::getNomeResponsavel).findFirst();

        return nomeResponsavel.orElse("Responsavel não identificado");
    }

    public void devolverLivro(String title){
        try {
            List<EmprestimoModel> livrosEmprestados = lerEmprestimos();

            String livroDevolucao = livrosEmprestados.stream().filter(e -> e.getLivro().getTitle().equalsIgnoreCase(title)).findFirst().get().getLivro().getTitle();
            livroDao.atualizarLivroEmprestimo(livroDevolucao, false);
            System.out.println(livroDevolucao + ", Foi devolvido");
            apagarEmprestimo(title);

        } catch (Exception e) {
            System.out.println("Erro ao devolver livro");
        }

    }

    private void apagarEmprestimo(String title) {
        try {
            List<EmprestimoModel> livrosEmprestados = lerEmprestimos();
            livrosEmprestados.removeIf(e -> e.getLivro().getTitle().equalsIgnoreCase(title));
            objectMapper.writeValue(new File(ARQUIVO_JSON), livrosEmprestados);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<EmprestimoModel> lerEmprestimos() {
        try {
            File file = new File(ARQUIVO_JSON);
            if(file.exists()) {
                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, EmprestimoModel.class);
                return  objectMapper.readValue(file, listType);
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
