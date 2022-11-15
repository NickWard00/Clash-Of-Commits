package ooga.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.ResourceBundle;

public class BlockView {
    private double xLocation;
    private double yLocation;
    private int state;
    private String imagePath;
    private ImageView imageView;
    private static final double BLOCK_WIDTH = 10;
    private static final double BLOCK_HEIGHT = 10;
    private static final String MAP_BLOCKS_PROPERTIES = "ResourceBundles.MapBlocks";
    private static ResourceBundle blockStates = ResourceBundle.getBundle(MAP_BLOCKS_PROPERTIES);

    public BlockView(double x, double y, int state, StackPane root){
        xLocation = x;
        yLocation = y;
        this.state = state %4; // for now mod 4 until we get more block images
        imagePath = generateImagePath(state);
        imageView = new ImageView(new Image(imagePath));
        imageView.setX(xLocation);
        imageView.setY(yLocation);
        root.getChildren().add(imageView);
    }
    private String generateImagePath(int state){
        return blockStates.getString("state"+Integer.toString(Math.abs(state %4)));
    }
    public String getImagePath(){
        return imagePath;
    }
}
