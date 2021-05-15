package model;

import javafx.beans.property.*;
import model.entityImpl.asEnum.EntityImpl;
import model.moverImpl.MoverImplementation;
import model.moverImpl.WeakMover;
import model.tableImpl.TableImpl;
import org.tinylog.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Representing the Sokoban game model.
 */
public class SokobanGameModel {
    private int boardSize = Configuration.BOARD_SIZE;
    private ReadOnlyObjectWrapper<Entity>[][] board = new ReadOnlyObjectWrapper[boardSize][boardSize];
    private ReadOnlyObjectWrapper<Set<Position>> ballPosition =  new ReadOnlyObjectWrapper<>();
    private ReadOnlyObjectWrapper<Set<Position>> goalPosition = new ReadOnlyObjectWrapper<>();
    private Mover mover = new WeakMover();

    private Table tableRepresentation;
    private ReadOnlyBooleanWrapper gameOver = new ReadOnlyBooleanWrapper();


    /**
     * Creating a Sokoban game model from {@code Configuration} .
     */
    public SokobanGameModel() {
        boardInit(Configuration.ValuableEntities());
        tableRepresentation = new TableImpl(boardSize, board, EntityImpl.NONE);
        ballPosition.set(tableRepresentation.getBallPositions());
        goalPosition.set(tableRepresentation.getGoalPositions());
        gameOver.bind(ballPosition.isEqualTo(goalPosition));
    }

    /**
     * Creating a Sokoban game model from a {@code List} .
     *
     * @param entityWrapperList the list what is representing the Entities.
     */
    public SokobanGameModel(List<EntityWrapper> entityWrapperList) {
        boardInit(entityWrapperList);
        tableRepresentation = new TableImpl(boardSize, board, EntityImpl.NONE);
        ballPosition.set(tableRepresentation.getBallPositions());
        goalPosition.set(tableRepresentation.getGoalPositions());


        gameOver.bind(ballPosition.isEqualTo(goalPosition));
    }

    private void boardInit(List<EntityWrapper> valuableEntities) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<Entity>(EntityImpl.NONE);
            }
        }

        valuableEntities.stream().forEach(a -> board[a.getXCoordinate()][a.getYCoordinate()].set(a.getEntity()));

        Logger.trace(toString());

    }

    /**
     * @return the gemaOver readOnlyProperty.
     */
    public ReadOnlyBooleanProperty GetReadOnlyGameOverProperty() {
        return gameOver.getReadOnlyProperty();
    }

    /**
     * Moving the player to the given direction.
     *
     * @param direction where to move the player
     * @throws CantBeMovedException if the player cant be moved.
     */
    public void move(Direction direction) throws CantBeMovedException {
        mover.move(tableRepresentation.getPlayerPosition(), direction, tableRepresentation);

       ballPosition.set(tableRepresentation.getBallPositions());
        Logger.trace("Balls on goals:{}",tableRepresentation.getGoalPositions().equals(tableRepresentation.getBallPositions()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SokobanGameModel table1 = (SokobanGameModel) o;
        if (this.boardSize != table1.boardSize) {
            return false;
        }
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++)

                if (!(this.board[i][j].get().equals(table1.board[i][j].get()))) {
                    return false;


                }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(boardSize);
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                for (int k = 0; k < 2; k++) {
                    result = result + board[i][j].getValue().hashCode();

                }
            }
        }
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                sb.append(board[j][i].getValue().toString()).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public ReadOnlyObjectProperty<Entity> squareProperty(int i, int j) {
        return board[i][j].getReadOnlyProperty();
    }


}

