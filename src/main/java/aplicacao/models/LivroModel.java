package aplicacao.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class LivroModel {

    private String title;
    private List<String> authors;
    private Integer publishedDate;
    private List<String> categories;
    private boolean emprestado;

    public LivroModel() {
    }

    public LivroModel(String title, List<String> authors, Integer publishedDate, List<String> categories) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.categories = categories;
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
    public String toString() {
        return "LivroModel{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", publishedDate=" + publishedDate +
                ", categories=" + categories +
                ", emprestado=" + emprestado +
                '}';
    }


}
