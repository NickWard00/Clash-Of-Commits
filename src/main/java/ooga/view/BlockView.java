package ooga.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class BlockView {
    private ImageView imageView;
    private String imagePath;

    public BlockView(int x, int y, int blockSize, String imagePath, GridPane root){
        this.imagePath = imagePath;
        imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(blockSize);
        imageView.setFitHeight(blockSize);
        root.add(imageView, x, y);
    }

    public String getImagePath(){
        return imagePath;
    }
}
