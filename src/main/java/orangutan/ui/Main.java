package orangutan.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import orangutan.Orangutan;

/**
 * A GUI for Orangutan using FXML.
 */
public class Main extends Application {
    private Orangutan orangutan = new Orangutan("./data/orangutan.txt", true);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            //set taskbar icon and title
            //icon taken from www.flaticon.com/free-icon/orangutan_7743169
            Image icon = new Image(this.getClass().getResourceAsStream("/images/icon.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Orangutan");

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setOrangutan(orangutan); //inject Orangutan instance
            fxmlLoader.<MainWindow>getController().welcome(); //display welcome message
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
