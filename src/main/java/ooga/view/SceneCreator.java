package ooga.view;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.ResourceBundle;

public class SceneCreator {
    private static final ResourceBundle images = ResourceBundle.getBundle(
            "ResourceBundles.Images");
    private static final ResourceBundle constants = ResourceBundle.getBundle(
            "ResourceBundles.ViewConstants");
    private static final ResourceBundle styles = ResourceBundle.getBundle("ResourceBundles.Stylesheets");
    private static final ResourceBundle labels = ResourceBundle.getBundle(
            "ResourceBundles.LabelsBundle");
    private static final int SCREEN_SIZE = Integer.parseInt(constants.getString("screenSize"));

    public Scene makeScene() {
        Group root = new Group();
        Scene scene = new Scene(root, SCREEN_SIZE, SCREEN_SIZE);
        return scene;
    }

    public ResourceBundle getImages() {
        return images;
    }

    public ResourceBundle getConstants() {
        return constants;
    }

    public ResourceBundle getStyles() {
        return styles;
    }

    public ResourceBundle getLabels() {
        return labels;
    }

    public int getScreenSize() {
        return SCREEN_SIZE;
    }
}
