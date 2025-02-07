package aplicacao.menus;

import aplicacao.models.EmprestimoModel;
import aplicacao.models.LivroModel;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuMain implements menuDao {
    Scanner sc = new Scanner(System.in);

    public void menu(){
        while(true){
            try {
                System.out.println("MENU DE OPÇÕES");
                System.out.println("1 - Registrar Livro");
                System.out.println("2 - Mostrar Livros");
                System.out.println("3 - Apagar Livro");
                System.out.println("4 - Emprestar Livro");
                System.out.println("5 - Devolver Livro");
                System.out.println("0 - Encerrar Sistema");
                int op = sc.nextInt();

                sc.nextLine();
                switch (op){
                    case 1:
                        System.out.println("- Registar Livro -");
                        registrarLivro();
                        break;
                    case 2:
                        System.out.println("- Mostrar Livros -");
                        mostrarLivros();
                        break;
                    case 3:
                        System.out.println("- Apagar Livro -");
                        apagarLivro();
                        break;
                    case 4:
                        System.out.println("- Emprestar Livro -");
                        break;
                    case 5:
                        System.out.println("- Devolver Livro -");
                        break;
                    case 0:
                        System.out.println("- Encerrar Sistema -");
                        return;
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    @Override
    public void registrarLivro() {
        while(true){
            try {
                System.out.println("Digite o titulo do livro");
                System.out.println("0 - para voltar");
                Optional<String> title = sc.nextLine().describeConstable();

                if (title.get().equals("0"))break;
                title.ifPresentOrElse(menuDao.livrosControl::getLivroModel, RuntimeException::new);
                break;
            } catch (Exception e) {
                System.out.println("Error: "+ e.getMessage());
            }

        }

    }

    @Override
    public void mostrarLivros() {
        System.out.println("*Lista de livros*");
        List<LivroModel> listLivros = menuDao.livroDao.lerLivros();
        listLivros.forEach(System.out::println);

        System.out.println("*Lista de livros emprestados*");
        List<EmprestimoModel> listLivrosEm = menuDao.emprestimoDao.lerEmprestimos();
        listLivrosEm.forEach(System.out::println);
    }

    @Override
    public void apagarLivro() {

            System.out.println("Digite o nome do livro");
            System.out.println("0 - para voltar");
            String title = sc.nextLine();

            if (title.equals("0"))return;

            menuDao.livroDao.excluirLivro(title);
    }

    @Override
    public void emprestarLivro() {


    }

    @Override
    public void devolverLivro() {

    }
}
