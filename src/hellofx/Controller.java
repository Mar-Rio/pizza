package hellofx;

import Modelo.Pedidos;
import Modelo.Pizza;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
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
    ObservableList<String> listaDescripcionPizzas
            = FXCollections.observableArrayList();

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
    private TextArea listaSeleccion;
    @FXML
    private TextField panelPrecio;
    @FXML
    private StackPane fondo;
    @FXML
    private CheckBox Gratinar;
    @FXML
    private CheckBox incluirBebida;
    @FXML
    private Label pizzaEnProceso;
    @FXML
    private Button botonNuevaPizza;
    @FXML
    private ListView<String> pizzasCreadas;
    @FXML
    private Button guardar;
    @FXML
    private Label precioParcial;
    @FXML
    private ImageView thumbsUp;
    @FXML
    private ImageView pizza1;
    private Pizza pizza;
    private Pedidos pedido;
    @FXML
    private Button enviarPedido;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory.ListSpinnerValueFactory valores = new SpinnerValueFactory.ListSpinnerValueFactory(tamanyos);
        tamanyo.setValueFactory(valores);

        normal.setUserData("Normal");
        integral.setUserData("Integral");
        ingredientes.setItems(listaIngredientes);
        tipo.setVisibleRowCount(5);
        tipo.getItems().add("Carbonara");
        tipo.getItems().add("Cuatro Quesos");
        tipo.getItems().add("Barbacoa");
        tipo.getItems().add("Romana");
        ingredientes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tipo.getSelectionModel().selectFirst();
        Gratinar.setUserData("gratinada");
        incluirBebida.setUserData("incluir bebida");
        pedido = new Pedidos();
        pizza = new Pizza();
        System.out.println(tamanyo.getValue());
        crearPizza();

    }

    @FXML
    private void mostrarTipos(ActionEvent event) {
        crearPizza();
    }

    @FXML
    private void elegirMasa(ActionEvent event) {
        crearPizza();
    }

    @FXML
    private void cambiarTamanyo(MouseEvent event) {
        crearPizza();
    }

    @FXML
    private void seleccionarIngredientes(MouseEvent event) {
        crearPizza();
    }

    private void mostrarPizza(ActionEvent event) {
        crearPizza();
    }

    private void crearPizza() {
        pizzaEnProceso.setText("Pizza " + pizza.getNumeroPizza());
        pizza.setIngredientesExtra(ingredientes.getSelectionModel().getSelectedItems());
        pizza.setMasa(masa.getSelectedToggle().getUserData().toString());
        pizza.setTamanyo(tamanyo.getValue());
        pizza.setTipo(tipo.getValue());
        if (incluirBebida.isSelected()) {
            pizza.setBebida(incluirBebida.getText());
        } else {
            pizza.setBebida("");
        }
        if (Gratinar.isSelected()) {
            pizza.setGratinado(Gratinar.getText());
        } else {
            pizza.setGratinado("");
        }
        listaSeleccion.setText(pizza.composicion());
        pizza.setDescripcionPizza(pizza.composicion());
        if (pedido.getPizzasPedido().isEmpty()) {
            panelPrecio.setText(String.format("A Pagar: %.2f$$.", pizza.calcularPrecio()));
            precioParcial.setText("");
        } else {
            panelPrecio.setText(String.format("Total: %.2f$$", pedido.getPrecioPedido() + pizza.calcularPrecio()));
            precioParcial.setText("Pizza " + pizza.getNumeroPizza() + ":  " + String.format("%.2f$$", pizza.calcularPrecio()));
        }
    }

    @FXML
    private void incluirGratinado(ActionEvent event) {
        if (Gratinar.isSelected()) {
            pizza.setGratinado(Gratinar.getUserData().toString());
            System.out.println(Gratinar.getUserData().toString());
            crearPizza();
        } else {
            pizza.setGratinado("");
            crearPizza();
        }
    }

    @FXML
    private void sumarBebida(ActionEvent event) {
        if (incluirBebida.isSelected()) {
            pizza.setBebida(incluirBebida.getText());
            crearPizza();
        } else {
            pizza.setBebida("");
            crearPizza();
        }
    }

    @FXML
    private void eliminarPizza(MouseEvent event) {
        Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
        dialogoAlerta.setTitle("¿Vas a eliminar la siguiente pizza del pedido?");
        dialogoAlerta.setHeaderText(pizzasCreadas.getSelectionModel().getSelectedItem());
        List keys = new ArrayList(pedido.getPizzasPedido().keySet());
        dialogoAlerta.setContentText(((Pizza) keys.get(pizzasCreadas.getSelectionModel().getSelectedIndex())).getDescripcionPizza());

        Optional<ButtonType> confirmacion = dialogoAlerta.showAndWait();
        if (confirmacion.get() == ButtonType.OK) {
            int indice = pizzasCreadas.getSelectionModel().getSelectedIndex();
            pedido.eliminarPizza(indice);
            listaDescripcionPizzas.remove(indice);
            for (Entry<Pizza, Double> pizzaGuardada : pedido.getPizzasPedido().entrySet()) {
                if (pizzaGuardada.getKey().getNumeroPizza() > indice) {
                    pizzaGuardada.getKey().setNumeroPizza(pizzaGuardada.getKey().getNumeroPizza() - 1);
                }
            }
            Pizza.contador--;
            pizza.setNumeroPizza(pizza.getNumeroPizza() - 1);
            listaDescripcionPizzas.clear();
            for (Entry<Pizza, Double> pizzaGuardada : pedido.getPizzasPedido().entrySet()) {
                String nombrePizza = pizzaGuardada.getKey().getNumeroPizza() + " " + pizzaGuardada.getKey().getTipo() + " "
                        + pizzaGuardada.getKey().getMasa() + " " + pizzaGuardada.getKey().getTamanyo() + " "
                        + String.format("%.2f", pizzaGuardada.getKey().calcularPrecio()) + "$";
                if (!pizzaGuardada.getKey().getBebida().isEmpty()) {
                    nombrePizza += " +Bebida";
                }
                listaDescripcionPizzas.add(nombrePizza);
            }
            pizzasCreadas.setItems(listaDescripcionPizzas);
            crearPizza();
        }
    }

    private void resetear() {
        listaSeleccion.clear();
        SpinnerValueFactory.ListSpinnerValueFactory valores = new SpinnerValueFactory.ListSpinnerValueFactory(tamanyos);
        tamanyo.setValueFactory(valores);
        tipo.getSelectionModel().selectFirst();
        if (integral.isSelected()) {
            normal.setSelected(true);
        }
        ingredientes.getSelectionModel().clearSelection();
        Gratinar.setSelected(false);
        incluirBebida.setSelected(false);
        crearPizza();
    }

    @FXML
    private void guardarPizza(ActionEvent event) {
        String nombrePizza = pizza.getNumeroPizza() + " " + pizza.getTipo() + " " + pizza.getMasa()
                + " " + pizza.getTamanyo() + " " + String.format("%.2f", pizza.calcularPrecio()) + "$";
        if (incluirBebida.isSelected()) {
            nombrePizza += " +Bebida";
        }
        listaDescripcionPizzas.add(nombrePizza);
        pizzasCreadas.setItems(listaDescripcionPizzas);
        pedido.añadirPizzaPedido(pizza, pizza.calcularPrecio());
        pizza = new Pizza();
        resetear();
    }

    @FXML
    private void resetearPedido(ActionEvent event) {
        pedido.eliminarPizzasPedido();
        listaDescripcionPizzas.clear();
        pizzasCreadas.setItems(listaDescripcionPizzas);
        Pizza.contador = 2;
        pizza.setNumeroPizza(1);
        resetear();
    }

    @FXML
    private void mostrarPedido() {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle("A COCINAR!!!");
        dialogo.setHeaderText(null);
        if (pedido.getPizzasPedido().isEmpty()) {
            dialogo.setContentText("Guarda primero las pizzas que quieras ordenar!!!");
        } else {
            String aCocina = "Pedido: " + pedido.getNumeroPedido();
            int i = 0;
            for (Entry<Pizza, Double> entrada : pedido.getPizzasPedido().entrySet()) {
                aCocina += "\n" + listaDescripcionPizzas.get(i);
                aCocina += "\n" + entrada.getKey().getDescripcionPizza();
                aCocina += "\nPrecio del Pedido: " + String.format("%.2f$$", entrada.getValue()) + "\n\n";
                i++;
            }
            dialogo.setContentText(aCocina);
        }
        dialogo.showAndWait();

    }
}
