/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package javafxteste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maken
 */
public class JavaFXTeste extends Application {

    private static TelaPrincipalController telaPrincipalController;

    private static List<Stage> telasAbertas = new ArrayList<>();
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaPrincipal.fxml"));
        Parent root = loader.load();
        telaPrincipalController = loader.getController();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    public static void abrirTela(String nomeTela) {
        try {
            Parent root = FXMLLoader.load(JavaFXTeste.class.getResource(nomeTela + ".fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            telasAbertas.add(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static TelaPrincipalController getTelaPrincipalController() {
        return telaPrincipalController;
    }

    public static void setTelaPrincipalController(TelaPrincipalController telaPrincipalController) {
        JavaFXTeste.telaPrincipalController = telaPrincipalController;
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        for (Stage stage : telasAbertas) {
            stage.close();
        }
    }
}
