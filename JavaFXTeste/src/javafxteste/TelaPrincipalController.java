/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxteste;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Maken
 */
public class TelaPrincipalController implements Initializable {
    @FXML
    private Label varVendidasTotal;

    @FXML
    private Label varVendidasHoje;

    @FXML
    private Button btnAddPedido;

    @FXML 
    private Button btnSair;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        varVendidasTotal.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("200")) {
                abrirPopUp();
            }
        });
    }

    private void abrirPopUp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Limite grátis atingido! Parabéns!!!");
        alert.setContentText("Você atingiu o limite de 200 vendas grátis! A partir de agora, você terá que pagar para continuar vendendo!");
        alert.showAndWait();
    }

    @FXML
    private void addPedidoOnAction(ActionEvent event) {
        JavaFXTeste.abrirTela("CadastroPedido");
    }

    public void incrementarContador(int quantidade) {
        int vendidasTotal = Integer.parseInt(varVendidasTotal.getText());
        int vendidasHoje = Integer.parseInt(varVendidasHoje.getText());
        vendidasTotal += quantidade;
        vendidasHoje += quantidade;
        varVendidasTotal.setText(String.valueOf(vendidasTotal));
        varVendidasHoje.setText(String.valueOf(vendidasHoje));
    }
    
    @FXML
    private void sairOnAction(ActionEvent event) {
        System.exit(0);
    }
}
