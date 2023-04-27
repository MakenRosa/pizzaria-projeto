package javafxteste;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TelaPrincipalController {

    @FXML
    private Label varVendidasTotal;

    @FXML
    private Label varVendidasHoje;

    @FXML
    private Button btnAddPedido;

    @FXML
    private Button btnPesqPedido;

    @FXML
    private Button btnSair;

    public void initialize() {
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
        alert.setContentText(
                "Você atingiu o limite de 200 vendas grátis! A partir de agora, você terá que pagar para continuar vendendo!");
        alert.showAndWait();
    }

    @FXML
    private void addPedidoOnAction(ActionEvent event) {
        WindowManager.openWindow("CadastroPedido");
    }

    @FXML
    private void pesqPedidoOnAction(ActionEvent event) {
        WindowManager.openWindow("PesquisaPedido");
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
        WindowManager.closeAllWindows();
        Platform.exit();
    }
}
