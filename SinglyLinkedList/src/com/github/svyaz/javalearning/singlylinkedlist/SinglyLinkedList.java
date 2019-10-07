package com.github.svyaz.javalearning.singlylinkedlist;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private static final String EXCEPTION_MESSAGE_INDEX_OUT_OF_BOUNDS = "Specified index is out of list bounds.";
    private static final String EXCEPTION_MESSAGE_EMPTY_LIST = "List is empty.";

    /**
     * Head item of the list.
     */
    private ListItem<T> head;

    /**
     * Counter for getting list size.
     */
    private int count;

    /**
     * Creates empty list.
     */
    public SinglyLinkedList() {
    }

    /**
     * Creates SinglyLinkedList with 1 item specified as head data.
     */
    public SinglyLinkedList(T head) {
        if (head != null) {
            this.head = new ListItem<>(head);
            count = 1;
        }
    }

    /**
     * Returns size of the list.
     */
    public int size() {
        return count;
    }

    /**
     * Returns head-data of the list.
     */
    public T getHeadData() {
        if (count == 0) {
            throw new NoSuchElementException(EXCEPTION_MESSAGE_EMPTY_LIST);
        }
        return head.getData();
    }

    /**
     * Returns data of the element with specified index of the list.
     */
    public T getData(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException(EXCEPTION_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        return getListItem(index).getData();
    }

    /**
     * Sets data of the element with specified index of the list.
     * Returns old value from the element data.
     */
    public T setData(int index, T data) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException(EXCEPTION_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        ListItem<T> currentItem = getListItem(index);
        T currentData = currentItem.getData();
        currentItem.setData(data);
        return currentData;
    }

    /**
     * Adds new element to the beginning of the list.
     */
    public void addToHead(T data) {
        head = new ListItem<>(data, head);
        ++count;
    }

    /**
     * Adds new element to the end of the list.
     */
    public void addToTail(T data) {
        if (count == 0) {
            head = new ListItem<>(data);
        } else {
            ListItem<T> currentTail = getListItem(count - 1);
            currentTail.setNext(new ListItem<>(data));
        }
        ++count;
    }

    /**
     * Adds new element to the specified index of the list.
     */
    public void add(int index, T data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException(EXCEPTION_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }

        if (index == 0) {
            head = new ListItem<>(data, head);
        } else {
            ListItem<T> previousItem = getListItem(index - 1);
            previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
        }
        ++count;
    }

    /**
     * Removes head element of the list.
     * Returns data of removed element or null if the list is empty.
     */
    public T removeHead() {
        if (count == 0) {
            throw new NoSuchElementException(EXCEPTION_MESSAGE_EMPTY_LIST);
        }
        T returnData = head.getData();
        head = head.getNext();
        --count;
        return returnData;
    }

    /**
     * Removes last element of the list.
     * Returns data of removed element or null if the list is empty.
     */
    public T removeTail() {
        if (count == 0) {
            throw new NoSuchElementException(EXCEPTION_MESSAGE_EMPTY_LIST);
        }

        T returnData;
        if (count == 1) {
            returnData = head.getData();
            head = null;
        } else {
            ListItem<T> previousItem = getListItem(count - 2);
            returnData = previousItem.getNext().getData();
            previousItem.setNext(null);
        }
        --count;
        return returnData;
    }

    /**
     * Removes element of the list with specified index.
     * Returns removed element data.
     */
    public T remove(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException(EXCEPTION_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }

        T returnData;
        if (index == 0) {
            returnData = head.getData();
            head = head.getNext();
        } else {
            ListItem<T> previousItem = getListItem(index - 1);
            returnData = previousItem.getNext().getData();
            previousItem.setNext(previousItem.getNext().getNext());
        }
        --count;
        return returnData;
    }

    /**
     * Removes specified element from the list if it presents.
     * Returns true if list is changed.
     */
    public boolean remove(T element) {
        for (ListItem<T> current = head, prev = null;
             current != null;
             prev = current, current = current.getNext()) {

            if (Objects.equals(current.getData(), element)) {
                if (prev == null) {
                    head = head.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                --count;
                return true;
            }
        }
        return false;
    }

    /**
     * Turns the list from end to start.
     */
    public void turn() {
        if (count < 2) {
            return;
        }

        ListItem<T> current = head;
        ListItem<T> prev = null;
        while (true) {
            if (head.getNext() != null) {
                head = head.getNext();
                current.setNext(prev);
                prev = current;
                current = head;
            } else {
                current.setNext(prev);
                return;
            }
        }
    }

    /**
     * Returns a copy of the list.
     */
    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();
        if (count == 0) {
            return newList;
        }
        newList.count = count;
        ListItem<T> tmpPrev = null;
        for (ListItem<T> current = head; current != null; current = current.getNext()) {
            ListItem<T> tmpItemLink = new ListItem<>(current.getData());
            if (tmpPrev != null) {
                tmpPrev.setNext(tmpItemLink);
            } else {
                newList.head = tmpItemLink;
            }
            tmpPrev = tmpItemLink;
        }
        return newList;
    }

    /**
     * Iterates through the list to find element with specified index.
     * Returns ListItem by the index.
     * Internal method.
     */
    private ListItem<T> getListItem(int index) {
        int i = 0;
        ListItem<T> tmpListItem = head;
        while (i < count) {
            if (i == index) {
                break;
            }
            tmpListItem = tmpListItem.getNext();
            ++i;
        }
        return tmpListItem;
    }

    /**
     * Returns string representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append('[');
        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            resultString.append(item.getData());
            if (item.getNext() != null) {
                resultString.append(", ");
            }
        }
        return resultString.append(']').toString();
    }

    /**
     * Returns true if the specified object is equal to the list.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        SinglyLinkedList<?> that = (SinglyLinkedList<?>) object;
        if (count != that.count) {
            return false;
        }
        if (count > 0) {
            ListItem<T> currentThis = this.head;
            ListItem<?> currentThat = that.head;
            while (currentThis != null) {
                if (!Objects.equals(currentThis.getData(), currentThat.getData())) {
                    return false;
                }
                currentThis = currentThis.getNext();
                currentThat = currentThat.getNext();
            }
        }
        return true;
    }

    /**
     * Calculates hash code of the list.
     */
    @Override
    public int hashCode() {
        int result = count;
        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            result += 31 * result + item.hashCode();
        }
        return result;
    }
}
