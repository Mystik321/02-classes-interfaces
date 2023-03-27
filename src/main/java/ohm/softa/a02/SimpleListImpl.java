package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable<Object> {

    private Element head;
    private Element tail;
    private int size;

    public SimpleListImpl() {
        head = tail = null;
    }

    private static class Element {
        Object item;
        private Element next;

        public Element(Object item) {
            this.item = item;
            this.next = null;
        }
    }
        @Override
        public Iterator<Object> iterator() {
            return new SimpleIteratorImpl();
        }

        private class SimpleIteratorImpl implements Iterator<Object> {
            private Element current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Object next() {
                Element data = current;
                current = current.next;
                return data;
            }
        }


        @Override
        public void add(Object o) {

            if (head == null && size == 0)
                head = tail = new Element(o);

            else {
                tail.next = new Element(o);
                tail = tail.next;
            }

            size++;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public SimpleList filter(SimpleFilter filter) {
            SimpleList result = new SimpleListImpl();

            for(Object o: this) {
                if(filter.include(o))
                    result.add(o);
            }

            return result;
        }
}
