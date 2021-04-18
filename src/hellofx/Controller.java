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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class Controller implements Initializable {

    ObservableList<String> tamanyos
            = FXCollections.observableArrayList("pequeña", "mediana", "familiar");
    ObservableList<String> listaIngredientes
            = FXCollections.observableArrayList("Jamón", "Queso", "Tomate", "Cebolla", "Campiñones", "Olivas");
    private Label ver;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory.ListSpinnerValueFactory valores = new SpinnerValueFactory.ListSpinnerValueFactory(tamanyos);
        tamanyo.setValueFactory(valores);
        normal.setUserData("normal");
        integral.setUserData("integral");
        ingredientes.setItems(listaIngredientes);
//        tipo.setVisibleRowCount(4);
//        tipo.getItems().add("carbonara");
//        tipo.getItems().add("cuatro quesos");
//        tipo.getItems().add("barbacoa");
//        tipo.getItems().add("romana");
     

    }

    @FXML
    private void mostrarTipos(ActionEvent event) {
    }

    @FXML
    private void elegirMasa(ActionEvent event) {
        Toggle seleccionado = masa.getSelectedToggle();
        if (seleccionado.getUserData().equals("normal")) {
        }
    }

    @FXML
    private void cambiarTamanyo(MouseEvent event) {
        String seleccionado = tamanyo.getValue();
    }

    private void seleccionarIngredientes(MouseEvent event) {
        int posSeleccionado = ingredientes.getSelectionModel().getSelectedIndex();
        String ingredienteSeleccionado = listaIngredientes.get(posSeleccionado);
    }

    @FXML
    private void mostrarPizza(ActionEvent event) {
    }  
   
}
