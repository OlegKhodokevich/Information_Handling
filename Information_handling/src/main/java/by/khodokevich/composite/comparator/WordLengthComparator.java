package by.khodokevich.composite.comparator;

import by.khodokevich.composite.entity.Component;

import java.util.Comparator;

public class WordLengthComparator implements Comparator<Component> {


    @Override
    public int compare(Component o1, Component o2) {
        return o1.getChildComponentAsList().size() - o2.getChildComponentAsList().size();
    }
}
