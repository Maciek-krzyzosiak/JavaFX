package sample;

import animatefx.animation.Bounce;
import animatefx.animation.BounceIn;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMovieController {
    public Button cancel;
    public Button add_movie;
    public TextField title;
    public TextField rating;
    public Label title_label;
    private Movie movie;

    public void cancel(ActionEvent event) {

        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void addMovie(ActionEvent event) {
         movie = createMovie();
         if(movie == null){
                new Bounce(title_label).play();
                new BounceIn(title_label).play();
         }
         else{
             cancel(event);
         }
    }
    private Movie createMovie(){
        String movieTitle = title.getText();
        if(title.getText().isEmpty()) return null;
        if(rating.getText().isEmpty()) return null;

        try {
            double rating = Double.parseDouble(this.rating.getText());
            return new Movie(movieTitle, rating);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Movie getMovie(){
        return movie;
    }

}
