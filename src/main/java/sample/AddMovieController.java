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
    public Label movie_rating;
    private Movie movie;

    public void cancel(ActionEvent event) {

        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void addMovie(ActionEvent event) {
         movie = createMovie();
         if(movie.getTitle().isEmpty()){
                new Bounce(title_label).play();
                new BounceIn(title_label).play();
         }
         if(Math.abs(movie.getRating() - 10) > 10){
                new Bounce(movie_rating).play();
                new BounceIn(movie_rating).play();
         }

         else{
             cancel(event);
         }
    }
    private Movie createMovie(){
        String movieTitle = title.getText();
        Double rating = -1.0;
        try {
            rating = Double.parseDouble(this.rating.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return new Movie(movieTitle, rating);
    }

    public Movie getMovie(){
        return movie;
    }

}
