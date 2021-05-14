package model;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlySetWrapper;
import model.entityImpl.asEnum.EntityImpl;
import model.moverImpl.MoverImplementation;
import model.tableImpl.TableImpl;

import java.util.Collection;
import java.util.List;

public class SokobanGameModel {
    public  int boardSize = Configuration.BOARD_SIZE;
    private ReadOnlyObjectWrapper<Entity>[][] board = new ReadOnlyObjectWrapper[boardSize][boardSize];
    private ReadOnlySetWrapper<Position> ballPosition;
    private ReadOnlySetWrapper<Position> goalPosition;
    private Mover mover = new MoverImplementation();

    private Table tableRepresentation;
    private ReadOnlyBooleanWrapper gameOver = new ReadOnlyBooleanWrapper();

    private List<EntityWrapper> valuableEntities = Configuration.ValuableEntities();

    public SokobanGameModel() {
        boardInit(valuableEntities);
        tableRepresentation = new TableImpl(boardSize, board, EntityImpl.NONE);
        ballPosition.addAll(tableRepresentation.getBallPositions());
        goalPosition.addAll(tableRepresentation.getGoalPositions());
        gameOver.bind(ballPosition.isEqualTo(goalPosition));
    }

    private void boardInit(Collection<EntityWrapper> valuableEntities) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<Entity>(EntityImpl.NONE);
            }
        }
        valuableEntities.stream().map(a -> board[a.getXCoordinate()][a.getYCoordinate()]= new ReadOnlyObjectWrapper<>(a.getEntity()));

    }

    public ReadOnlyBooleanProperty GetReadOnlyGameOverProperty() {
        return gameOver.getReadOnlyProperty();
    }

    public void move(Direction direction) throws cantBeMovedException {
        mover.move(tableRepresentation.getPlayerPosition(), direction, tableRepresentation);
        ballPosition.addAll(tableRepresentation.getBallPositions());
    }

}

