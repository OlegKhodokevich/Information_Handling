package by.khodokevich.composite.comparator;

import by.khodokevich.composite.entity.Component;
import by.khodokevich.composite.entity.ComponentType;

import java.util.Comparator;

public class SentenceByLengthWordsComparator implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        o1.getChildComponentAsList().stream().filter(s->s.getType() == ComponentType.WORD).max(new WordLengthComparator());
        return 0;
    }
}
