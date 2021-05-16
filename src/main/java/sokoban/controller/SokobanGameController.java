package sokoban.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.beans.binding.ObjectBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sokoban.GameWinner;
import sokoban.model.CantBeMovedException;
import sokoban.model.SokobanGameModel;
import sokoban.model.directionImpl.EnumDirection;
import sokoban.model.entityImpl.asEnum.EntityImpl;
import org.tinylog.Logger;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class SokobanGameController {

    private SokobanGameModel model = new SokobanGameModel();
    @FXML
    private GridPane board;
    private String name;
    @FXML
    private Button giveUpFinishButton;

    @FXML
    private void initialize() throws IOException {
        createBoard();
        Logger.info("name = {}", name);
        model.GetReadOnlyGameOverProperty().addListener(
                ((observableValue, oldValue, newValue) -> {
                    if (newValue) {
                        Logger.info("It's a Win.");
                        Alert alert =new Alert(Alert.AlertType.INFORMATION);
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
    }

    private void saveToList() throws IOException {
        String filePath;
        StringBuilder stringBuilder = new StringBuilder();
        filePath = stringBuilder.append(System.getProperty("user.home")).append(File.separator).append(".sokoban").append(File.separator).append("resultList.json").toString();

        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        List<GameWinner> countries = new LinkedList<>();
        if(inputStream.available()>0) {
            countries = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .readValue(inputStream, new TypeReference<List<GameWinner>>() {
                    });
        }
        countries.add(new GameWinner(name));
        var objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try (var writer = new FileWriter(file)) {
            objectMapper.writeValue(writer, countries);
        }

    }

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
        //todo magic Number to constants
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
        try {
            model.move(EnumDirection.UP);
        } catch (CantBeMovedException e) {
            Logger.info("{}", e.toString());
        } catch (Exception e) {
            Logger.info("{}", e.toString());
        }
    }

    @FXML
    private void handleDownButton() {
        try {
            model.move(EnumDirection.DOWN);
        } catch (CantBeMovedException e) {
            Logger.info("{}", e.toString());
        } catch (Exception e) {
            Logger.info("{}", e.toString());
        }
    }

    @FXML
    private void handleRightButton() {
        try {
            model.move(EnumDirection.RIGHT);
        } catch (CantBeMovedException e) {
            Logger.info("{}", e.toString());
        } catch (Exception e) {
            Logger.info("{}", e.toString());
        }
    }

    @FXML
    private void handleLeftButton() {
        try {
            model.move(EnumDirection.LEFT);
        } catch (CantBeMovedException e) {
            Logger.info("{}", e.toString());
        } catch (Exception e) {
            Logger.info("{}", e.toString());
        }
    }
    @FXML
    public void handleGiveUpFinishButton(ActionEvent actionEvent) throws IOException {
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

}
