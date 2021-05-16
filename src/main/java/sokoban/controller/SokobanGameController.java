package sokoban.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.tinylog.Logger;
import sokoban.GameWinner;
import sokoban.model.CantBeMovedException;
import sokoban.model.Direction;
import sokoban.model.SokobanGameModel;
import sokoban.model.directionImpl.EnumDirection;
import sokoban.model.entityImpl.asEnum.EntityImpl;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller for the opening Game scene.
 */
public class SokobanGameController {

    private SokobanGameModel model = new SokobanGameModel();
    @FXML
    private GridPane board;
    private String name;
    @FXML
    private Button giveUpFinishButton;

    @FXML
    private Label stepsLabel;

    private IntegerProperty steps = new SimpleIntegerProperty();

    @FXML
    private void initialize() throws IOException {
        createBoard();
        Logger.info("name = {}", name);
        model.GetReadOnlyGameOverProperty().addListener(
                ((observableValue, oldValue, newValue) -> {
                    if (newValue) {
                        Logger.info("It's a Win.");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Win");
                        alert.setContentText(String.format("Congratulations, %s!", name));
                        alert.show();
                        giveUpFinishButton.setText("Finish");
                        try {
                            saveToList();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
        );
        stepsLabel.textProperty().bind(steps.asString());
        steps.set(0);
    }


    private void saveToList() throws IOException {
        String filePath;
        StringBuilder stringBuilder = new StringBuilder();
        filePath = stringBuilder.append(System.getProperty("user.home")).append(File.separator).append(".sokoban").append(File.separator).append("resultList.json").toString();

        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        List<GameWinner> countries = new LinkedList<>();
        if (inputStream.available() > 0) {
            countries = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .readValue(inputStream, new TypeReference<List<GameWinner>>() {
                    });
        }
        countries.add(new GameWinner(name,steps.get()));
        var objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try (var writer = new FileWriter(file)) {
            objectMapper.writeValue(writer, countries);
        }

    }

    /**
     * Sets the name of the Player.
     *
     * @param name of the player.
     */
    public void setName(String name) {
        Logger.info("Setting name to {}", name);
        this.name = name;
    }

    private void createBoard() {
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare(j, i);
                board.add(square, i, j);
            }
        }
    }

    private StackPane createSquare(int i, int j) {
        var square = new StackPane();
        square.getStyleClass().add("square");

        var piece = new Circle(10);

        piece.fillProperty().bind(
                new ObjectBinding<Paint>() {
                    {
                        super.bind(model.squareProperty(j, i));
                    }

                    @Override
                    protected Paint computeValue() {
                        return switch ((EntityImpl) model.squareProperty(j, i).get()) {
                            case NONE -> Color.TRANSPARENT;
                            case PLAYER -> Color.RED;
                            case BALL -> Color.BLUE;
                            case GOAL -> Color.GREEN;
                            case WALL -> Color.BLACK;
                        };
                    }
                }
        );
        square.getChildren().add(piece);
        return square;
    }

    @FXML
    private void handleUpButton() {
        move(EnumDirection.UP);
    }

    @FXML
    private void handleDownButton() {
        move(EnumDirection.DOWN);
    }

    @FXML
    private void handleRightButton() {
        move(EnumDirection.RIGHT);
    }

    @FXML
    private void handleLeftButton() {
        move(EnumDirection.LEFT);
    }

    @FXML
    private void handleGiveUpFinishButton(ActionEvent actionEvent) throws IOException {
        var buttonText = ((Button) actionEvent.getSource()).getText();
        Logger.debug("{} is pressed", buttonText);
        if (buttonText.equals("Give Up")) {

            Logger.info("The game has been given up");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/table.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    private void handleResetButton(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui.fxml"));
        Parent root = fxmlLoader.load();
        SokobanGameController controller = fxmlLoader.<SokobanGameController>getController();
        controller.setName(name);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void move(Direction direction) {
        try {
            steps.set(steps.get() + 1);
            model.move(direction);
        } catch (CantBeMovedException e) {
            Logger.info("{}", e.toString());
            steps.set(steps.get() - 1);
        } catch (Exception e) {
            Logger.info("{}", e.toString());
            steps.set(steps.get() - 1);
        }
        Logger.info("Number of Steps: {}",steps.get());
    }


}
