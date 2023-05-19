/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxteste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import javafxteste.PesquisaClienteController.Cliente;
import javax.swing.JOptionPane;
//import org.json.JSONObject;

/**
 *
 * @author Martao
 */


public class CadastroClienteController implements Initializable {
    

    @FXML
    private TextField nomeClienteField;
    
    @FXML
    private TextField cepClienteField;
    
    @FXML
    private TextField emailClienteField;

    @FXML
    private TextField telefoneClienteField;
    
    @FXML
    private TextField ruaClienteField;
    
    @FXML
    private TextField bairroClienteField;
    
    @FXML
    private TextField cidadeClienteField;
    
    @FXML
    private ComboBox<String> estadoClienteCombo;
    
    @FXML
    private TextField numeroClienteField;
    
    @FXML
    private TextField complementoClienteField;
    
    @FXML
    private TextArea observacaoClienteField;
    
    @FXML
    private Button cadastrarButton;
    
    Cliente cliente = new Cliente();

    
    

    @FXML
    private void fecharJanela(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
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
    
    private void applyPhoneMask(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                textField.setText(formatPhone(oldValue, newValue));
                textField.positionCaret(textField.getText().length());
            }
        });
    }   
    
      @FXML
    private void cadastrarCliente() {
        if (validarCampos()) {
            cliente.setNome(nomeClienteField.getText().trim());
            cliente.setTelefone(telefoneClienteField.getText().trim());
            cliente.setEmail(emailClienteField.getText().trim());
            Stage stage = (Stage) cadastrarButton.getScene().getWindow();
            stage.close();
        }

    }
        
    
    private Boolean validarCampos() {
        if (nomeClienteField.getText().trim().isEmpty() || !validateName(nomeClienteField.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Nome do cliente invalido");
            return false;
        }

        if (cepClienteField.getText().trim().isEmpty() || !validateValue(cepClienteField.getText().trim())) {
            JOptionPane.showMessageDialog(null, "CEP invalido");
            return false;
        }
        
        if (telefoneClienteField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Telefone invalido");
            return false;
        }
        
        if (numeroClienteField.getText().trim().isEmpty() || !validateValue(numeroClienteField.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Numero invalido");
            return false;
        }
     
        if (emailClienteField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email invalido");
            return false;
        }
        if (ruaClienteField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Rua invalido");
            return false;
        }
        if (bairroClienteField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bairro invalido");
            return false;
        }
        if (cidadeClienteField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cidade invalido");
            return false;
        }
        if (complementoClienteField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complemento invalido");
            return false;
        }
        if (observacaoClienteField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Observação invalido");
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


    private boolean validateName(String name) {
        return name.matches("^[a-zA-ZÀ-ú]+(\\s[a-zA-ZÀ-ú]+)*$");
    }

    private boolean validateValue(String value) {
        return value.matches("^\\d+(\\.\\d{1,2})?\\)?$");
    }
    
    
    private void applyStateValues() {
    estadoClienteCombo.setItems(FXCollections.observableArrayList(
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", 
            "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", 
            "RS", "RO", "RR", "SC", "SP", "SE", "TO"));
    }
    

    private void preencherEndereco() {
    String cep = cepClienteField.getText().replaceAll("[^0-9]", "");
//    if (cep.length() != 8) {
//        JOptionPane.showMessageDialog(null, "CEP inválido");
//        return;
//    }
//    
//    try {
//        URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        
//        if (conn.getResponseCode() != 200) {
//            JOptionPane.showMessageDialog(null, "CEP não encontrado");
//            return;
//        }
//        
//        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        StringBuilder sb = new StringBuilder();
//        String output;
//        while ((output = br.readLine()) != null) {
//            sb.append(output);
//        }
//        
////        JSONObject obj = new JSONObject(sb.toString());
////        ruaClienteField.setText(obj.getString("logradouro"));
////        bairroClienteField.setText(obj.getString("bairro"));
////        cidadeClienteField.setText(obj.getString("localidade"));
////        estadoClienteCombo.setValue(obj.getString("uf"));
//        
//        // Você pode preencher o número com um valor padrão, por exemplo, 0
//        numeroClienteField.setText("0");
//        
//        conn.disconnect();
//    } catch (IOException e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(null, "Erro ao buscar CEP");
//    }
}


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        applyPhoneMask(telefoneClienteField);
        applyNameMask(nomeClienteField);
        applyStateValues();
    }    
}
