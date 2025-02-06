package aplicacao.services;

import aplicacao.controlers.LivroController;
import aplicacao.dao.LivroDao;
import aplicacao.models.LivroModel;
import aplicacao.responses.LivroResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApiLivrosService {
                                    //titulo tem que ter todos os espaços em branco com "+"
    public void getLivroModel(String titulo){
        try {
            String tituloPlus = titulo.replace(" ", "+");


            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.googleapis.com/books/v1/volumes?q=" + tituloPlus +"&fields=items(volumeInfo/title,volumeInfo/authors,volumeInfo/publishedDate,volumeInfo/categories)&AIzaSyDJ4X5teNueYu6nTftNLOosa4rP8-g0ZS8=yourAPIKey"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());



            if (response.statusCode() == 200){
                ObjectMapper mapper = new ObjectMapper();

                LivroDao livroDao = new LivroDao();
                LivroController livroController = new LivroController();
                try {
                    String responseBody = response.body();
                    if (responseBody == null || responseBody.trim().isEmpty()) {
                        System.out.println("Resposta da API vazia ou inválida.");
                        return;
                    }

                    LivroResponse livroResponse = mapper.readValue(response.body(), LivroResponse.class);
                    List<LivroResponse.Item> items = livroResponse.getItems();


                    if (items != null){
                       Optional<LivroResponse.Item> resultado = items.stream()
                               .filter(i -> i.getVolumeInfo().getTitle().equalsIgnoreCase(titulo))
                               .findFirst();

                       if (resultado.isPresent()){
                           LivroResponse.Item item = resultado.get();

                           LivroModel livroModel = new LivroModel();
                           livroModel.setTitle(item.getVolumeInfo().getTitle());
                           livroModel.setAuthors(item.getVolumeInfo().getAuthors());
                           livroModel.setCategories(item.getVolumeInfo().getCategories());


                           livroDao.salvarLivro(livroModel);
                           System.out.println("Adicionado com sucesso: \n" + livroModel );
                       } else {
                           System.out.println("Livro com o título '" + titulo + "' não encontrado.");
                           livroController.registrarLivroManualmente(titulo);
                       }

                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage() + " Error");
                }

            } else {
                System.out.println("Erro ao buscar dados da API. Código de resposta: " + response.statusCode());

            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }




}
