package aplicacao.responses;

import aplicacao.models.LivroModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LivroResponse {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    public static class Item {
        private VolumeInfo volumeInfo;

        public VolumeInfo getVolumeInfo() {
            return volumeInfo;
        }

        public void setVolumeInfo(VolumeInfo volumeInfo) {
            this.volumeInfo = volumeInfo;
        }

        public static class VolumeInfo {
            private String title;
            private List<String> authors;
            private String publishedDate;
            private List<String> categories;


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

            public String getPublishedDate() {
                return publishedDate;
            }

            public void setPublishedDate(String publishedDate) {
                this.publishedDate = publishedDate;
            }

            public List<String> getCategories() {
                return categories;
            }

            public void setCategories(List<String> categories) {
                this.categories = categories;
            }

            @Override
            public String toString() {
                return "VolumeInfo{" +
                        "title='" + title + '\'' +
                        ", authors=" + authors +
                        ", publishedDate='" + publishedDate + '\'' +
                        ", categories=" + categories +
                        '}';
            }
        }
    }
}

