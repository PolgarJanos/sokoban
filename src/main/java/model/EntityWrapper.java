package model;

import java.util.Objects;

/**
 * Represent a entity  with his position.
 */
public class EntityWrapper {
    private Entity entity;
    private Position position;

    /**
     * Creat a {@code EntityWrapper}.
     *
     * @param entity   entity to be wrapped.
     * @param position the entity position on the board.
     */
    public EntityWrapper(Entity entity, Position position) {
        this.entity = entity;
        this.position = position;
    }

    /**
     * @return the entity first coordinate.
     */
    public int getXCoordinate() {
        return position.getXCoordinate();
    }

    /**
     * @return the entity second coordinate.
     */
    public int getYCoordinate() {
        return position.getYCoordinate();
    }

    /**
     * @return the entity from the wrapper.
     */
    public Entity getEntity() {
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityWrapper that = (EntityWrapper) o;
        return Objects.equals(entity, that.entity) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entity, position);
    }
}
