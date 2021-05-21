package by.khodokevich.composite.entity;

import java.util.List;

public interface Component {
    String toString();

    void add(Component component);

    void remove(Component component);

    List<Component> getNextComponentAsList();

    ComponentType getType();
}
