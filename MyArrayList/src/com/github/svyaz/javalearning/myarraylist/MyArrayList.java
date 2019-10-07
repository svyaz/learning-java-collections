package com.github.svyaz.javalearning.myarraylist;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    /**
     * Exceptions messages
     */
    private static final String EXCEPTION_MESSAGE_INDEX_OUT_OF_BOUNDS = "Specified index is out of list bounds.";
    private static final String EXCEPTION_MESSAGE_METHOD_NOT_IMPLEMENTED = "Method not implemented.";
    private static final String EXCEPTION_MESSAGE_ILLEGAL_CAPACITY = "Specified capacity must be greater than 0.";
    private static final String EXCEPTION_MESSAGE_NO_NEXT_ELEMENT = "No next element in list.";
    private static final String EXCEPTION_MESSAGE_CONCURRENT_MODIFICATION = "Concurrent modification of list found.";

    /**
     * Default capacity for items.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Private array for storing list elements.
     */
    private E[] items;

    /**
     * Private field for storing array length.
     */
    private int size;

    /**
     * Modifications counter for checking by iterator.
     */
    private int modCount = 0;

    /**
     * Creates instance with DEFAULT_CAPACITY.
     */
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates instance with specified capacity.
     */
    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_CAPACITY);
        }
        items = (E[]) new Object[capacity];
    }

    /**
     * Creates instance with specified elements of input array.
     */
    @SuppressWarnings("unchecked")
    public MyArrayList(E[] items) {
        int capacity = DEFAULT_CAPACITY + (items.length <= DEFAULT_CAPACITY ? 0 : items.length);
        this.items = Arrays.copyOf(items, capacity);
        this.size = items.length;
    }

    /**
     * Returns the number of elements in this list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns true if this list contains at least one element such as specified object.
     */
    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    /**
     * Nested class for implementation iterator.
     */
    private class MyArrayListIterator implements Iterator<E> {
        /**
         * Index of current list element.
         */
        private int currentIndex = -1;

        /**
         * Start value of property modCount of the list.
         */
        private int startModCount = modCount;

        /**
         * Returns true if the iteration has more elements.
         */
        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        /**
         * Returns the next element in the iteration.
         */
        @Override
        public E next() {
            if (currentIndex + 1 >= size) {
                throw new NoSuchElementException(EXCEPTION_MESSAGE_NO_NEXT_ELEMENT);
            }
            if (modCount != startModCount) {
                throw new ConcurrentModificationException(EXCEPTION_MESSAGE_CONCURRENT_MODIFICATION);
            }
            ++currentIndex;
            return items[currentIndex];
        }
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    /**
     * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element).
     */
    @Override
    @SuppressWarnings("all")
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }
        System.arraycopy(items, 0, array, 0, size);
        if (array.length > size) {
            array[size] = null;
        }
        return array;
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     */
    @Override
    public boolean remove(Object object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], object)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if this list contains all of the elements of the
     * specified collection.
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
     * Appends all of the elements in the specified collection to the end of the list.
     * Returns true if this list changed as a result of the call.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.size() == 0) {
            return false;
        }
        ++modCount;
        ensureCapacity(size + collection.size());
        int i = size;
        for (E element : collection) {
            items[i] = element;
            ++i;
        }
        size += collection.size();
        return true;
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).
     * Returns true if this list changed as a result of the call.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(EXCEPTION_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        if (collection.size() == 0) {
            return false;
        }
        ++modCount;
        int additionsLength = collection.size();
        ensureCapacity(size + additionsLength);
        System.arraycopy(items, index, items, index + additionsLength, size - index);
        int i = index;
        for (E element : collection) {
            items[i] = element;
            ++i;
        }
        size += additionsLength;
        return true;
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection.
     * Returns true if this list changed as a result of the call.
     * Uses private method removeItems().
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        return removeItems(collection, true);
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection.
     * Returns true if this list changed as a result of the call.
     * Uses private method removeItems().
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        return removeItems(collection, false);
    }

    /**
     * Removes all of the elements from this list.
     */
    @Override
    public void clear() {
        ++modCount;
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(EXCEPTION_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        return items[index];
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     * Returns the element previously at the specified position
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(EXCEPTION_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        E currentElement = items[index];
        items[index] = element;
        return currentElement;
    }

    /**
     * Appends the specified element to the end of list.
     */
    @Override
    public boolean add(E element) {
        if (items.length <= size) {
            increaseCapacity();
        }
        ++modCount;
        items[size] = element;
        ++size;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in the list.
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(EXCEPTION_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        if (items.length <= size) {
            increaseCapacity();
        }
        ++modCount;
        if (index < size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }
        items[index] = element;
        ++size;
    }

    /**
     * Removes the element at the specified position in the list.
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(EXCEPTION_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        ++modCount;
        E removedElement = items[index];
        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }
        items[size - 1] = null;
        --size;
        return removedElement;
    }

    /**
     * Returns the index of the first occurrence of the specified element.
     * Returns -1 if the specified element is not found.
     */
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], object)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], object)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method not implemented.
     * Not necessary by the task.
     */
    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException(EXCEPTION_MESSAGE_METHOD_NOT_IMPLEMENTED);
    }

    /**
     * Method not implemented.
     * Not necessary by the task.
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException(EXCEPTION_MESSAGE_METHOD_NOT_IMPLEMENTED);
    }

    /**
     * Method not implemented.
     * Not necessary by the task.
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException(EXCEPTION_MESSAGE_METHOD_NOT_IMPLEMENTED);
    }

    /**
     * Reduces items[] capacity to current list size.
     */
    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    /**
     * Increases capacity of items
     */
    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    /**
     * Check if the current capacity is not less than specified number
     * and increases it if necessary.
     */
    public void ensureCapacity(int capacity) {
        if (items.length < capacity) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    /**
     * Internal method for removing items from the list with condition 'isPresent'.
     * Used by removeAll and retainAll.
     * Returns true if any items is removed.
     */
    private boolean removeItems(Collection<?> collection, boolean isPresent) {
        boolean modified = false;
        int i = 0;
        while (i < size) {
            if (collection.contains(items[i]) == isPresent) {
                remove(i);
                modified = true;
                continue;
            }
            ++i;
        }
        return modified;
    }

    /**
     * Returns true if the specified object is equal to the list.
     * Not included current value of modCount property.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        MyArrayList<?> myArrayList = (MyArrayList<?>) object;
        if (size != myArrayList.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(myArrayList.items[i], items[i])) {
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
        int result = 1;
        for (int i = 0; i < size; i++) {
            result = 31 * result + (items[i] == null ? 0 : items[i].hashCode());
        }
        return result;
    }

    /**
     * Returns string representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append('[');
        for (int i = 0; i < size; i++) {
            resultString.append(items[i]);
            if (i < size - 1) {
                resultString.append(", ");
            }
        }
        return resultString.append(']').toString();
    }
}
