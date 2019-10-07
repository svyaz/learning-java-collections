package com.github.svyaz.javalearning.singlylinkedlist;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class SinglyLinkedListTest {
    @Test
    public void constructorEmptyTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        Assert.assertEquals(list.size(), 0);
    }

    @Test
    public void constructor1Items1Test() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("Hi there");
        Assert.assertTrue(list.size() == 1 && list.getHeadData().equals("Hi there"));
    }

    @Test
    public void constructor1Items3Test() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        Assert.assertEquals(list.size(), 3);
    }

    @Test
    public void constructor2Test() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("Hi there");
        Assert.assertTrue(list.size() == 1 && list.getHeadData().equals("Hi there"));
    }

    @Test
    public void getDataIndexTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        Assert.assertTrue(list.size() == 3 &&
                list.getData(0).equals("1 item") &&
                list.getData(1).equals("2 item") &&
                list.getData(2).equals("3 item"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getDataIndexException1Test() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        list.getData(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getDataIndexException2Test() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        list.getData(3);
    }

    @Test
    public void setDataTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        Assert.assertEquals(list.setData(1, "Second item!"), "2 item");
    }

    @Test
    public void setDataToHeadByIndexTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        Assert.assertEquals(list.setData(0, "Head item!"), "1 item");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setDataIndexException1Test() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        list.setData(-1, "Hi there");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setDataIndexException2Test() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        list.setData(3, "Hi there");
    }

    @Test
    public void addToHeadTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("John");
        list.addToHead("Elton");
        Assert.assertTrue(list.size() == 2 &&
                list.getData(0).equals("Elton") &&
                list.getData(1).equals("John"));
    }

    @Test
    public void addToHeadOfEmptyTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToHead("Hi there");
        Assert.assertTrue(list.size() == 1 && list.getHeadData().equals("Hi there"));
    }

    @Test
    public void addToTailTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("1 item");
        list.addToTail("2 item");
        list.addToTail("last item");
        Assert.assertTrue(list.size() == 3 &&
                list.getData(0).equals("1 item") &&
                list.getData(1).equals("2 item") &&
                list.getData(2).equals("last item"));
    }

    @Test
    public void addToTailOfEmpty() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("Hi there");
        Assert.assertTrue(list.size() == 1 && list.getHeadData().equals("Hi there"));
    }

    @Test
    public void addByIndexToStartTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.add(0, "0 item");
        Assert.assertTrue(list.size() == 3 &&
                list.getData(0).equals("0 item") &&
                list.getData(1).equals("1 item") &&
                list.getData(2).equals("2 item"));
    }

    @Test
    public void addByIndexToEndTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.add(2, "3 item");
        Assert.assertTrue(list.size() == 3 &&
                list.getData(0).equals("1 item") &&
                list.getData(1).equals("2 item") &&
                list.getData(2).equals("3 item"));
    }

    @Test
    public void addByIndexToMiddleTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        list.add(1, "middle item");
        Assert.assertTrue(list.size() == 4 &&
                list.getData(0).equals("1 item") &&
                list.getData(1).equals("middle item") &&
                list.getData(2).equals("2 item") &&
                list.getData(3).equals("3 item"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addByIndexException1Test() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.add(-1, "-1 item");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addByIndexException2Test() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.add(3, "3 item");
    }

    @Test
    public void getHeadDataTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 element");
        list.addToTail("2 element");
        list.addToTail("3 element");
        Assert.assertEquals(list.getHeadData(), "1 element");
    }

    @Test
    public void getHeadData1ElementTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("Hi there");
        Assert.assertEquals(list.getHeadData(), "Hi there");
    }

    @Test(expected = NoSuchElementException.class)
    public void getHeadDataEmptyListTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.getHeadData();
    }

    @Test
    public void removeHeadTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("1 item");
        list.addToHead("2 item");
        list.addToHead("3 item");
        Assert.assertTrue(list.removeHead().equals("3 item") &&
                list.size() == 2 &&
                list.getData(0).equals("2 item") &&
                list.getData(1).equals("1 item"));
    }

    @Test
    public void removeHead1ElementTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("1 item");
        Assert.assertTrue(list.removeHead().equals("1 item") && list.size() == 0);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeHeadEmptyListTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.removeHead();
    }

    @Test
    public void removeTailTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        Assert.assertTrue(list.removeTail().equals("3 item") &&
                list.size() == 2 &&
                list.getData(0).equals("1 item") &&
                list.getData(1).equals("2 item"));
    }

    @Test
    public void removeTail1ElementTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("1 item");
        Assert.assertTrue(list.removeTail().equals("1 item") && list.size() == 0);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeTailEmptyListTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.removeTail();
    }

    @Test
    public void removeByIndexFromStartTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("111");
        list.addToTail("222");
        list.addToTail("333");
        Assert.assertTrue(list.remove(0).equals("111") &&
                list.size() == 2 &&
                list.getData(0).equals("222") &&
                list.getData(1).equals("333"));
    }

    @Test
    public void removeByIndexFromEndTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("111");
        list.addToTail("222");
        list.addToTail("333");
        Assert.assertTrue(list.remove(2).equals("333") &&
                list.size() == 2 &&
                list.getData(0).equals("111") &&
                list.getData(1).equals("222"));
    }

    @Test
    public void removeByIndexFromMiddleTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("111");
        list.addToTail("222");
        list.addToTail("333");
        Assert.assertTrue(list.remove(1).equals("222") &&
                list.size() == 2 &&
                list.getData(0).equals("111") &&
                list.getData(1).equals("333"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeByIndexException1Test() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("Hi there");
        list.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeByIndexException2Test() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("Hi there");
        list.remove(1);
    }

    @Test
    public void removeElementTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        Assert.assertTrue(list.remove("2 item") &&
                list.size() == 2 &&
                list.getData(0).equals("1 item") &&
                list.getData(1).equals("3 item"));
    }

    @Test
    public void removeElementHeadTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        Assert.assertTrue(list.remove("1 item") &&
                list.size() == 2 &&
                list.getData(0).equals("2 item") &&
                list.getData(1).equals("3 item"));
    }

    @Test
    public void removeElementLastTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        Assert.assertTrue(list.remove("3 item") &&
                list.size() == 2 &&
                list.getData(0).equals("1 item") &&
                list.getData(1).equals("2 item"));
    }

    @Test
    public void removeElementFalseTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        Assert.assertTrue(!list.remove("4 item") &&
                list.size() == 3);
    }

    @Test
    public void removeElementOneTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("1 item");
        Assert.assertTrue(list.remove("1 item") &&
                list.size() == 0);
    }

    @Test
    public void removeElementEmptyTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        Assert.assertTrue(!list.remove("Hi there") &&
                list.size() == 0);
    }

    @Test
    public void removeElementNullTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("Hi there");
        list.addToHead(null);
        list.addToTail(null);
        Assert.assertTrue(list.remove(null) &&
                list.size() == 2 &&
                list.getData(0).equals("Hi there") &&
                list.getData(1) == null);
    }

    @Test
    public void turnTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("One");
        list.addToTail("Two");
        list.addToTail("Three");
        list.addToTail("Four");
        list.turn();
        Assert.assertTrue(list.size() == 4 &&
                list.getData(0).equals("Four") &&
                list.getData(1).equals("Three") &&
                list.getData(2).equals("Two") &&
                list.getData(3).equals("One"));
    }

    @Test
    public void turnEmptyTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.turn();
        Assert.assertEquals(list.size(), 0);
    }

    @Test
    public void turnOneElementTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("Hi there");
        list.turn();
        Assert.assertTrue(list.size() == 1 &&
                list.getHeadData().equals("Hi there"));
    }

    @Test
    public void copyTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("I");
        list.addToTail("like");
        list.addToTail("Java");
        SinglyLinkedList<?> copy = list.copy();
        Assert.assertTrue(copy.size() == 3 &&
                copy.getData(0).equals("I") &&
                copy.getData(1).equals("like") &&
                copy.getData(2).equals("Java"));
    }

    @Test
    public void copyOneElementTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("Hi there");
        SinglyLinkedList<?> copy = list.copy();
        Assert.assertTrue(copy.size() == 1 &&
                copy.getData(0).equals("Hi there"));
    }

    @Test
    public void copyEmptyListTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        SinglyLinkedList<?> copy = list.copy();
        Assert.assertEquals(copy.size(), 0);
    }

    @Test
    public void toStringTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("I");
        list.addToTail("like");
        list.addToTail("Java");
        Assert.assertEquals(list.toString(), "[I, like, Java]");
    }

    @Test
    public void toStringEmptyTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        Assert.assertEquals(list.toString(), "[]");
    }

    @Test
    public void toStringOneElementTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("Hi there");
        Assert.assertEquals(list.toString(), "[Hi there]");
    }

    @Test
    public void equalsSameObjectTest() {
        SinglyLinkedList<String> list1 = new SinglyLinkedList<>("Hi there");
        Assert.assertEquals(list1, list1);
    }

    @Test
    public void equalsNullTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("Hi there");
        Assert.assertNotEquals(list, null);
    }

    @Test
    public void equalsNullsInDataTest() {
        SinglyLinkedList<String> list1 = new SinglyLinkedList<>("Hi there");
        SinglyLinkedList<String> list2 = new SinglyLinkedList<>("Hi there");
        list1.addToTail(null);
        list2.addToTail(null);
        Assert.assertEquals(list1, list2);
    }

    @Test
    public void equalsDifferentTypesObjectsTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("Hi there");
        StringBuilder object = new StringBuilder(list.getHeadData());
        Assert.assertNotEquals(list, object);
    }

    @Test
    public void equalsDifferentDataTypesTest() {
        SinglyLinkedList<Integer> intList = new SinglyLinkedList<>(10);
        SinglyLinkedList<Double> doubleList = new SinglyLinkedList<>(10.0);
        Assert.assertNotEquals(intList, doubleList);
    }

    @Test
    public void equalsDifferentSizesTest() {
        SinglyLinkedList<String> list1 = new SinglyLinkedList<>("Hi there");
        SinglyLinkedList<String> list2 = new SinglyLinkedList<>("Hi there");
        list2.addToTail("Hello world!");
        Assert.assertNotEquals(list1, list2);
    }

    @Test
    public void equalsTrueTest() {
        SinglyLinkedList<String> list1 = new SinglyLinkedList<>("Hi there");
        SinglyLinkedList<String> list2 = new SinglyLinkedList<>("Hi there");
        Assert.assertEquals(list1, list2);
    }

    @Test
    public void equalsFalseTest() {
        SinglyLinkedList<String> list1 = new SinglyLinkedList<>("Hi there");
        list1.addToTail("Hello world!");
        SinglyLinkedList<String> list2 = new SinglyLinkedList<>("Hi there");
        list1.addToTail("Hello universe!");
        Assert.assertNotEquals(list1, list2);
    }

    @Test
    public void hashCode1Test() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addToTail("1 item");
        list.addToTail("2 item");
        list.addToTail("3 item");
        Assert.assertEquals(list.hashCode(), -1990532352);
    }

    @Test
    public void hashCode2Test() {
        SinglyLinkedList<String> list1 = new SinglyLinkedList<>("Hi there");
        SinglyLinkedList<String> list2 = new SinglyLinkedList<>("Hi there");
        Assert.assertEquals(list1.hashCode(), list2.hashCode());
    }

    @Test
    public void hashCode3Test() {
        SinglyLinkedList<String> list1 = new SinglyLinkedList<>();
        list1.addToTail("Hi");
        list1.addToTail("there");
        SinglyLinkedList<String> list2 = new SinglyLinkedList<>();
        list2.addToTail("Hi");
        list2.addToTail("buddy");
        Assert.assertNotEquals(list1.hashCode(), list2.hashCode());
    }

    @Test
    public void hashCodeNullInDataTest() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>("Hi");
        list.addToTail(null);
        list.addToTail("there");
        Assert.assertEquals(list.hashCode(), 112818788);
    }
}
