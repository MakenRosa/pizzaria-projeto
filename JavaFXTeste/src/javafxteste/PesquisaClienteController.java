package javafxteste;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PesquisaClienteController {

    @FXML
    private TextField buscaClienteField;

    @FXML
    private Button buscarButton;

    @FXML
    private TableView<Cliente> listaClientes;

    @FXML
    private TableColumn<Cliente, String> colunaNome;

    @FXML
    private TableColumn<Cliente, String> colunaTelefone;

    @FXML
    private TableColumn<Cliente, String> colunaEmail;

    @FXML
    private Button fecharButton;

    @FXML
    private Button pesquisaAvancadaButton;

    @FXML
    private void initialize() {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        popularListaExemplos();
    }

    private void popularListaExemplos() {
        if (listaClientes == null) {
            System.out.println("listaClientes não foi inicializado corretamente.");
            return;
        }
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(new Cliente("Maken", "(48)9 96699669", "Maken.gamer@hotmail.com"),
                new Cliente("Jéssica", "(48)9 961889292", "Jessica@gmail.com"),
                new Cliente("Alcione", "(48)9 12332188", "frederico@gmail.com"),
                new Cliente("Martyin", "(48)9 999910660", "martaoDoTi@gmail.com"));

        listaClientes.setItems(clientes);
    }

    @FXML
    private void buscarCliente() {
        if (buscaClienteField == null || buscaClienteField == null) {
            System.out.println("busca cliente deu pau");
            return;
        }

        String busca = buscaClienteField.getText().trim().toLowerCase();

        if (!busca.isEmpty()) {
            FilteredList<Cliente> clientesFiltrados = listaClientes.getItems()
                    .filtered(cliente -> cliente.getNome().toLowerCase().contains(busca) ||
                            cliente.getTelefone().toLowerCase().contains(busca) ||
                            cliente.getEmail().toLowerCase().contains(busca));
            listaClientes.setItems(clientesFiltrados);
        } else {
            popularListaExemplos();
        }
    }

    public static class Cliente {
        private String nome;
        private String telefone;
        private String email;

        public Cliente() {
            this.nome = null;
            this.telefone = null;
            this.email = null;
        }
        
        

        public Cliente(String nome, String telefone, String email) {
            this.nome = new String(nome);
            this.telefone = new String(telefone);
            this.email = new String(email);
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    @FXML
    private void fecharJanela(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
