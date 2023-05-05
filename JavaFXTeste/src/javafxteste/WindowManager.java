package javafxteste;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WindowManager {

    private static List<Stage> openWindows = new ArrayList<>();

    public static void openWindow(String windowName) {
        try {
            Parent root = FXMLLoader.load(JavaFXTeste.class.getResource(windowName + ".fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            openWindows.add(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeAllWindows() {
        for (Stage stage : openWindows) {
            stage.close();
        }
        openWindows.clear();
    }
}
