package aplicacao;

import aplicacao.services.ApiLivrosService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello, World!");

        ApiLivrosService apiLivro = new ApiLivrosService();

        System.out.println(apiLivro.getLivroModel("9786555355208"));
    }
}