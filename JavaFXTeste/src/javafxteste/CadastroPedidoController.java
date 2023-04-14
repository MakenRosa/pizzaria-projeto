package javafxteste;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafxteste.TelaPrincipalController;
import javafx.stage.Stage;



public class CadastroPedidoController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label tituloLabel;

    @FXML
    private Label nomeClienteLabel;

    @FXML
    private TextField nomeClienteField;

    @FXML
    private Label telefoneClienteLabel;

    @FXML
    private TextField telefoneClienteField;

    @FXML
    private Label valorPedidoLabel;

    @FXML
    private TextField valorPedidoField;

    @FXML
    private Label dataPedidoLabel;

    @FXML
    private TextField dataPedidoField;

    @FXML
    private Label formaPagamentoLabel;

    @FXML
    private TextField formaPagamentoField;

    @FXML
    private Label valorTotalPedidoLabel;

    @FXML
    private TextField valorTotalPedidoField;

    @FXML
    private Button cadastrarButton;

    @FXML
    private ToggleGroup formaPagamento;

    @FXML
    private RadioButton radioDinheiro;

    @FXML
    private RadioButton radioPix;

    @FXML
    private RadioButton radioCredito;

    @FXML
    private RadioButton radioDebito;

    @FXML
    private void initialize() {
        // Permitir de 0 a 11 dígitos no campo de telefone os quais serão formatados com
        // máscara de telefone (xx)xxxxx-xxxx
        telefoneClienteField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().isEmpty()) {
                return change;
            }
            Pattern pattern = Pattern.compile("\\d{0,11}");
            if (pattern.matcher(change.getControlNewText()).matches()) {
                return change;
            }
            return null;
        }));

    }

    @FXML
    private void cadastrarPedido() {
        if (validarCampos()) {
            TelaPrincipalController telaPrincipalController = JavaFXTeste.getTelaPrincipalController();
            System.out.println(telaPrincipalController);
            telaPrincipalController.incrementarContador(1);
            Stage stage = (Stage) cadastrarButton.getScene().getWindow();
            stage.close();
        }

    }

    private Boolean validarCampos() {
        if (valorPedidoField.getText().trim().isEmpty()) {
            return false;
        } else {
            try {
                Double.parseDouble(valorPedidoField.getText().trim());
            } catch (Exception e) {
                return false;
            }
        }
        if (dataPedidoField.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

}
