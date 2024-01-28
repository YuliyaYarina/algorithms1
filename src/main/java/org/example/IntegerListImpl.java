package org.example;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList{

    private final  Integer[] what;
    private int size;

    public IntegerListImpl(Integer[] what, int size) {
        this.what = what;
        this.size = size;
    }

    public IntegerListImpl(int initSize) {
        what = new Integer[initSize];
    }

    @Override
        public Integer add(Integer item) {
        validateSize();
        validateIten(item);
        what[size++] = item;
            return item;
        }

        @Override
        public Integer add(int index, Integer item) {
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
        public Integer set(int index, Integer item) {
           validateIndex(index);
           validateIten(item);
           what[index] = item;
            return item;
        }

        @Override
        public Integer remove(Integer item) {
        validateIten(item);

        int index = indexOf(item);

            return remove(index);
        }

        @Override
        public Integer remove(int index) {
           validateIndex(index);

           Integer iten = what[index];

        if (index != size) {
            System.arraycopy(what, index + 1, what, index, size - index);
        }
            size--;
            return iten;
        }

        @Override
        public boolean contains(Integer item) {
            Integer[] storegeCopy = toArray();
            sort(storegeCopy);
            return binarySearch(storegeCopy, item);
        }

        @Override
        public int indexOf(Integer item) {
            for (int i = 0; i < size; i++) {
                if (what[i].equals(item)) {
                    return  i;
                }
            }
            return -1;
        }

        @Override
        public int lastIndexOf(Integer item) {
            for (int i = size - 1; i >= 0; i--) {
                if (what[i].equals(item)) {
                    return  i;
                }
            }
            return -1;
        }

        @Override
        public Integer get(int index) {
        validateIndex(index);
            return what[index];
        }

        @Override
        public boolean equals(IntegerList otherList) {
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
        public Integer[] toArray() {
            return Arrays.copyOf(what,size);
        }

        private void validateIten(Integer iten) {
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

    private void sort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = ( min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid -1;
            }else {
                min = mid +1;
            }
        }
        return false;
    }



}
