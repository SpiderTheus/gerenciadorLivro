package aplicacao.responses;

import aplicacao.models.LivroModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LivroResponse {

    private int numFound;
    private int start;
    private boolean numFoundExact;

    private List<LivroModel> docs;
    @JsonProperty("num_found")
    private int numFoundResponse;

    private  String q;

    private int offset;

    public int getNumFound() {
        return numFound;
    }

    public void setNumFound(int numFound) {
        this.numFound = numFound;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public boolean isNumFoundExact() {
        return numFoundExact;
    }

    public void setNumFoundExact(boolean numFoundExact) {
        this.numFoundExact = numFoundExact;
    }

    public List<LivroModel> getDocs() {
        return docs;
    }

    public void setDocs(List<LivroModel> docs) {
        this.docs = docs;
    }


    public int getNumFoundResponse() {
        return numFoundResponse;
    }

    public void setNumFoundResponse(int numFoundResponse) {
        this.numFoundResponse = numFoundResponse;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
