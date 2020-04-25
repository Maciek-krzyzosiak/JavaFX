package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Movie {

    private final SimpleStringProperty title;
    private final SimpleDoubleProperty rating;

    public Movie(String title, double rating) {
        this.title = new SimpleStringProperty(title);
        this.rating = new SimpleDoubleProperty(rating);
    }

    public String getTitle() {
        return title.get();
    }

    public double getRating() {
        return rating.get();
    }
}
