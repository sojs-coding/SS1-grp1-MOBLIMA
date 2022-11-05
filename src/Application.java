import boundary.StartUpUI;
import boundary.movieGoerUI.MovieGoerUI;
import entity.movie.Movie;

public class Application {
    public static void main(String[] args) {
        Initialization init = Initialization.initProgram();

        StartUpUI startUpUI = new StartUpUI();
        startUpUI.start();

        init.saveProgram();
    }
}