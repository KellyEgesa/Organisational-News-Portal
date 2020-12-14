package model;

import java.util.Objects;

public class News {
    private String newsInfo;
    private int id;

    public News(String newsInfo){
        this.newsInfo = newsInfo;
    }

    public String getNewsInfo() {
        return newsInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return getId() == news.getId() &&
                Objects.equals(getNewsInfo(), news.getNewsInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNewsInfo(), getId());
    }
}
