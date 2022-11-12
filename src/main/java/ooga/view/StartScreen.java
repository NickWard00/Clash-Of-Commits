package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */

public class StartScreen {
    //load images and constants
    public static final ResourceBundle images = ResourceBundle.getBundle(
            "ResourceBundles.Images");
    public static final ResourceBundle constants = ResourceBundle.getBundle(
            "ResourceBundles.ViewConstants");

    public static final ResourceBundle styles = ResourceBundle.getBundle("ResourceBundles.Stylesheets");
    private ResourceBundle labels = ResourceBundle.getBundle(
            "ResourceBundles.LabelsBundle");

    public static final int SCREEN_SIZE = Integer.parseInt(constants.getString("screenSize"));

    private Button startGame;
    private ImageView background;
    private Pane startGamePane;
    private Stage currentStage;

    private ComboBox languageSelector;

    private HBox buttonRow;

    private final Map<String, String> languageMap= Map.of(
            labels.getString("eng"), "setEnglish",
            labels.getString("span"), "setSpanish",
            labels.getString("germ"),"setGerman",
            labels.getString("sim"), "setSimlish"
            );

    public StartScreen(Stage stage){
        currentStage = stage;
    }

    public Scene makeScene(){
        background = new ImageView(new Image(images.getString("startScreenImage")));
        startGame = new Button(labels.getString("startButton"));
        languageSelector = new ComboBox<>();
        languageSelector.getItems().addAll(
                labels.getString("eng"),
                labels.getString("span"),
                labels.getString("germ"),
                labels.getString("sim"));
        buttonRow = new HBox();
        buttonRow.getChildren().addAll(languageSelector, startGame);
        buttonRow.setAlignment(Pos.BOTTOM_CENTER);
        buttonRow.setId("buttonRow");
        startGamePane = new StackPane();
        startGamePane.setPrefSize(SCREEN_SIZE, SCREEN_SIZE);
        startGamePane.getChildren().addAll(background,buttonRow);
        StackPane.setAlignment(startGamePane, Pos.CENTER);


        handleEvents();
        Scene s = new Scene(startGamePane, SCREEN_SIZE, SCREEN_SIZE);
        s.getStylesheets().add(styles.getString("startScreenCSS"));
        return s;
    }

    public void nextScreen(){
        ChooseGameScreen c = new ChooseGameScreen(currentStage);
        currentStage.setScene(c.makeScene());
        currentStage.show();
    }

    public void setEnglish() {
        labels= ResourceBundle.getBundle(
                "ResourceBundles.LabelsBundle");
    }

    public void setSpanish() {
        labels= ResourceBundle.getBundle(
                "ResourceBundles.LabelsBundle_es");
    }
    public void setGerman() {
        labels= ResourceBundle.getBundle(
                "ResourceBundles.LabelsBundle_de");
    }

    public void setSimlish(){
        labels= ResourceBundle.getBundle(
                "ResourceBundles.LabelsBundle_sim");
    }

    public ResourceBundle getLabels(){
        return labels;
    }



    public void handleEvents(){
        startGame.setOnAction(event -> {
            nextScreen();
        });
        languageSelector.setOnAction(event -> {
            try {
                Method changeLanguage = this.getClass().getDeclaredMethod(
                        languageMap.get(languageSelector.getValue()));
                changeLanguage.invoke(this);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });

        };
    }

