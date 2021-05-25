package by.khodokevich.composite.entity;

import java.util.List;

public interface Component {
    String toString();

    void add(Component component);

    void addAll(List<Component> components);

    void remove(Component component);

    List<Component> getChildComponentAsList();

    ComponentType getType();
}
