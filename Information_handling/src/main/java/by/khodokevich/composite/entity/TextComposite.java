package by.khodokevich.composite.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements Component {
    private List<Component> components = new ArrayList<>();
    private ComponentType textType;

    public TextComposite(ComponentType textType) {
        this.textType = textType;
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public List<Component> getNextComponentAsList() {
        return components;
    }

    @Override
    public ComponentType getType() {
        return textType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (textType == ComponentType.TEXT){
            sb.append(textType.getBeforeComponent());
        }

        for (Component element : components) {
            sb.append(textType.getBeforeComponent());
            sb.append(element.toString());
            sb.append(textType.getAfterComponent());
        }

        return sb.toString().trim();
    }
}
