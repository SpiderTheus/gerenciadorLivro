package aplicacao.services;

//    public EnderecoDto getEndereco(String cep) throws IOException, InterruptedException {
//        try {
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://viacep.com.br/ws/"+cep+"/json/")).build();
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            ObjectMapper mapper = new ObjectMapper();
//            enderecoDto = mapper.readValue(response.body(), EnderecoDto.class);
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        return enderecoDto;
//    }
//}


import aplicacao.controlers.LivroController;
import aplicacao.dao.LivroDao;
import aplicacao.models.LivroModel;
import aplicacao.responses.LivroResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiLivrosService {


    public LivroModel getLivroModel(String isbn) throws IOException, InterruptedException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://openlibrary.org/search.json?q=" + isbn + "&fields=title,author_name,first_publish_year,subject"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());



            if (response.statusCode() == 200){
                ObjectMapper mapper = new ObjectMapper();

                LivroResponse livroResponse = mapper.readValue(response.body(), LivroResponse.class);



                LivroModel livroModel = new LivroModel();
                LivroDao livroDao = new LivroDao();
                if (livroResponse.getDocs() != null && !livroResponse.getDocs().isEmpty()) {
                    // Pegando o primeiro livro da lista de docs


                    LivroModel livro = livroResponse.getDocs().get(0);

                    livroModel.setTitle(livro.getTitle());
                    livroModel.setAuthorName(livro.getAuthorName());
                    livroModel.setFirstPublishYear(livro.getFirstPublishYear());
                    livroModel.setSubject(livro.getSubject());



                    livroDao.salvarLivro(livroModel);
                    return livroModel;
                } else if (livroResponse.getNumFound() == 0) {
                    LivroController livroController = new LivroController();

                    System.out.println("Livro não encontrado");
                    System.out.println("Entrando no cadastro manualmente");
                    livroModel = livroController.registrarLivro(livroResponse.getQ());
                    livroDao.salvarLivro(livroModel);
                    return livroModel;
                }

            } else {
                System.out.println("Erro ao buscar dados da API. Código de resposta: " + response.statusCode());
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }




}
