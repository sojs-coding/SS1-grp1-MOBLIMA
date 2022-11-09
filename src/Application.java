import boundary.StartUpUI;
import controller.Initialization;

public class Application {
    public static void main(String[] args) {
        Initialization init = Initialization.initProgram();

        StartUpUI startUpUI = new StartUpUI();
        startUpUI.start();

        init.saveProgram();
    }
}