package javafxteste;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

public class CadastroPedidoController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label tituloLabel;

    @FXML
    private RadioButton brotoRadioButton;

    @FXML
    private RadioButton grandeRadioButton;

    @FXML
    private RadioButton giganteRadioButton;

    @FXML
    private ComboBox<String> saboresPizzaComboBox;

    @FXML
    private TextArea descricaoSaborTextArea;

    @FXML
    private ComboBox<String> bordaPizzaComboBox;

    @FXML
    private Spinner<Integer> quantidadePizzaSpinner;

    @FXML
    private CheckBox retiradaBalcaoCheckBox;

    @FXML
    private ComboBox<String> bebidaComboBox;

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

    private void applyPhoneMask(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                textField.setText(formatPhone(oldValue, newValue));
                textField.positionCaret(textField.getText().length());
            }
        });
    }

    private void applyDateMask(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                textField.setText(formatDate(oldValue, newValue));
                textField.positionCaret(textField.getText().length());
            }
        });
    }

    private void applyNameMask(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[a-zA-ZÀ-ú\\s]{0,50}")) {
                    textField.setText(oldValue);
                }
            }
        });
    }

    private void applyValueMask(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\,\\d{0,2})?")) {
                    textField.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void initialize() {
        applyPhoneMask(telefoneClienteField);
        applyDateMask(dataPedidoField);
        applyNameMask(nomeClienteField);
        applyValueMask(valorPedidoField);
        applyValueMask(valorTotalPedidoField);
        applyPizzaValues();
        applyBordaValues();
        applyBebidaValues();
        applyDescSaboresValues();
        quantidadePizzaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));

    }

    private void applyDescSaboresValues() {
        saboresPizzaComboBox.setOnAction((event) -> {
            String saborSelecionado = saboresPizzaComboBox.getValue();
            switch (saborSelecionado) {
                case "Mussarela":
                    descricaoSaborTextArea.setText("Sabor clássico da pizza, com queijo mussarela e molho de tomate.");
                    break;
                case "Calabresa":
                    descricaoSaborTextArea
                            .setText("Pizza com fatias de calabresa, queijo mussarela e molho de tomate.");
                    break;
                case "Margherita":
                    descricaoSaborTextArea.setText("Pizza com molho de tomate, queijo mussarela e manjericão.");
                    break;
                case "Frango com Catupiry":
                    descricaoSaborTextArea
                            .setText("Pizza com pedaços de frango e catupiry, queijo mussarela e molho de tomate.");
                    break;
                case "Quatro Queijos":
                    descricaoSaborTextArea.setText("Pizza com queijos mussarela, parmesão, gorgonzola e catupiry.");
                    break;
                default:
                    descricaoSaborTextArea.setText("");
                    break;
            }
        });
    }

    private void applyBebidaValues() {
        // Adiciona as opções de bebida na ComboBox bebidaComboBox
        bebidaComboBox.setItems(FXCollections.observableArrayList(
                "Coca-Cola", "Fanta", "Sprite", "Guaraná", "Água mineral", "Suco de laranja"));
    }

    private void applyBordaValues() {
        // Adiciona as opções de borda na ComboBox bordaPizzaComboBox
        bordaPizzaComboBox.setItems(FXCollections.observableArrayList(
                "Sem borda", "Catupiry", "Cheddar", "Cream cheese"));
    }

    private void applyPizzaValues() {
        // Adiciona os sabores de pizza na ComboBox saboresPizzaComboBox
        saboresPizzaComboBox.setItems(FXCollections.observableArrayList(
                "Mussarela", "Calabresa", "Margherita", "Frango com Catupiry", "Quatro Queijos"));
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

    @FXML
    private void fecharJanela(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private Boolean validarCampos() {
        // Validação do campo nomeClienteField
        if (nomeClienteField.getText().trim().isEmpty() || !validateName(nomeClienteField.getText().trim())) {
            return false;
        }

        // Validação do campo valorPedidoField
        if (valorPedidoField.getText().trim().isEmpty() || !validateValue(valorPedidoField.getText().trim())) {
            return false;
        }
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

    private String formatPhone(String oldValue, String newValue) {
        String formatted = newValue.replaceAll("[^0-9]", "");
        if (formatted.length() > 2) {
            formatted = "(" + formatted.substring(0, 2) + ")" + formatted.substring(2);
        }
        if (formatted.length() > 6) {
            formatted = formatted.substring(0, 5) + " " + formatted.substring(5);
        }
        if (formatted.length() > 12) {
            formatted = formatted.substring(0, 10) + "-" + formatted.substring(10);
        }
        return formatted.length() > 15 ? oldValue : formatted;
    }

    private String formatDate(String oldValue, String newValue) {
        String formatted = newValue.replaceAll("[^0-9]", "");
        if (formatted.length() > 2) {
            formatted = formatted.substring(0, 2) + "/" + formatted.substring(2);
        }
        if (formatted.length() > 5) {
            formatted = formatted.substring(0, 5) + "/" + formatted.substring(5);
        }
        return formatted.length() > 10 ? oldValue : formatted;
    }

    private boolean validateName(String name) {
        return name.matches("^[a-zA-ZÀ-ú]+(\\s[a-zA-ZÀ-ú]+)*$");
    }

    private boolean validateValue(String value) {
        return value.matches("^\\d+(\\.\\d{1,2})?$");
    }

}
