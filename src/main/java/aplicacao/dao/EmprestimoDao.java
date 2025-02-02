package aplicacao.dao;

import aplicacao.models.EmprestimoModel;
import aplicacao.models.LivroModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;


import java.io.File;
import java.sql.Struct;
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
                emprestimos.add(emprestimo);
                objectMapper.writeValue(new File(ARQUIVO_JSON), emprestimos);
                livroDao.atualizarLivroEmprestimo(livro.getTitle(), true);
                System.out.println(livro.getTitle() + ", Foi emprestado para " + obterResponsavel(livro.getTitle()));
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

    public void devolverLivro(int i){
        List<EmprestimoModel> livrosEmprestados = lerEmprestimos();
        String livroDevolucao = livrosEmprestados.get(i).getLivro().getTitle();
        livroDao.atualizarLivroEmprestimo(livroDevolucao, false);
        System.out.println(livroDevolucao + ", Foi devolvido");
        apagarEmprestimo(i);
    }

    private void apagarEmprestimo(int indice) {
        try {
            List<EmprestimoModel> livrosEmprestados = lerEmprestimos();
            livrosEmprestados.remove(indice);
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
