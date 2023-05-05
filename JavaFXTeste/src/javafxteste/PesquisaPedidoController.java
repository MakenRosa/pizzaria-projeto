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

public class PesquisaPedidoController {

    @FXML
    private TextField buscaPedidoField;

    @FXML
    private Button buscarButton;

    @FXML
    private TableView<Pedido> listaPedidos;

    @FXML
    private TableColumn<Pedido, String> colunaPedido;

    @FXML
    private TableColumn<Pedido, String> colunaCliente;

    @FXML
    private TableColumn<Pedido, String> colunaDetalhes;

    @FXML
    private Button fecharButton;

    @FXML
    private Button pesquisaAvancadaButton;

    @FXML
    private void initialize() {
        colunaPedido.setCellValueFactory(new PropertyValueFactory<>("pedido"));
        colunaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colunaDetalhes.setCellValueFactory(new PropertyValueFactory<>("detalhes"));

        popularListaExemplos();
    }

    private void popularListaExemplos() {
        if (listaPedidos == null) {
            System.out.println("listaPedidos não foi inicializado corretamente.");
            return;
        }
        ObservableList<Pedido> pedidosExemplo = FXCollections.observableArrayList(
                new Pedido("Pedido 1", "João Silva", "2 Pizzas Grande Calabresa - R$ 60,00"),
                new Pedido("Pedido 2", "Maria Oliveira", "1 Pizza Gigante Quatro Queijos - R$ 45,00"),
                new Pedido("Pedido 3", "José Santos", "3 Pizzas Broto Mussarela - R$ 30,00"),
                new Pedido("Pedido 4", "Ana Costa", "1 Pizza Grande Frango com Catupiry - R$ 40,00"),
                new Pedido("Pedido 5", "Paulo Souza", "2 Pizzas Gigante Margherita - R$ 70,00"));

        listaPedidos.setItems(pedidosExemplo);
    }

    @FXML
    private void buscarPedido() {
        if (buscaPedidoField == null || listaPedidos == null) {
            System.out.println("buscaPedidoField ou listaPedidos não foram inicializados corretamente.");
            return;
        }

        String busca = buscaPedidoField.getText().trim().toLowerCase();

        if (!busca.isEmpty()) {
            FilteredList<Pedido> pedidosFiltrados = listaPedidos.getItems()
                    .filtered(pedido -> pedido.getCliente().toLowerCase().contains(busca) ||
                            pedido.getPedido().toLowerCase().contains(busca) ||
                            pedido.getDetalhes().toLowerCase().contains(busca));
            listaPedidos.setItems(pedidosFiltrados);
        } else {
            popularListaExemplos();
        }
    }

    public static class Pedido {
        private final SimpleStringProperty pedido;
        private final SimpleStringProperty cliente;
        private final SimpleStringProperty detalhes;

        public Pedido(String pedido, String cliente, String detalhes) {
            this.pedido = new SimpleStringProperty(pedido);
            this.cliente = new SimpleStringProperty(cliente);
            this.detalhes = new SimpleStringProperty(detalhes);
        }

        public String getPedido() {
            return pedido.get();
        }

        public void setPedido(String pedido) {
            this.pedido.set(pedido);
        }

        public String getCliente() {
            return cliente.get();
        }

        public void setCliente(String cliente) {
            this.cliente.set(cliente);
        }

        public String getDetalhes() {
            return detalhes.get();
        }

        public void setDetalhes(String detalhes) {
            this.detalhes.set(detalhes);
        }
    }

    @FXML
    private void fecharJanela(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
