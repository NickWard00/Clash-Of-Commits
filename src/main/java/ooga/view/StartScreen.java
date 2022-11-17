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

/**
 * creates the starting screen of the game, which allows you to toggle the language before proceeding.
 */
public class StartScreen {

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

    private Map<String, String> languageMap= Map.of(
            labels.getString("eng"), "setEnglish",
            labels.getString("span"), "setSpanish",
            labels.getString("germ"),"setGerman",
            labels.getString("sim"), "setSimlish"
            );

    public StartScreen(Stage stage){
        currentStage = stage;
    }

    //makes the scene that is displayed on the screen
    public Scene makeScene(){
        background = new ImageView(new Image(images.getString("startScreenImage")));
        startGame = new Button(labels.getString("startButton"));
        startGame.setId("startGame");
        languageSelector = new ComboBox<>();
        languageSelector.setId("languageSelector");
        languageSelector.getItems().addAll(
                labels.getString("eng"), labels.getString("span"),
                labels.getString("germ"), labels.getString("sim"));
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

    //moves to the next screen of the game
    public void nextScreen(){
        ChooseGameScreen c = new ChooseGameScreen(currentStage, labels);
        currentStage.setScene(c.makeScene());
        currentStage.show();
    }

    //sets the language to english
    public void setEnglish() {
        labels= ResourceBundle.getBundle(
                "ResourceBundles.LabelsBundle");
    }
    //sets the language to spanish
    public void setSpanish() {
        labels= ResourceBundle.getBundle(
                "ResourceBundles.LabelsBundle_es");
    }
    //sets the language to german
    public void setGerman() {
        labels= ResourceBundle.getBundle(
                "ResourceBundles.LabelsBundle_de");
    }
    //sets the language to simlish
    public void setSimlish(){
        labels= ResourceBundle.getBundle(
                "ResourceBundles.LabelsBundle_simlish");
    }

    //is necessary in order to pass the language resourcebundle to other screens
    //other resourcebundles are final and do not need to be passed this way
    public ResourceBundle getLabels(){
        return labels;
    }


    //handles the changing of languages using the selector and the clicking of the start button
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

