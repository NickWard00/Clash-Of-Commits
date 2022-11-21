package ooga.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import ooga.model.obstacle.Obstacle;

public class BlockView{
    private ImageView imageView;
    private String imagePath;
    private double xPos;
    private double yPos;

    public BlockView(int x, int y, int blockSize, String imagePath, GridPane root){
        this.imagePath = imagePath;
        this.xPos = x;
        this.yPos = y;
        imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(blockSize);
        imageView.setFitHeight(blockSize);
        root.add(imageView, x, y);
    }

    public String getImagePath(){
        return imagePath;
    }

    public ImageView getImageView() {
        return this.imageView;
    }
    public double getXPosition() {
        return this.xPos;
    }

    public double getYPosition() {
        return this.yPos;
    }
}
