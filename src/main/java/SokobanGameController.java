import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import model.CantBeMovedException;
import model.SokobanGameModel;
import model.directionImpl.EnumDirection;
import model.entityImpl.asEnum.EntityImpl;
import org.tinylog.Logger;

public class SokobanGameController {

    private SokobanGameModel model = new SokobanGameModel();
    @FXML
    private GridPane board;

    @FXML
    private void initialize() {
        createBoard();
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
        }catch (CantBeMovedException e){
            Logger.info("{}",e.toString());
        }catch (Exception e) {
            Logger.info("{}", e.toString());
        }
    }

    @FXML
    private void handleDownButton() {
        try {
            model.move(EnumDirection.DOWN);
        }catch (CantBeMovedException e){
            Logger.info("{}",e.toString());
        }catch (Exception e){
            Logger.info("{}",e.toString());
        }
    }
    @FXML
    private void handleRightButton() {
        try {
            model.move(EnumDirection.RIGHT);
        }catch (CantBeMovedException e){
            Logger.info("{}",e.toString());
        }catch (Exception e){
            Logger.info("{}",e.toString());
        }
    }
    @FXML
    private void handleLeftButton() {
        try {
            model.move(EnumDirection.LEFT);
        }catch (CantBeMovedException e){
            Logger.info("{}",e.toString());
        }catch (Exception e){
            Logger.info("{}",e.toString());
        }
    }

}
