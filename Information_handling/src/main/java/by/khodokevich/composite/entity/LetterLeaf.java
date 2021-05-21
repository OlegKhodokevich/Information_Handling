package by.khodokevich.composite.entity;

import java.util.List;

public class LetterLeaf implements Component {
    private String value;
    private ComponentType componentType;

    public LetterLeaf(String value, ComponentType componentType) {
        this.value = value;
        this.componentType = componentType;
    }

    @Override
    public void add(Component component){
        throw new UnsupportedOperationException("Undivided unit : " + getClass().getSimpleName());
    }

    @Override
    public void remove(Component component){
        throw new UnsupportedOperationException("Undivided unit : " + getClass().getSimpleName());
    }

    @Override
    public List<Component> getNextComponentAsList(){
        throw new UnsupportedOperationException("Undivided unit : " + getClass().getSimpleName());
    }

    @Override
    public ComponentType getType() {
        return componentType;
    }

    @Override
    public String toString() {
        return value;
    }
}
