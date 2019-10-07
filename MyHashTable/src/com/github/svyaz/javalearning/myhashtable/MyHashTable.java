package com.github.svyaz.javalearning.myhashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Collection permits null as elements and permits duplicate objects.
 */
public class MyHashTable<T> implements Collection<T> {
    /**
     * Exceptions messages
     */
    private static final String EXCEPTION_MESSAGE_ILLEGAL_CAPACITY = "Specified capacity must be greater than 0.";
    private static final String EXCEPTION_MESSAGE_NULL_ARGUMENT = "Specified argument is null.";
    private static final String EXCEPTION_MESSAGE_NO_NEXT_ELEMENT = "No next element in table.";
    private static final String EXCEPTION_MESSAGE_CONCURRENT_MODIFICATION = "Concurrent modification of table found.";

    /**
     * Default capacity for arrayItems.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Internal array for storing lists.
     */
    private ArrayList<T>[] arrayItems;

    /**
     * count of elements in the table
     */
    private int count;

    /**
     * Modifications counter for checking by iterator.
     */
    private int modCount = 0;

    /**
     * Creates instance with DEFAULT_CAPACITY size of internal array
     */
    @SuppressWarnings("unchecked")
    public MyHashTable() {
        arrayItems = (ArrayList<T>[]) new ArrayList[DEFAULT_CAPACITY];
    }

    /**
     * Creates instance with specified size of internal array
     */
    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_CAPACITY);
        }
        arrayItems = (ArrayList<T>[]) new ArrayList[capacity];
    }

    /**
     * Returns the number of elements in this collection.
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns true if this collection contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns true if this collection contains at least one of the specified element
     * such that Objects.equals(object, element).
     */
    @Override
    public boolean contains(Object object) {
        if (count == 0) {
            return false;
        }
        int index = getIndex(object);
        return arrayItems[index] != null && arrayItems[index].contains(object);
    }

    /**
     * Returns an iterator over the elements in this collection.
     */
    @Override
    public Iterator<T> iterator() {
        return new MyHashTableIterator();
    }

    /**
     * Nested class for implementation iterator 2.
     */
    private class MyHashTableIterator implements Iterator<T> {
        /**
         * Position of current internal array element.
         */
        private int currentPosition = 0;

        /**
         * Iterator for current element in internal array.
         */
        private Iterator<T> currentIterator = null;

        /**
         * Start value of property modCount of the table.
         */
        private int startModCount = modCount;

        /**
         * Returns true if the iteration has more elements.
         */
        @Override
        public boolean hasNext() {
            if (currentIterator != null && currentIterator.hasNext()) {
                return true;
            }
            for (int i = currentPosition; i < arrayItems.length; i++) {
                if (arrayItems[i] != null) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Returns the next element in the iteration.
         */
        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (modCount != startModCount) {
                throw new ConcurrentModificationException(EXCEPTION_MESSAGE_CONCURRENT_MODIFICATION);
            }
            if (currentIterator != null && currentIterator.hasNext()) {
                return currentIterator.next();
            }
            for (int i = currentPosition; i < arrayItems.length; i++) {
                if (arrayItems[i] != null) {
                    currentPosition = i + 1;
                    currentIterator = arrayItems[i].iterator();
                    return currentIterator.next();
                }
            }
            throw new NoSuchElementException(EXCEPTION_MESSAGE_NO_NEXT_ELEMENT);
        }
    }

    /**
     * Returns an array containing all of the elements in this collection.
     */
    @Override
    public Object[] toArray() {
        if (count == 0) {
            return new Object[0];
        }

        Object[] result = new Object[count];
        int i = 0;
        for (T item : this) {
            result[i] = item;
            ++i;
        }
        return result;
    }

    /**
     * Returns an array containing all of the elements in this collection.
     */
    @Override
    @SuppressWarnings("all")
    public <T1> T1[] toArray(T1[] array) {
        if (array == null) {
            throw new NullPointerException(EXCEPTION_MESSAGE_NULL_ARGUMENT);
        }
        if (array.length < count) {
            return (T1[]) Arrays.copyOf(toArray(), count, array.getClass());
        }
        System.arraycopy(toArray(), 0, array, 0, count);
        if (array.length > count) {
            array[count] = null;
        }
        return array;
    }

    /**
     * Adds an element to the table.
     * Permits null and duplicates.
     * Returns true if this collection changed as a result of the call.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean add(T object) {
        int index = getIndex(object);
        if (arrayItems[index] == null) {
            arrayItems[index] = new ArrayList<>();
        }
        arrayItems[index].add(object);
        ++modCount;
        ++count;
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this collection
     * such that Objects.equals(object, element).
     * Returns true if the collection changed as a result of this call.
     */
    @Override
    public boolean remove(Object object) {
        int index = getIndex(object);
        if (arrayItems[index] == null) {
            return false;
        }
        if (arrayItems[index].remove(object)) {
            ++modCount;
            --count;
            return true;
        }
        return false;
    }

    /**
     * Returns true if this collection contains all of the elements
     * in the specified collection.
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to this collection.
     */
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null) {
            throw new NullPointerException(EXCEPTION_MESSAGE_NULL_ARGUMENT);
        }
        if (collection.size() == 0) {
            return false;
        }
        for (T element : collection) {
            add(element);
        }
        return true;
    }

    /**
     * Removes all of this table's elements that are also contained in the
     * specified collection.
     * Returns true if this table changed as a result of the call.
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        return removeItems(collection, true);
    }

    /**
     * Retains only the elements in this collection that are contained in the
     * specified collection.
     * Returns true if this collection changed as a result of the call.
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        return removeItems(collection, false);
    }

    /**
     * Internal method for removeAll() and retainAll().
     * isPresent = true - removeAll
     * isPresent = false - retainAll
     */
    @SuppressWarnings("all")
    private boolean removeItems(Collection<?> collection, boolean isPresent) {
        if (collection == null) {
            throw new NullPointerException(EXCEPTION_MESSAGE_NULL_ARGUMENT);
        }
        int countBefore = count;
        for (ArrayList<T> list : arrayItems) {
            if (list != null) {
                int listCountBefore = list.size();
                if (isPresent) {
                    list.removeAll(collection);
                } else {
                    list.retainAll(collection);
                }
                count -= listCountBefore - list.size();
            }
        }
        if (count < countBefore) {
            ++modCount;
            return true;
        }
        return false;
    }

    /**
     * Removes all of the elements from this collection.
     */
    @Override
    public void clear() {
        ++modCount;
        for (int i = 0; i < arrayItems.length; i++) {
            arrayItems[i] = null;
        }
        count = 0;
    }

    /**
     * Returns true if the specified object is equal to the table.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        MyHashTable<?> myHashTable = (MyHashTable<?>) object;
        if (count != myHashTable.count) {
            return false;
        }
        for (int i = 0; i < arrayItems.length; i++) {
            if (!Objects.equals(myHashTable.arrayItems[i], arrayItems[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a hash code based on the contents of the specified array.
     */
    @Override
    public int hashCode() {
        int result = 31 + Objects.hashCode(count);
        for (ArrayList arrayItem : arrayItems) {
            result = 31 * result + (arrayItem == null ? 0 : arrayItem.hashCode());
        }
        return result;
    }

    /**
     * Returns string representation of the table.
     */
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append('[');
        boolean isFirst = true;
        for (T item : this) {
            if (isFirst) {
                isFirst = false;
            } else {
                resultString.append(", ");
            }
            resultString.append(String.valueOf(item));
        }
        return resultString.append(']').toString();
    }

    /**
     * Calculates index of internal array by hashCode.
     * Internal method.
     */
    private int getIndex(Object object) {
        return Math.abs(Objects.hashCode(object) % arrayItems.length);
    }
}
