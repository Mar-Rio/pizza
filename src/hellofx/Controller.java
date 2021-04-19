package hellofx;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class Controller implements Initializable {

    ObservableList<String> tamanyos
            = FXCollections.observableArrayList("Pequeña", "Mediana", "Familiar");
    ObservableList<String> listaIngredientes
            = FXCollections.observableArrayList("Jamón", "Queso", "Tomate", "Cebolla", "Champiñones", "Olivas");
//    private Label ver;
    @FXML
    private ToggleGroup masa;
    @FXML
    private ComboBox<String> tipo;
    @FXML
    private RadioButton normal;
    @FXML
    private RadioButton integral;
    @FXML
    private Spinner<String> tamanyo;
    @FXML
    private ListView<String> ingredientes;
    @FXML
    private Button boton;
    @FXML
    private TextField listaSeleccion;
    @FXML
    private TextField panelPrecio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory.ListSpinnerValueFactory valores = new SpinnerValueFactory.ListSpinnerValueFactory(tamanyos);
        tamanyo.setValueFactory(valores);
        normal.setUserData("normal");
        integral.setUserData("integral");
        ingredientes.setItems(listaIngredientes);
        tipo.setVisibleRowCount(5);
        tipo.getItems().add("Carbonara");
        tipo.getItems().add("Cuatro Quesos");
        tipo.getItems().add("Barbacoa");
        tipo.getItems().add("Romana");
        ingredientes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tipo.getSelectionModel().selectFirst();
        crearPizza();
    }

    @FXML
    private void mostrarTipos(ActionEvent event) {
    }

    @FXML
    private void elegirMasa(ActionEvent event) {
    }

    @FXML
    private void cambiarTamanyo(MouseEvent event) {
        String seleccionado = tamanyo.getValue();
    }

    @FXML
    private void seleccionarIngredientes(MouseEvent event) {
        int posSeleccionado = ingredientes.getSelectionModel().getSelectedIndex();
        String ingredienteSeleccionado = listaIngredientes.get(posSeleccionado);
    }

    @FXML
    private void mostrarPizza(ActionEvent event) {
        crearPizza();
    }

    private void crearPizza() {
        Pizza pizza = new Pizza();
        pizza.setIngredientesExtra(ingredientes.getSelectionModel().getSelectedItems());
        pizza.setMasa(masa.getSelectedToggle().getUserData().toString());
        pizza.setTamanyo(tamanyo.getValue());
        pizza.setTipo(tipo.getValue());
        listaSeleccion.setText(pizza.composicion());
        panelPrecio.setText("PRECIO: " + String.format("%2f", pizza.calcularPrecio()) + " $$.");
        System.out.println(masa.getSelectedToggle().toString());
        System.out.println(tamanyo.getValue());
        System.out.println(tipo.getValue());
        System.out.println(ingredientes.getSelectionModel().getSelectedItems().toString());
    }

}
