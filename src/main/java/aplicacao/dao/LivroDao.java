package aplicacao.dao;

import aplicacao.models.LivroModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivroDao {

    private static final String ARQUIVO_JSON = "/home/spider/study/java/gerenciadorLivros/src/data/livros.json";
    private final ObjectMapper objectMapper;

    public LivroDao(){
        this.objectMapper = new ObjectMapper();
    }
    public void salvarLivro(LivroModel livro){
        try {
            List<LivroModel> livros = lerLivros();

            if(livros.contains(livro)){
                System.out.println(livro.getTitle() + ", Esse livro já está Registrado");
                return;
            } else if (livroExiste(livro.getTitle())) {
                System.out.println(livro.getTitle() + ", esse livro está Registrado mas foi emprestado");
                return;
            }

            livros.add(livro);
            objectMapper.writeValue(new File(ARQUIVO_JSON), livros);
            System.out.println("Adicionado com sucesso: \n" + livro);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<LivroModel> lerLivros() {
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

    public void excluirLivro(String title){
        try {
            List<LivroModel> livros = lerLivros();
            boolean isRm = livros.removeIf(l -> l.getTitle().equalsIgnoreCase(title));
            if (!isRm){
                System.out.println(title + ", Esse livro não existe em memoria");
                return;
            }
            objectMapper.writeValue(new File(ARQUIVO_JSON), livros);
            System.out.println("Livro apagado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarLivroEmprestimo(String title, Boolean isEmprest) {
        try {
            List<LivroModel> livros = lerLivros();

            livros.stream().filter(l -> l.getTitle().equalsIgnoreCase(title))
                    .forEach(l -> l.setEmprestado(isEmprest));
            objectMapper.writeValue(new File(ARQUIVO_JSON), livros);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean livroExiste(String t) {
        List<LivroModel> livros = lerLivros();
        return livros.stream()
                .anyMatch(l -> l.getTitle().equalsIgnoreCase(t));
    }

    public Optional<LivroModel> obterLivro(String title){
        List<LivroModel> livros = lerLivros();
        return livros.stream().filter(l -> l.getTitle().equalsIgnoreCase(title)).findFirst();
    }
}
