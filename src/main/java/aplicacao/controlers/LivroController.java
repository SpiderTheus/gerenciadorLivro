package aplicacao.controlers;

import aplicacao.models.LivroModel;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LivroController {

    public LivroModel registrarLivro(String q){
        LivroModel livroModel = new LivroModel();

        while (true){
            Scanner sc = new Scanner(System.in);
            try {

                System.out.println("Digite o titulo do livro:");
                String title = sc.nextLine();

                System.out.println("Digite o nome dos autores:(sempre separe por virgula!)");
                String authorNames = sc.nextLine();
                List<String> authorName = Arrays.asList(authorNames.split(","));
                
                System.out.println("Digite o ano de lançamento da edição:");
                Integer firstPublishYear = sc.nextInt();
                sc.nextLine();

                System.out.println("Digite os generos:(sempre separe por virgula!)");
                String subjects = sc.nextLine();
                List<String> subject = Arrays.asList(subjects.split(","));

                livroModel.setTitle(title);
                livroModel.setAuthorName(authorName);
                livroModel.setFirstPublishYear(firstPublishYear);
                livroModel.setSubject(subject);

                sc.close();
                return livroModel;
            } catch (Exception e){
                System.out.println("Algo deu errado, tente novamente!");
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }




    };

}
