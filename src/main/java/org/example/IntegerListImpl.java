package org.example;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList{

    private Integer[] what;
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
        growIfNeeded();
        validateIten(item);
        what[size++] = item;
            return item;
        }

        @Override
        public Integer add(int index, Integer item) {
        growIfNeeded();
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
    private void growIfNeeded() {
        if (size == what.length) {
            grow();
        }
    }
    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

    private void sort(Integer[] arr) {
       quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
             quickSort(arr, begin, partitionIndex - 1);
             quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElemtnts(arr, i, j);
            }
        }
        swapElemtnts(arr, i + 1, end);
        return i + 1;
    }

    private void swapElemtnts(Integer[] arr, int i1, int i2){
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;

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

    private void  grow() {
        what = Arrays.copyOf(what, size + size / 2);
    }



}
