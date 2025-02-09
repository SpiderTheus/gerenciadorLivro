package aplicacao.models;

import java.util.List;
import java.util.Objects;

public class LivroModel {

    private String title;
    private List<String> authors;
    private Integer publishedDate;
    private List<String> categories;
    private boolean emprestado;

    public LivroModel() {
        this.emprestado = false;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public Integer getPublishedDate() {
        return publishedDate;
    }
    public void setPublishedDate(Integer publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<String> getCategories() {
        return categories;
    }
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public boolean isEmprestado() {
        return emprestado;
    }
    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LivroModel that = (LivroModel) o;
        return emprestado == that.emprestado && Objects.equals(title, that.title) && Objects.equals(authors, that.authors) && Objects.equals(publishedDate, that.publishedDate) && Objects.equals(categories, that.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, publishedDate, categories, emprestado);
    }

    @Override
    public String toString() {
        return "titulo='" + title + '\'' +
                ", autores=" + authors +
                ", Data de publicação=" + publishedDate +
                ", categorias=" + categories +
                ", emprestado=" + emprestado +
                '}';
    }
}
