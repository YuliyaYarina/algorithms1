package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringListImpl implements StringList{

    private final  String[] what;
    private int size;

    public StringListImpl(int initSize) {
        what = new String[initSize];
    }
        @Override
        public String add(String item) {
        validateSize();
        validateIten(item);
        what[size++] = item;
            return item;
        }

        @Override
        public String add(int index, String item) {
        validateSize();
        validateIten(item);
        validateIndex(index);

        if (index == size) {
            what[size++] = item;
            return item;
        }
            System.arraycopy(what, index, what, index + 1, size - index);
            size++;

            return item;
        }

        @Override
        public String set(int index, String item) {
           validateIndex(index);
           validateIten(item);
           what[index] = item;
            return item;
        }

        @Override
        public String remove(String item) {
        validateIten(item);

        int index = indexOf(item);

            return remove(index);
        }

        @Override
        public String remove(int index) {
           validateIndex(index);

           String iten = what[index];

        if (index != size) {
            System.arraycopy(what, index + 1, what, index, size - index);
        }
            size--;
            return iten;
        }

        @Override
        public boolean contains(String item) {
            return indexOf(item) != -1;
        }

        @Override
        public int indexOf(String item) {
            for (int i = 0; i < size; i++) {
                if (what[i].equals(item)) {
                    return  i;
                }
            }
            return -1;
        }

        @Override
        public int lastIndexOf(String item) {
            for (int i = size - 1; i >= 0; i--) {
                if (what[i].equals(item)) {
                    return  i;
                }
            }
            return -1;
        }

        @Override
        public String get(int index) {
        validateIndex(index);
            return what[index];
        }

        @Override
        public boolean equals(StringList otherList) {
            return Arrays.equals(this.toArray(), otherList.toArray());
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public void clear() {
        size =0;
        }

        @Override
        public String[] toArray() {
            return Arrays.copyOf(what,size);
        }

        private void validateIten(String iten) {
        if (iten == null) {
            throw  new NullItenException();
        }
    }
    private void validateSize() {
        if (size == what.length) {
            throw  new StoregeIsFullException();
        }
    }
    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }



}
