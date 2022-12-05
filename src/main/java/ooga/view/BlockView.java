package ooga.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;
import java.util.List;

public class BlockView extends ImageView {
    private String imagePath;
    private int state;

    public BlockView(int x, int y, int blockSize, int state, String imagePath){
        super(new Image(imagePath));
        this.imagePath = imagePath;
        this.state = state;
        setX(x);
        setY(y);
        setFitWidth(blockSize);
        setFitHeight(blockSize);
    }

    public List<Double> getKey() {
        return Arrays.asList(getX(), getY());
    }
}
