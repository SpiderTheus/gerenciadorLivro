package aplicacao.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class LivroModel {

    public boolean setDisponivel;
    private String title;

    @JsonProperty("author_name")
    private List<String> authorName;

    @JsonProperty("first_publish_year")
    private Integer firstPublishYear;

    private List<String> subject;

    private boolean isDisponivel;


    public LivroModel() {
        this.isDisponivel = true;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthorName() {
        return authorName;
    }

    public void setAuthorName(List<String> authorName) {
        this.authorName = authorName;
    }

    public Integer getFirstPublishYear() {
        return firstPublishYear;
    }

    public void setFirstPublishYear(Integer firstPublishYear) {
        this.firstPublishYear = firstPublishYear;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    public boolean isDisponivel() {
        return isDisponivel;
    }

    public void setDisponivel(boolean disponivel) {
        isDisponivel = disponivel;
    }

    @Override
    public String toString() {
        return "LivroModel{" +
                "title='" + title + '\'' +
                ", authorName=" + authorName +
                ", firstPublishYear=" + firstPublishYear +
                ", subject=" + subject +
                ", isDisponivel=" + isDisponivel +
                '}';
    }
}
