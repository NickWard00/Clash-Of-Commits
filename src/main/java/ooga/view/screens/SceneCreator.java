package ooga.view.screens;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.ResourceBundle;

/**
 * @author Nick Ward
 */
public abstract class SceneCreator {
    public static final ResourceBundle images = ResourceBundle.getBundle(
            "ResourceBundles.Images");
    public static final ResourceBundle constants = ResourceBundle.getBundle(
            "ResourceBundles.ViewConstants");
    public static final ResourceBundle styles = ResourceBundle.getBundle("ResourceBundles.Stylesheets");
    public static final ResourceBundle labels = ResourceBundle.getBundle(
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
