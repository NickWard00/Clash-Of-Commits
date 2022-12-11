package ooga.view.screens;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
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
public class StartScreen extends SceneCreator {
    private Button startGame;
    private ImageView background;
    private Pane startGamePane;
    private Stage currentStage;
    private ComboBox languageSelector;
    private HBox buttonRow;
    private ResourceBundle labels;
    private ResourceBundle images;
    private ResourceBundle styles;
    private ResourceBundle media;
    private int screenSize;
    private Map<String, String> languageMap;
    private Media music;
    private MediaPlayer myMedia;

    /**
     * Constructor for the startscreen
     * @param stage what stage the screen should be set in
     */
    public StartScreen(Stage stage) {
        this.currentStage = stage;
        this.labels = getLabels();
        this.images = getImages();
        this.styles = getStyles();
        this.media = getMedia();
        this.screenSize = getScreenSize();
        this.languageMap = Map.of(
                labels.getString("eng"), "setEnglish",
                labels.getString("span"), "setSpanish",
                labels.getString("germ"),"setGerman",
                labels.getString("sim"), "setSimlish"
        );
        music = new Media(new File(media.getString("start")).toURI().toString());
    }

    /**
     * Makes the scene corresponding to the start screen
     * @return the scene corresponding to the start screen
     */
    @Override
    public Scene makeScene() throws IllegalStateException {
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
        startGamePane.setPrefSize(screenSize, screenSize);
        startGamePane.getChildren().addAll(background,buttonRow);
        StackPane.setAlignment(startGamePane, Pos.CENTER);
        handleEvents();
        Scene scene = new Scene(startGamePane, screenSize, screenSize);
        scene.getStylesheets().add(styles.getString("startScreenCSS"));
        currentStage.setTitle(labels.getString("splashScreenTitle"));
        currentStage.getIcons().add(new Image(labels.getString("splashScreenIcon")));
        myMedia = new MediaPlayer(music);
        return scene;
    }

    /**
     * Selects the next screen to be displayed
     * @param labels the resource bundle containing the labels
     */
    private void nextScreen(ResourceBundle labels){
        ChooseGameScreen c = new ChooseGameScreen(currentStage, labels);
        currentStage.setScene(c.makeScene());
        currentStage.show();
        myMedia.stop();
    }

    /**
     * Sets the language to English
     */
    private void setEnglish() {
        labels = ResourceBundle.getBundle(
                "ResourceBundles.LabelsBundle");
    }

    /**
     * Sets the language to Spanish
     */
    private void setSpanish() {
        labels = ResourceBundle.getBundle(
                "ResourceBundles.LabelsBundle_es");
    }

    /**
     * Sets the language to German
     */
    private void setGerman() {
        labels = ResourceBundle.getBundle(
                "ResourceBundles.LabelsBundle_de");
    }

    /**
     * Sets the language to simlish
     */
    private void setSimlish(){
        labels = ResourceBundle.getBundle(
                "ResourceBundles.LabelsBundle_simlish");
    }

    //handles the changing of languages using the selector and the clicking of the start button
    private void handleEvents() throws IllegalStateException {
        startGame.setOnAction(event -> {
            nextScreen(labels);
        });
        languageSelector.setOnAction(event -> {
            try {
                Method changeLanguage = this.getClass().getDeclaredMethod(
                        languageMap.get(languageSelector.getValue()));
                changeLanguage.invoke(this);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException("languageNotFound", e);
            }
        });

        };
    }

