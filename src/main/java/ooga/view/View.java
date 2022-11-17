package ooga.view;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.controller.EntityView;
import ooga.model.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class View {
    private Scene scene;
    private BorderPane root;
    private Stage stage;
    private MapView mapView;
    private List<EntityView> entityViewList;

    public View(Stage stage){
        this.stage = stage;
        entityViewList = new ArrayList<>();
        setupGame(stage);
    }
    public void step(double elapsedTime){

    }

    private void setupGame(Stage stage){
        root = new BorderPane(); //later change to Root object ??? 
        scene = new Scene(root);
        scene.setOnKeyPressed(e->getKeyInput(e.getCode()));
        StartScreen startScreen = new StartScreen(stage);
        stage.setScene(startScreen.makeScene());
    }

    public void addEntity(Entity entity){
        Map<String, String> entityAttributes = entity.getMyAttributes();
        String imageName = entityAttributes.get("Name");
        double xPos = Double.parseDouble(entityAttributes.get("xPosition"));
        double yPos = Double.parseDouble(entityAttributes.get("yPosition"));
        int size = Integer.parseInt(entityAttributes.get("Size"));
        String spriteLocation = entityAttributes.get("Sprites");
        String startingDirection = entityAttributes.get("Direction");
        EntityView entityView = new EntityView(spriteLocation, startingDirection, imageName, xPos, yPos, size, size);

        entityViewList.add(entityView);
    }

    public void removeEntity(Entity entity){
        String entityName = entity.getMyAttributes().get("Name");
        if (!entityViewList.isEmpty()) {
            entityViewList.forEach(entityView -> {
                if (entityView.getEntityName().equals(entityName)) {
                    entityViewList.remove(entityView);
                }
            });
        }
    }

    private void getKeyInput(KeyCode key){
        switch(key){
            case LEFT -> {
                mapView.moveRight();
            }
            case RIGHT -> {
                mapView.moveLeft();
            }
            case DOWN -> {
                mapView.moveUp();
            }
            case UP -> {
                mapView.moveDown();
            }
        }
    }

}
