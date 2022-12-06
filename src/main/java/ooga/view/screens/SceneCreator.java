package ooga.view.screens;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;

import java.util.ResourceBundle;

/**
 * @author Nick Ward
 */

/**
 * The basic skeleton that the various scenes are based off of
 */
public abstract class SceneCreator {
    public static final ResourceBundle images = ResourceBundle.getBundle(
            "ResourceBundles.Images");
    public static final ResourceBundle constants = ResourceBundle.getBundle(
            "ResourceBundles.ViewConstants");
    public static final ResourceBundle styles = ResourceBundle.getBundle("ResourceBundles.Stylesheets");
    public static final ResourceBundle labels = ResourceBundle.getBundle(
            "ResourceBundles.LabelsBundle");
    public static final ResourceBundle media = ResourceBundle.getBundle("ResourceBundles.Sounds");
    private static final int SCREEN_SIZE = Integer.parseInt(constants.getString("screenSize"));

    /**
     * makes the scene
     * @return
     */
    public Scene makeScene() {
        Group root = new Group();
        Scene scene = new Scene(root, SCREEN_SIZE, SCREEN_SIZE);
        return scene;
    }

    /**
     * returns the images resourcebundle
     * @return images
     */
    public ResourceBundle getImages() {
        return images;
    }

    /**
     * returns the constants resourcebundle
     * @return constants
     */
    public ResourceBundle getConstants() {
        return constants;
    }

    /**
     * returns the styles resourcebundle
     * @return styles
     */
    public ResourceBundle getStyles() {
        return styles;
    }

    /**
     * returns the labels resourcebundle
     * @return labels
     */
    public ResourceBundle getLabels() {
        return labels;
    }

    /**
     * returns the media resourcebundle
     * @return media
     */
    public ResourceBundle getMedia(){
        return media;
    }

    /**
     * returns the screen size of the scene
     * @return screen size
     */
    public int getScreenSize() {
        return SCREEN_SIZE;
    }
}
