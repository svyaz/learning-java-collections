package com.github.svyaz.javalearning.myarraylist;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MyArrayListTest {
    @Test
    public void constructor1Test() {
        MyArrayList<Object> list = new MyArrayList<>();
        Assert.assertNotNull(list);
    }

    @Test
    public void constructor2Test() {
        MyArrayList<Object> list = new MyArrayList<>(15);
        Assert.assertNotNull(list);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor2ExceptionTest() {
        MyArrayList<Object> list = new MyArrayList<>(0);
        Assert.assertNull(list);
    }

    @Test
    public void constructor3Test() {
        MyArrayList<Object> list = new MyArrayList<>(new String[]{"One", "Two", "Three"});
        Assert.assertTrue(list.size() == 3 &&
                list.get(0).equals("One") &&
                list.get(1).equals("Two") &&
                list.get(2).equals("Three"));
    }

    @Test
    public void equalsSameObjectTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"1", "2", "3"});
        Assert.assertEquals(list, list);
    }

    @Test
    public void equalsDifferentClassesTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"1", "2", "3"});
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("1", "2", "3"));
        Assert.assertNotEquals(list, arrayList);
    }

    @Test
    public void equalsNullObjectTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"1", "2", "3"});
        Assert.assertNotEquals(list, null);
    }

    @Test
    public void equalsDifferentObjectsTest() {
        MyArrayList<String> list1 = new MyArrayList<>(new String[]{"1", "2", "3"});
        MyArrayList<String> list2 = new MyArrayList<>(new String[]{"1", "2", "4"});
        Assert.assertNotEquals(list1, list2);
    }

    @Test
    public void equalsDifferentSizesTest() {
        MyArrayList<String> list1 = new MyArrayList<>(new String[]{"1", "2"});
        MyArrayList<String> list2 = new MyArrayList<>(new String[]{"1", "2", "3"});
        Assert.assertNotEquals(list1, list2);
    }

    @Test
    public void equalsEqualObjectsTest() {
        MyArrayList<String> list1 = new MyArrayList<>(new String[]{"1", "2", "3"});
        MyArrayList<String> list2 = new MyArrayList<>(new String[]{"1", "2", "3"});
        Assert.assertEquals(list1, list2);
    }

    @Test
    public void hashCode1Test() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"One", "Two", "Three", "Four", "Five"});
        Assert.assertEquals(list.hashCode(), -1010478461);
    }

    @Test
    public void hashCode2Test() {
        MyArrayList<String> list1 = new MyArrayList<>(new String[]{"One", "Two", "Three", "Four", "Five"});
        MyArrayList<String> list2 = new MyArrayList<>(new String[]{"One", "Two", "Three", "Four", "Five"});
        Assert.assertEquals(list1.hashCode(), list2.hashCode());
    }

    @Test
    public void hashCode3Test() {
        MyArrayList<String> list1 = new MyArrayList<>(new String[]{"One", "Two", "Three", "Four", "Five"});
        MyArrayList<String> list2 = new MyArrayList<>(new String[]{"One", "Two", "Three", "Four", "Five", "Six"});
        Assert.assertNotEquals(list1.hashCode(), list2.hashCode());
    }

    @Test
    @SuppressWarnings("all")
    public void toString1Test() {
        MyArrayList<String> list = new MyArrayList<>(15);
        Assert.assertEquals("[]", list.toString());
    }

    @Test
    public void toString2Test() {
        MyArrayList<Double> list = new MyArrayList<>(new Double[]{1.0, 2.0, null, 4.0});
        Assert.assertEquals("[1.0, 2.0, null, 4.0]", list.toString());
    }

    @Test
    public void toString3Test() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"One", "Two", "Three", "Four"});
        Assert.assertEquals("[One, Two, Three, Four]", list.toString());
    }

    @Test
    @SuppressWarnings("all")
    public void sizeEmptyTest() {
        MyArrayList<String> list = new MyArrayList<>();
        Assert.assertEquals(list.size(), 0);
    }

    @Test
    public void sizeNotEmptyTest() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{5, 2});
        Assert.assertEquals(list.size(), 2);
    }

    @Test
    @SuppressWarnings("all")
    public void isEmptyTest() {
        MyArrayList<Integer> list = new MyArrayList<>();
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void isNotEmptyTest() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(5);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void addTest() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Hi there");
        Assert.assertTrue(list.size() == 1 &&
                list.get(0).getClass() == String.class &&
                list.get(0).equals("Hi there"));
    }

    @Test
    public void addNullTest() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add(null);
        Assert.assertTrue(list.size() == 1 &&
                list.get(0) == null);
    }

    @Test
    public void addWithIndex0Test() {
        MyArrayList<Integer> list = new MyArrayList<>(3);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(0, 10);
        Assert.assertTrue(list.size() == 4 &&
                list.get(0) == 10 && list.get(1) == 0 && list.get(2) == 1 && list.get(3) == 2);
    }

    @Test
    public void addWithIndexLastTest() {
        MyArrayList<Integer> list = new MyArrayList<>(3);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(2, 10);
        Assert.assertTrue(list.size() == 4 &&
                list.get(0) == 0 && list.get(1) == 1 && list.get(2) == 10 && list.get(3) == 2);
    }

    @Test
    public void addWithIndexSizeTest() {
        MyArrayList<Integer> list = new MyArrayList<>(3);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3, 10);
        Assert.assertTrue(list.size() == 4 &&
                list.get(0) == 0 && list.get(1) == 1 && list.get(2) == 2 && list.get(3) == 10);
    }

    @Test
    public void addWithIndexMiddleTest() {
        MyArrayList<Integer> list = new MyArrayList<>(3);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(1, 10);
        Assert.assertTrue(list.size() == 4 &&
                list.get(0) == 0 && list.get(1) == 10 && list.get(2) == 1 && list.get(3) == 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    @SuppressWarnings("all")
    public void addException1Test() {
        MyArrayList<Integer> list = new MyArrayList<>(3);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(-1, 100);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    @SuppressWarnings("all")
    public void addException2Test() {
        MyArrayList<Integer> list = new MyArrayList<>(3);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(4, 100);
    }

    @Test
    public void removeAllTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"One", "Two", "Two", "Three", "Four", "Two"});
        String[] strings = {"One", "Two"};
        Assert.assertTrue(list.removeAll(Arrays.asList(strings)) &&
                list.size() == 2 &&
                list.get(0).equals("Three") &&
                list.get(1).equals("Four"));
    }

    @Test
    public void removeAllFalseTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"One", "Two", "Two", "Three", "Four", "Two"});
        String[] strings = {"Zero"};
        Assert.assertFalse(list.removeAll(Arrays.asList(strings)));
    }

    @Test
    public void removeAllFalseNullTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"One", "Two", "Two", "Three", "Four", "Two"});
        String[] strings = {null};
        Assert.assertFalse(list.removeAll(Arrays.asList(strings)));
    }

    @Test
    @SuppressWarnings("all")
    public void removeAllFalseEmptyTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"One", "Two", "Two", "Three", "Four", "Two"});
        String[] strings = {};
        Assert.assertFalse(list.removeAll(Arrays.asList(strings)));
    }

    @Test
    public void retainAllTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"One", "Two", "Two", "Three", "Four", "Two"});
        String[] strings = {"One", "Two", "Eight"};
        Assert.assertTrue(list.retainAll(Arrays.asList(strings)) &&
                list.size() == 4 &&
                list.get(0).equals("One") &&
                list.get(1).equals("Two") &&
                list.get(2).equals("Two") &&
                list.get(3).equals("Two"));
    }

    @Test
    public void retainAllFalseTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"One", "Two", "Two", "Three", "Four", "Two"});
        String[] strings = {"One", "Two", "Three", "Four"};
        Assert.assertFalse(list.retainAll(Arrays.asList(strings)));
    }

    @Test
    public void retainAllNullTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"One", "Two", "Two", "Three", "Four", "Two"});
        String[] strings = {null};
        Assert.assertTrue(list.retainAll(Arrays.asList(strings)) && list.size() == 0);
    }

    @Test
    @SuppressWarnings("all")
    public void retainAllEmptyTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"One", "Two", "Two", "Three", "Four", "Two"});
        String[] strings = {};
        Assert.assertTrue(list.retainAll(Arrays.asList(strings)) && list.size() == 0);
    }

    @Test
    public void remove0Test() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{10, 11, 12});
        Assert.assertTrue(list.remove(0) == 10 &&
                list.size() == 2 && list.get(0) == 11 && list.get(1) == 12);
    }

    @Test
    public void removeLastTest() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{10, 11, 12});
        Assert.assertTrue(list.remove(2) == 12 &&
                list.size() == 2 && list.get(0) == 10 && list.get(1) == 11);
    }

    @Test
    public void removeMiddleTest() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{10, 11, 12});
        Assert.assertTrue(list.remove(1) == 11 &&
                list.size() == 2 && list.get(0) == 10 && list.get(1) == 12);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeException1Test() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{10, 11, 12});
        list.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeException2Test() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{10, 11, 12});
        list.remove(3);
    }

    @Test
    public void indexOfTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertEquals(list.indexOf("like"), 1);
    }

    @Test
    public void indexOfNotFoundTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertEquals(list.indexOf("C++"), -1);
    }

    @Test
    public void indexOfNullFoundTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "don't", "like", null});
        Assert.assertEquals(list.indexOf(null), 3);
    }

    @Test
    public void indexOfNullNotFoundTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertEquals(list.indexOf(null), -1);
    }

    @Test
    public void lastIndexOfTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "like", "Java"});
        Assert.assertEquals(list.lastIndexOf("like"), 2);
    }

    @Test
    public void lastIndexOfNotFoundTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertEquals(list.lastIndexOf("C++"), -1);
    }

    @Test
    public void lastIndexOfNullFoundTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "don't", "like", null, "Oh", null, "no"});
        Assert.assertEquals(list.lastIndexOf(null), 5);
    }

    @Test
    public void lastIndexOfNullNotFoundTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertEquals(list.lastIndexOf(null), -1);
    }

    @Test
    public void containsTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertTrue(list.contains("like"));
    }

    @Test
    public void containsFalseTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertFalse(list.contains("C++"));
    }

    @Test
    public void containsNullTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "don't", "like", null});
        Assert.assertTrue(list.contains(null));
    }

    @Test
    public void containsNullFalseTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertFalse(list.contains(null));
    }

    @Test
    public void containsAllTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        String[] strings = {"Java", "like"};
        Assert.assertTrue(list.containsAll(Arrays.asList(strings)));
    }

    @Test
    public void containsAllFalseTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        String[] strings = {"Java", "C++"};
        Assert.assertFalse(list.containsAll(Arrays.asList(strings)));
    }

    @Test
    public void containsAllNullTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "don't", "like", null});
        String[] strings = {"I", null};
        Assert.assertTrue(list.containsAll(Arrays.asList(strings)));
    }

    @Test
    public void containsAllNullFalseTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        String[] strings = {"Java", null};
        Assert.assertFalse(list.containsAll(Arrays.asList(strings)));
    }

    @Test
    public void iteratorTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Iterator<String> iterator = list.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next()).append("-");
        }
        Assert.assertEquals(sb.toString(), "I-like-Java-");
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorException1Test() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"1 element"});
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    @SuppressWarnings("unused")
    public void iteratorException2Test() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"1 element", "2 element"});
        for (String string : list) {
            list.add("next element");
        }
    }

    @Test
    public void addAllTest() {
        MyArrayList<String> list = new MyArrayList<>();
        Assert.assertTrue(list.addAll(Arrays.asList("I", "like", "Java")) &&
                list.size() == 3 &&
                list.get(0).equals("I") &&
                list.get(1).equals("like") &&
                list.get(2).equals("Java"));
    }

    @Test
    public void addAllIncreaseCapacityTest() {
        MyArrayList<Number> list = new MyArrayList<>(new Number[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        Double[] doubles = {100.0, 200.0, 300.0};
        Assert.assertTrue(list.addAll(Arrays.asList(doubles)) &&
                list.size() == 13 &&
                list.get(10).equals(100.0) &&
                list.get(11).equals(200.0) &&
                list.get(12).equals(300.0));
    }

    @Test
    public void addAllEmptyTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertFalse(list.addAll(Arrays.asList(new String[]{})));
    }

    @Test
    public void addAllIndexTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertTrue(list.addAll(1, Arrays.asList("very", "much")) &&
                list.size() == 5 &&
                list.get(0).equals("I") &&
                list.get(1).equals("very") &&
                list.get(2).equals("much") &&
                list.get(3).equals("like") &&
                list.get(4).equals("Java"));
    }

    @Test
    public void addAllIndexAt0Test() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertTrue(list.addAll(0, Arrays.asList("very", "much")) &&
                list.size() == 5 &&
                list.get(0).equals("very") &&
                list.get(1).equals("much") &&
                list.get(2).equals("I") &&
                list.get(3).equals("like") &&
                list.get(4).equals("Java"));
    }

    @Test
    public void addAllIndexAtLastTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertTrue(list.addAll(2, Arrays.asList("very", "much")) &&
                list.size() == 5 &&
                list.get(0).equals("I") &&
                list.get(1).equals("like") &&
                list.get(2).equals("very") &&
                list.get(3).equals("much") &&
                list.get(4).equals("Java"));
    }

    @Test
    public void addAllIndexAtSizeTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertTrue(list.addAll(3, Arrays.asList("very", "much")) &&
                list.size() == 5 &&
                list.get(0).equals("I") &&
                list.get(1).equals("like") &&
                list.get(2).equals("Java") &&
                list.get(3).equals("very") &&
                list.get(4).equals("much"));
    }

    @Test
    public void addAllIndexFalseTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertFalse(list.addAll(1, Arrays.asList(new String[]{})));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllIndexExceptionTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        list.addAll(5, Arrays.asList("12", "13"));
    }

    @Test
    public void toArrayTest() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{10, 11, 12});
        Object[] result = list.toArray();
        Assert.assertTrue(result.length == 3 &&
                (int) result[0] == 10 && (int) result[1] == 11 && (int) result[2] == 12);
    }

    @Test
    @SuppressWarnings("all")
    public void toArrayEmptyTest() {
        MyArrayList<Integer> list = new MyArrayList<>();
        Assert.assertEquals(list.toArray().length, 0);
    }

    @Test
    public void toArrayT1Test() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        String[] strings = {"str_0", "str_1", "str_2", "str_3"};
        String[] newStrings = list.toArray(strings);
        Assert.assertTrue(newStrings == strings &&
                newStrings[0].equals("I") &&
                newStrings[1].equals("like") &&
                newStrings[2].equals("Java") &&
                newStrings[3] == null);
    }

    @Test
    public void toArrayT2Test() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        String[] strings = {"str_0", "str_1", "str_2"};
        String[] newStrings = list.toArray(strings);
        Assert.assertTrue(newStrings == strings &&
                newStrings[0].equals("I") &&
                newStrings[1].equals("like") &&
                newStrings[2].equals("Java"));
    }

    @Test
    public void toArrayT3Test() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        String[] strings = {"str_0", "str_1"};
        String[] newStrings = list.toArray(strings);
        Assert.assertTrue(newStrings != strings &&
                newStrings[0].equals("I") &&
                newStrings[1].equals("like") &&
                newStrings[2].equals("Java"));
    }

    @Test
    public void removeObjectTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertTrue(list.remove("like"));
    }

    @Test
    public void removeObjectFalseTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertFalse(list.remove("C++"));
    }

    @Test
    public void removeObjectNullTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "don't", "like", null});
        Assert.assertTrue(list.remove(null));
    }

    @Test
    public void removeObjectNullFalseTest() {
        MyArrayList<String> list = new MyArrayList<>(new String[]{"I", "like", "Java"});
        Assert.assertFalse(list.remove(null));
    }

    @Test
    public void addWithIncreaseCapacityTest() {
        MyArrayList<Integer> list = new MyArrayList<>(1);
        list.add(5);
        list.add(2);
        Assert.assertTrue(list.size() == 2 &&
                list.get(0) == 5 &&
                list.get(1) == 2);
    }

    @Test
    public void clearTest() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{0, 1, 2});
        list.clear();
        Assert.assertEquals(list.size(), 0);
    }

    @Test
    public void getTest() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(5);
        Assert.assertEquals(list.get(0).intValue(), 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    @SuppressWarnings("all")
    public void getExceptionTest() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.get(1);
    }

    @Test
    public void setTest() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(5);
        int currentElement = list.set(0, 2);
        Assert.assertTrue(list.get(0) == 2 && currentElement == 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setExceptionTest() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{});
        list.set(1, 2);
    }

    /**
     * Nothing asserts in trimToSizeTest below.
     * Test only fails if any exception is thrown.
     */
    @Test
    public void trimToSizeTest() {
        MyArrayList<Integer> list = new MyArrayList<>(4);
        list.add(5);
        list.trimToSize();
    }

    /**
     * Nothing asserts in ensureCapacityTest below.
     * Test only fails if any exception is thrown.
     */
    @Test
    public void ensureCapacityTest() {
        MyArrayList<Integer> list = new MyArrayList<>(4);
        list.ensureCapacity(5);
    }

    /**
     * Tests UnsupportedOperationException for not-implemented method.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void listIteratorExceptionTest() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{0, 1, 2});
        list.listIterator();
    }

    /**
     * Tests UnsupportedOperationException for not-implemented method.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void listIteratorIndexExceptionTest() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{0, 1, 2});
        list.listIterator(1);
    }

    /**
     * Tests UnsupportedOperationException for not-implemented method.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void subListExceptionTest() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{0, 1, 2});
        list.subList(0, 1);
    }
}
