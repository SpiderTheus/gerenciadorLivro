package aplicacao.dao;

import aplicacao.models.LivroModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LivroDao {

    private static final String ARQUIVO_JSON = "/home/spider/study/java/gerenciadorLivros/src/data/livros.json";
    private ObjectMapper objectMapper;

    public LivroDao(){
        this.objectMapper = new ObjectMapper();
    }
    public void salvarLivro(LivroModel livro){
        try {
            List<LivroModel> livros = lerLivros();
            livros.add(livro);
            objectMapper.writeValue(new File(ARQUIVO_JSON), livros);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private List<LivroModel> lerLivros() {
        try {
            File file = new File(ARQUIVO_JSON);
            if(file.exists()) {
                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, LivroModel.class);
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
