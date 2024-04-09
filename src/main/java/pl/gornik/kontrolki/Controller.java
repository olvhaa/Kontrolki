package pl.gornik.kontrolki;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Slider mySlider;

    @FXML
    private Rectangle myRectangle;

    @FXML
    private TextField txtSlider;
    @FXML
    private ScrollBar myScroll;
    @FXML
    private Label lblScroll;

    @FXML
    private Ellipse myEllipse;

    @FXML
    private CheckBox chbRMF;

    @FXML
    private CheckBox chbZET;

    @FXML
    private CheckBox chbEska;

    @FXML
    private TextArea myArea;

    @FXML
    private ListView<String> myList;

    @FXML
    private ChoiceBox<String> myChoice;

    @FXML
    private ComboBox<String> myCombo;

    @FXML
    private Label lblChoice;

    @FXML
    private Label lblCombo;
    @FXML
    private Spinner<Integer> spiMin;

    @FXML
    private Spinner<Integer> spiMax;

    @FXML
    private Label lblInfo;

    @FXML
    private Label lblNumber;

    @FXML
    private Button btnRandom;

    private List<String> radio;

    @FXML
    private Label lblNumbers;

    @FXML
    private Spinner<Integer> spiNumbers;
    @FXML
    private Button btnRandom2;
    @FXML
    private ColorPicker cpColor;

    @FXML
    private Rectangle colorRec;

    @FXML
    private Label lblColor;

    public int min = 50, max = 200;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//     Slider
        mySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                txtSlider.setText(String.valueOf((int) mySlider.getValue()) + "%");
                myRectangle.setWidth(6 * mySlider.getValue());
                myRectangle.setHeight(4 * mySlider.getValue());
            }
        });
        txtSlider.setOnAction(event -> {
            mySlider.setValue(Double.parseDouble(txtSlider.getText()));
        });

// Scroll
        myScroll.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                lblScroll.setText(String.valueOf((int) myScroll.getValue()) + "px");
                myEllipse.setLayoutY(55 + myScroll.getValue());
            }
        });
//CheckBox i ListView
        chbRMF.setOnAction(event -> addValueToArea(chbRMF));
        chbZET.setOnAction(event -> addValueToArea(chbZET));
        chbEska.setOnAction(event -> addValueToArea(chbEska));

        radio = Arrays.asList(chbEska.getText(), chbRMF.getText(), chbZET.getText());
        //myChoice.getItems().add(ch)
        myChoice.getItems().addAll(radio);

        myCombo.getItems().addAll(radio);
        myChoice.setOnAction(event -> {
            lblChoice.setText(myChoice.getValue());
        });

        myCombo.setOnAction(actionEvent -> {
            lblCombo.setText(myCombo.getValue());
        });

//Spinner
        SpinnerValueFactory minValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 50);
        spiMin.setValueFactory(minValue);
        SpinnerValueFactory maxValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(100, 500, 200);
        spiMax.setValueFactory(maxValue);

    SpinnerValueFactory numberOfDraw = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
    spiNumbers.setValueFactory(numberOfDraw);

    spiMin.valueProperty().addListener(new ChangeListener<Integer>() {
        @Override
        public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
             min = spiMin.getValue();
            lblInfo.setText("Generowanie liczb z zakresu od " + min + " do " + max);

        }
    });
        spiMin.setOnMouseClicked(mouseEvent ->
                lblInfo.setText("Generowanie liczb z zakresu od " + spiMin.getValue() + " do " + spiMax.getValue()));

        spiMax.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                max = spiMax.getValue();
                lblInfo.setText("Generowanie liczb z zakresu od " + min + " do " + max);

            }
        });
        spiMax.setOnMouseClicked(mouseEvent ->
                lblInfo.setText("Generowanie liczb z zakresu od " + spiMin.getValue() + " do " + spiMax.getValue())
        );

        btnRandom.setOnAction(event ->{
                    lblNumber.setText(String.valueOf(randomNumber(spiMin.getValue(), spiMax.getValue())));
                }
                );

btnRandom2.setOnAction(event ->{
    ArrayList<Integer> list = new ArrayList<>();
    for(int i = 0; i < spiNumbers.getValue(); i++){
        list.add(randomNumber(spiMin.getValue(), spiMax.getValue()));
    }
    lblNumbers.setText(String.valueOf(list));

});
///// Color Picker
        cpColor.setOnAction(event ->{
            Color color = cpColor.getValue();
            colorRec.setFill(color);
            lblColor.setText(String.valueOf(cpColor.getValue()));
            colorRec.setStroke(color.invert());

          lblColor.setText("#" + String.valueOf(cpColor.getValue()).substring(2,8));
        });
    }


    public int randomNumber(int min, int max){
        return (int)(Math.random() * (max - min)) + min;
    }



    public void addValueToArea(CheckBox checkBox) {
        if (checkBox.isSelected()) {
            myArea.setText(myArea.getText() + checkBox.getText() + "\n");
            myList.getItems().add(checkBox.getText());
        } else {
            myList.getItems().remove(checkBox.getText());
        }
    }

 //   public int
}
