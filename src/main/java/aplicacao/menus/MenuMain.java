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
                        emprestarLivro();
                        break;
                    case 5:
                        System.out.println("- Devolver Livro -");
                        devolverLivro();
                        break;
                    case 0:
                        System.out.println("- Encerrando Sistema -");
                        System.out.println("- Seus dados foram salvos -");
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
                if(title.isPresent()){
                    if (title.get().equals("0"))break;
                    title.ifPresentOrElse(menuDao.livrosControl::getLivroModel, RuntimeException::new);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: "+ e.getMessage());
            }
        }
    }

    @Override
    public void mostrarLivros() {
        System.out.println("*Lista de livros*");
        List<LivroModel> listLivros = menuDao.livroDao.lerLivros();
        listLivros.sort((l1, l2) -> l1.getTitle().compareToIgnoreCase(l2.getTitle()));
        listLivros.forEach(System.out::println);

        System.out.println("*Lista de livros emprestados*");
        List<EmprestimoModel> listLivrosEm = menuDao.emprestimoDao.lerEmprestimos();
        listLivrosEm.sort((l1, l2) -> l1.getNomeResponsavel().compareToIgnoreCase(l2.getNomeResponsavel()));
        listLivrosEm.forEach(System.out::println);
    }

    @Override
    public void apagarLivro() {
            mostrarLivros();
            System.out.println("Digite o nome do livro:");
            System.out.println("0 - para voltar");

            String title = sc.nextLine();
            if (title.equals("0"))return;

            menuDao.livroDao.excluirLivro(title);
    }

    @Override
    public void emprestarLivro() {
        System.out.println("Livros disponíveis");
        menuDao.livroDao.lerLivros().stream().filter(l -> !l.isEmprestado()).forEach(System.out::println);
        System.out.println("Digite o titulo do livro:");
        System.out.println("0 - para voltar");
        String title = sc.nextLine();

        if(title.equals("0")) return;
        Optional<LivroModel> livro = menuDao.livroDao.obterLivro(title);
        if(livro.isPresent()){
            System.out.println("Digite o nome do responsável:");
            String resp = sc.nextLine();
            menuDao.emprestimoDao.emprestarLivro(livro.get(), resp);
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    @Override
    public void devolverLivro() {
        System.out.println("Qual livro você deseja devolver");
        menuDao.emprestimoDao.lerEmprestimos().forEach(System.out::println);
        System.out.println("Digite o titulo:");
        String title = sc.nextLine();

        if(title.equals("0")) return;
        menuDao.emprestimoDao.devolverLivro(title);

    }
}
