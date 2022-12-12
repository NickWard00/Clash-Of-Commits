package ooga.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;
import java.util.List;

/**
 * @author Nick Ward
 */

/**
 * The BlockView displays each tile of the game map.
 */
public class BlockView extends ImageView {
    private String imagePath;
    private int state;
    private String blockViewType;

    /**
     * Constructor for the BlockView
     * @param x: x coordinate
     * @param y: y coordinate
     * @param blockSize: the size of the block in pixels (it will always be square)
     * @param state: what type of block the block is (can the player walk through or not)
     * @param imagePath: what image the block should be set to
     */
    public BlockView(int x, int y, int blockSize, int state, String imagePath, String blockViewType){
        super(new Image(imagePath));
        this.imagePath = imagePath;
        this.state = state;
        this.blockViewType = blockViewType;
        setX(x);
        setY(y);
        setFitWidth(blockSize);
        setFitHeight(blockSize);
    }

    /**
     * returns the location of the block
     * @return block coordinates
     */
    public List<Double> getKey() {
        return Arrays.asList(getX(), getY());
    }

    public String getBlockViewType() { return blockViewType; }

    /**
     * Returns the image path
     * @return image path
     */
    public String getImagePath() {
        return imagePath;
    }
}
