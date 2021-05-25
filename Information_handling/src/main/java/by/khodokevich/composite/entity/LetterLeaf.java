package by.khodokevich.composite.entity;

import java.util.List;

public class LetterLeaf implements Component {
    private char symbol;
    private ComponentType componentType;

    public LetterLeaf(char symbol, ComponentType componentType) {
        this.symbol = symbol;
        this.componentType = componentType;
    }

    @Override
    public void add(Component component){
        throw new UnsupportedOperationException("Undivided unit : " + getClass().getSimpleName());
    }

    @Override
    public void addAll(List<Component> components) {
        throw new UnsupportedOperationException("Undivided unit : " + getClass().getSimpleName());
    }

    @Override
    public void remove(Component component){
        throw new UnsupportedOperationException("Undivided unit : " + getClass().getSimpleName());
    }

    @Override
    public List<Component> getChildComponentAsList() {
        throw new UnsupportedOperationException("Undivided unit : " + getClass().getSimpleName());
    }

    @Override
    public ComponentType getType() {
        return componentType;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
