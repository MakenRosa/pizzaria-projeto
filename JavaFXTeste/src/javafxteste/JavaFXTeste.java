package javafxteste;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXTeste extends Application {

    private static TelaPrincipalController telaPrincipalController;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaPrincipal.fxml"));
        Parent root = loader.load();
        telaPrincipalController = loader.getController();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        // Adicionar evento de fechamento de janela
        stage.setOnCloseRequest(event -> {
            WindowManager.closeAllWindows();
            Platform.exit();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static TelaPrincipalController getTelaPrincipalController() {
        return telaPrincipalController;
    }

    public static void setTelaPrincipalController(TelaPrincipalController telaPrincipalController) {
        JavaFXTeste.telaPrincipalController = telaPrincipalController;
    }
}
