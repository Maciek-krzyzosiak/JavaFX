package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML public TableColumn<Movie, String> movie_name;
    @FXML public TableColumn<Movie, Double> imbd_score;
    @FXML public Button add_movie;
    @FXML public Button delete_move;
    @FXML private TableView<Movie> movie_table;
    @FXML private Button movies_button;
    private Movie dialogMovie;
    private final ObservableList<Movie> data = FXCollections.observableArrayList();

    public ArrayList<Movie> getMovies(){
        ArrayList<Movie> movies = new ArrayList<>();
        Connection connection = Jsoup.connect("https://www.imdb.com/chart/top/");
        try{
            Elements titleColumn = connection.get().getElementsByClass("titleColumn");
            Elements imbdRating = connection.get().getElementsByClass("ratingColumn imdbRating");
            for(int i = 0 ; i < titleColumn.size(); i++){
                String title = titleColumn.get(i).text().replaceAll("(.*)\\. ", "");
                String rating = imbdRating.get(i).text();

                movies.add(new Movie(title, Double.parseDouble(rating)));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    @FXML
    public void fillTable(ActionEvent event){
        data.addAll(getMovies());
        movie_table.setItems(data);
    }

    @FXML
    public void addMovie(ActionEvent event){
        CreateStage dialog = new CreateStage();
        Movie movie = dialog.getMovie();
        if(movie == null) return;

        movie_table.getItems().add(dialog.getMovie());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movie_name.setCellValueFactory(new PropertyValueFactory<>("title"));
        imbd_score.setCellValueFactory(new PropertyValueFactory<>("rating"));
        movie_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    class CreateStage {

        private AddMovieController controller;
        public CreateStage() {

            Parent root = null;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add_movie_stage.fxml"));
                root = fxmlLoader.load();
                controller = fxmlLoader.getController();

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Movie creator");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        }

        public Movie getMovie() {
            return controller.getMovie();
        }
    }
}
