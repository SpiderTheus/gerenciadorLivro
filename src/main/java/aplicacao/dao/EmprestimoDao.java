package aplicacao.dao;

import aplicacao.models.EmprestimoModel;
import aplicacao.models.LivroModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDao {
    private static final String ARQUIVO_JSON = "/home/spider/study/java/gerenciadorLivros/src/data/livros_emprestados.json";
    private final ObjectMapper objectMapper;

    public EmprestimoDao(){
        this.objectMapper = new ObjectMapper();
    }

    public void emprestarLivro(int indice, String responsavel){
        try {
            LivroDao livroDao = new LivroDao();
            List<LivroModel> livros = livroDao.lerLivros();

            LivroModel livro = livros.get(indice);
            if (livro.isDisponivel()){

                EmprestimoModel emprestimo = new EmprestimoModel(livro, responsavel);


                List<EmprestimoModel> emprestimos = lerEmprestimos();
                emprestimos.add(emprestimo);
                objectMapper.writeValue(new File(ARQUIVO_JSON), emprestimos);

                livroDao.excluirLivro(indice);


            }

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
