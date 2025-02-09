package aplicacao.controlers;

import aplicacao.dao.LivroDao;
import aplicacao.models.LivroModel;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LivroController {
    LivroModel livroModel = new LivroModel();
    LivroDao livroDao = new LivroDao();
    Scanner sc = new Scanner(System.in);

    public void registrarLivroManualmente(String title){
        System.out.println("* Registrando "+ title +" Manualmente * \n" +
                "(preencha todos os campos como for solicitado)");
                System.out.println("0 - para voltar ao menu");
        while (true){
            try {
                System.out.println("Digite o nome dos autores:(sempre separe por virgula!)");
                String authors = sc.nextLine();
                if (authors.equals("0"))return;
                List<String> authorNames = Arrays.asList(authors.split(","));
                
                System.out.println("Digite o ano de lançamento da edição:");
                Integer publishYear = sc.nextInt();
                if (publishYear.equals(0))return;

                sc.nextLine();

                System.out.println("Quais as categorias:(sempre separe por virgula!)");
                String categories = sc.nextLine();
                if (categories.equals("0"))return;

                List<String> subject = Arrays.asList(categories.split(","));

                livroModel.setTitle(title);
                livroModel.setAuthors(authorNames);
                livroModel.setPublishedDate(publishYear);
                livroModel.setCategories(subject);

                livroDao.salvarLivro(livroModel);
                return;
            } catch (Exception e){
                System.out.println("Algo deu errado, tente novamente!");
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }

    };

}
