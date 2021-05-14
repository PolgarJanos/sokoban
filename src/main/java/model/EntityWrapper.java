package model;

import java.util.Objects;

public class EntityWrapper {
private Entity entity;
private  Position position;

    public EntityWrapper(Entity entity, Position position) {
        this.entity = entity;
        this.position = position;
    }


    public int getXCoordinate() {
        return position.getXCoordinate();
    }

    public int getYCoordinate() {
        return position.getYCoordinate();
    }

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
