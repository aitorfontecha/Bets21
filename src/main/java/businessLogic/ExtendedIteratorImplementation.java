package businessLogic;

import java.util.Vector;

import domain.Event;

public class ExtendedIteratorImplementation<Event> implements ExtendedIterator<Event> {

    private Vector<Event> vector;
    private int position;

    public ExtendedIteratorImplementation(Vector<Event> pvector) {

        vector = pvector;
        position = 0;
    }

    @Override
    public boolean hasNext() {
        if ((position + 1) < vector.size()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Event next() {
        position++;
        return vector.get(position);
    }

    @Override
    public Event previous() {
        position--;
        return vector.get(position);
    }

    @Override
    public boolean hasPrevious() {
        if ((position - 1) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void goFirst() {
        position = -1;
    }

    @Override
    public void goLast() {
        position = vector.size();
    }

}
