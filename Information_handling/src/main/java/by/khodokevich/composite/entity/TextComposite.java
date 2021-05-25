package by.khodokevich.composite.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextComposite implements Component {
    private List<Component> components;
    private ComponentType textType;

    {
        components = new ArrayList<>();
    }

    public TextComposite(ComponentType textType) {
        this.textType = textType;
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void addAll(List<Component> components) {
        this.components.addAll(components);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public List<Component> getChildComponentAsList() {
        List<Component> newComponents = new ArrayList<>();
        newComponents.addAll(components);
        return newComponents;
    }

    @Override
    public ComponentType getType() {
        return textType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Component element : components) {
            sb.append(textType.getBeforeComponent());
            sb.append(element.toString());
            sb.append(textType.getAfterComponent());
        }


        return sb.toString().stripTrailing();
    }
}
