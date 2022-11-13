package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ResourceBundle;

public class FileInputOutput {
  private static final String OPEN_DATA_FILE = "Open Data File";
  private static final String SIM_FILES = "SIM Files";
  private static final String GRID_SCREEN_CSS = "gridScreen.css";
  private static final String TITLE_TEXT = "titleText";
  private static final String MAIN_TEXT = "mainText";
  private static final String UPLOAD_GIF = "uploadGif";
  private static final String UPLOAD_BOX = "uploadBox";
  private static final String FILE_UPLOAD_ERROR = "fileUploadError";
  private static final String BUTTON = "button";
  // kind of data files to look for
  private static final String DATA_FILE_SIM_EXTENSION = "*.sim";
  // default to start in the data folder to make it easy on the user to find
  private static final String DATA_FILE_FOLDER = System.getProperty("user.dir") + "/data";
  // NOTE: make ONE chooser since generally accepted behavior is that it remembers where user left it last
  private final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_SIM_EXTENSION);
  private static final String DEFAULT_RESOURCE_PACKAGE = String.format("%s.",
          FileInputOutput.class.getPackageName());
  private static final String COMMAND_PROPERTIES = "Command";

  private ResourceBundle myResource;
  private BorderPane inputPane;
  private ResourceBundle myCommands = java.util.ResourceBundle.getBundle(
          String.format("%s%s", DEFAULT_RESOURCE_PACKAGE, COMMAND_PROPERTIES));
  private ImageView inputBackground;
  private final Stage myStage;
  private final List<String> buttonList = List.of("uploadButton", "backButton");
  private double mySize;

  /**
   * Constructor for FileInput
   *
   * @param size
   */
  public FileInputOutput(double size, Stage stage) {
    inputPane = new BorderPane();
    inputBackground = new ImageView();
    myStage = stage;
    mySize = size;
  }

  /**
   * Sets up the file input screen
   *
   * @return
   */
  public Pane setUpRootPane() {
    Text title = new Text(myResource.getString(TITLE_TEXT));
    title.getStyleClass().add(MAIN_TEXT);

    inputBackground.setImage(new Image(myResource.getString(UPLOAD_GIF)));
    inputBackground.setFitHeight(mySize);
    inputBackground.setFitWidth(mySize);
    inputPane.getChildren().addAll(inputBackground);

    VBox upload = new VBox(title);
    for (String button : buttonList) {
      upload.getChildren().add(makeButton(button));
    }
    upload.setAlignment(Pos.CENTER);
    upload.getStyleClass().add(UPLOAD_BOX);
    inputPane.setTop(upload);
    return inputPane;
  }

  /**
   * Make a button and sets properties
   *
   * @param property
   * @return
   */
  public Button makeButton(String property) {
    Button result = new Button();
    String labelText = myResource.getString(property);
    result.setText(labelText);
    result.setId(property);
    result.getStyleClass().add(BUTTON);
    result.setOnAction(event -> {
      try {
        Method m = this.getClass().getDeclaredMethod(myCommands.getString(property));
        m.invoke(this);
      } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
        showMessage(Alert.AlertType.ERROR, myResource.getString(e.getCause().getMessage()), e);
      }
    });
    return result;
  }

  private void showMessage(Alert.AlertType type, String message, Exception e) {
    new Alert(type, message).showAndWait();
  }

  /**
   * Sets up the file chooser
   *
   * @param extensionAccepted
   * @return
   */
  private static FileChooser makeChooser(String extensionAccepted) {
    FileChooser result = new FileChooser();
    result.setTitle(OPEN_DATA_FILE);
    // pick a reasonable place to start searching for files
    result.setInitialDirectory(new File(DATA_FILE_FOLDER));
    result.getExtensionFilters()
        .setAll(new FileChooser.ExtensionFilter(SIM_FILES, extensionAccepted));
    return result;
  }

  private void setResource(ResourceBundle myResource) {
    this.myResource = myResource;
  }
}

