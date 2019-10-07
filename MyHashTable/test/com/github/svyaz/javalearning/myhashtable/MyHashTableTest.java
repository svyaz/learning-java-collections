package com.github.svyaz.javalearning.myhashtable;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class MyHashTableTest {
    @Test
    public void constructor1Test() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        Assert.assertNotNull(hashTable);
    }

    @Test
    public void constructor2Test() {
        MyHashTable<String> hashTable = new MyHashTable<>(15);
        Assert.assertNotNull(hashTable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor2ExceptionTest() {
        new MyHashTable<String>(-10);
    }

    @Test
    public void toArrayTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi");
        hashTable.add("there!");
        hashTable.add(null);
        hashTable.add("Hi there!");
        Object[] objects = hashTable.toArray();
        Assert.assertTrue(objects.length == 4 &&
                Objects.equals(objects[0], null) &&
                objects[1].equals("Hi there!") &&
                objects[2].equals("Hi") &&
                objects[3].equals("there!"));
    }

    @Test
    public void toArrayEmptyTest() {
        Assert.assertEquals(new MyHashTable<>().toArray().length, 0);
    }

    @Test
    public void toArrayWithParamLessLengthTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi");
        hashTable.add("there");
        hashTable.add("!");
        String[] strings = {"Hi there!"};
        String[] newStrings = hashTable.toArray(strings);
        Assert.assertTrue(strings != newStrings && newStrings.length == 3 &&
                newStrings[0].equals("!") &&
                newStrings[1].equals("there") &&
                newStrings[2].equals("Hi"));
    }

    @Test
    public void toArrayWithParamGreaterLengthTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi");
        hashTable.add("there");
        String[] strings = new String[3];
        hashTable.toArray(strings);
        Assert.assertTrue(strings[0].equals("there") &&
                strings[1].equals("Hi") &&
                strings[2] == null);
    }

    @Test(expected = NullPointerException.class)
    public void toArrayWithParamNullPointerExceptionTest() {
        MyHashTable<Integer> hashTable = new MyHashTable<>();
        hashTable.add(1);
        hashTable.add(2);
        hashTable.toArray(null);
    }

    @Test(expected = ArrayStoreException.class)
    @SuppressWarnings("all")
    public void toArrayWithParamArrayStoreExceptionTest() {
        MyHashTable<Integer> hashTable = new MyHashTable<>();
        hashTable.add(1);
        hashTable.add(2);
        Double[] array = new Double[3];
        hashTable.toArray(array);
    }

    @Test
    public void addAndSizeTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi there!");
        hashTable.add("I");
        hashTable.add("like");
        hashTable.add("Java");
        Assert.assertEquals(hashTable.size(), 4);
    }

    @Test
    public void addNullTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add(null);
        hashTable.add(null);
        Assert.assertEquals(hashTable.size(), 2);
    }

    @Test
    public void removeEmptyTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        Assert.assertFalse(hashTable.remove("Hi there!"));
    }

    @Test
    public void removeFalseTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi");
        hashTable.add("there!");
        Assert.assertFalse(hashTable.remove("Hi there!"));
    }

    @Test
    public void removeTrueTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi");
        hashTable.add("there!");
        hashTable.add("Hi there!");
        Assert.assertTrue(hashTable.remove("Hi there!"));
    }

    @Test
    public void removeSomeEqualsTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("One");
        hashTable.add("Two");
        hashTable.add("Two");
        hashTable.add("Three");
        hashTable.add("Four");
        hashTable.add("Two");
        Assert.assertTrue(hashTable.remove("Two") &&
                hashTable.size() == 5 &&
                hashTable.contains("One") &&
                hashTable.contains("Three") &&
                hashTable.contains("Four") &&
                hashTable.contains("Two"));
    }

    @Test
    public void removeOneArrayItemFalseTest() {
        MyHashTable<String> hashTable = new MyHashTable<>(1);
        hashTable.add("1");
        hashTable.add("2");
        hashTable.add("3");
        Assert.assertFalse(hashTable.remove("4"));
    }

    @Test
    public void removeOneArrayItemTrueTest() {
        MyHashTable<String> hashTable = new MyHashTable<>(1);
        hashTable.add("1");
        hashTable.add("2");
        hashTable.add("3");
        Assert.assertTrue(hashTable.remove("2") && hashTable.size() == 2);
    }

    @Test
    public void removeNullFalseTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("1");
        hashTable.add("2");
        hashTable.add("3");
        Assert.assertFalse(hashTable.remove(null));
    }

    @Test
    public void removeNullTrueTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi");
        hashTable.add(null);
        hashTable.add("null");
        Assert.assertTrue(hashTable.remove(null) && hashTable.size() == 2);
    }

    @Test
    public void containsAllTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("I");
        hashTable.add("like");
        hashTable.add("Java");
        String[] strings = {"Java", "like"};
        Assert.assertTrue(hashTable.containsAll(Arrays.asList(strings)));
    }

    @Test
    public void containsAllFalseTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("I");
        hashTable.add("like");
        hashTable.add("Java");
        String[] strings = {"Java", "C++"};
        Assert.assertFalse(hashTable.containsAll(Arrays.asList(strings)));
    }

    @Test
    public void containsAllNullTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("I");
        hashTable.add("don't");
        hashTable.add("like");
        hashTable.add(null);
        String[] strings = {"I", null};
        Assert.assertTrue(hashTable.containsAll(Arrays.asList(strings)));
    }

    @Test
    public void containsAllNullFalseTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("I");
        hashTable.add("like");
        hashTable.add("Java");
        String[] strings = {"Java", null};
        Assert.assertFalse(hashTable.containsAll(Arrays.asList(strings)));
    }

    @Test
    public void addAllTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        Assert.assertTrue(hashTable.addAll(Arrays.asList("I", "like", "Java")) &&
                hashTable.size() == 3 &&
                hashTable.contains("I") &&
                hashTable.contains("like") &&
                hashTable.contains("Java"));
    }

    @Test
    public void addAllWithNullTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        Assert.assertTrue(hashTable.addAll(Arrays.asList("I", "like", "Java", null)) &&
                hashTable.size() == 4 &&
                hashTable.contains(null) &&
                hashTable.contains("I") &&
                hashTable.contains("like") &&
                hashTable.contains("Java"));
    }

    @Test
    public void addAllEmptyCollectionTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi there!");
        Assert.assertFalse(hashTable.addAll(Arrays.asList(new String[]{})));
    }

    @Test(expected = NullPointerException.class)
    public void addAllNullPointerExceptionTest() {
        new MyHashTable<>().addAll(null);
    }

    @Test
    public void removeAllTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("One");
        hashTable.add("Two");
        hashTable.add("Two");
        hashTable.add("Three");
        hashTable.add("Four");
        hashTable.add("Two");
        String[] strings = {"One", "Two"};
        Assert.assertTrue(hashTable.removeAll(Arrays.asList(strings)) &&
                hashTable.size() == 2 &&
                hashTable.contains("Three") &&
                hashTable.contains("Four") &&
                !hashTable.contains("One") &&
                !hashTable.contains("Two"));
    }

    @Test
    public void removeAllFalseTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("One");
        hashTable.add("Two");
        hashTable.add("Two");
        hashTable.add("Three");
        hashTable.add("Four");
        hashTable.add("Two");
        String[] strings = {"Zero"};
        Assert.assertFalse(hashTable.removeAll(Arrays.asList(strings)));
    }

    @Test
    public void removeAllEmptyTableTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        String[] strings = {"Zero"};
        Assert.assertFalse(hashTable.removeAll(Arrays.asList(strings)));
    }

    @Test
    public void removeAllFalseNullTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("One");
        hashTable.add("Two");
        hashTable.add("Two");
        hashTable.add("Three");
        hashTable.add("Four");
        hashTable.add("Two");
        String[] strings = {null};
        Assert.assertFalse(hashTable.removeAll(Arrays.asList(strings)));
    }

    @Test
    @SuppressWarnings("all")
    public void removeAllFalseEmptyTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("One");
        hashTable.add("Two");
        hashTable.add("Two");
        hashTable.add("Three");
        hashTable.add("Four");
        hashTable.add("Two");
        String[] strings = {};
        Assert.assertFalse(hashTable.removeAll(Arrays.asList(strings)));
    }

    @Test(expected = NullPointerException.class)
    public void removeAllNullCollectionTest() {
        new MyHashTable<>().removeAll(null);
    }

    @Test
    public void retainAllTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("One");
        hashTable.add("Two");
        hashTable.add("Two");
        hashTable.add("Three");
        hashTable.add("Four");
        hashTable.add("Two");
        String[] strings = {"One", "Two", "Six"};
        Assert.assertTrue(hashTable.retainAll(Arrays.asList(strings)) &&
                hashTable.size() == 4 &&
                hashTable.contains("One") &&
                hashTable.contains("Two") &&
                !hashTable.contains("Six"));
    }

    @Test
    public void retainAllToEmptyTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("One");
        hashTable.add("Two");
        hashTable.add("Two");
        hashTable.add("Three");
        hashTable.add("Four");
        hashTable.add("Two");
        String[] strings = {"Zero"};
        Assert.assertTrue(hashTable.retainAll(Arrays.asList(strings)) &&
                hashTable.size() == 0);
    }

    @Test
    public void retainAllEmptyTableTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        String[] strings = {"Zero"};
        Assert.assertFalse(hashTable.retainAll(Arrays.asList(strings)));
    }

    @Test
    public void retainAllFalseEmptyTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("One");
        hashTable.add("Two");
        hashTable.add(null);
        hashTable.add("Two");
        hashTable.add("Three");
        hashTable.add("Four");
        hashTable.add("Two");
        String[] strings = {"One", "Two", "Three", "Four", null};
        Assert.assertFalse(hashTable.retainAll(Arrays.asList(strings)));
    }

    @Test(expected = NullPointerException.class)
    public void retainAllFalseNullTest() {
        new MyHashTable<>().retainAll(null);
    }

    @Test
    @SuppressWarnings("all")
    public void sizeOfEmptyTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        Assert.assertEquals(hashTable.size(), 0);
    }

    @Test
    @SuppressWarnings("all")
    public void isEmptyTrueTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        Assert.assertTrue(hashTable.isEmpty());
    }

    @Test
    public void isEmptyFalseTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi there");
        Assert.assertFalse(hashTable.isEmpty());
    }

    @Test
    public void clearTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi there");
        hashTable.clear();
        Assert.assertEquals(hashTable.size(), 0);
    }

    @Test
    @SuppressWarnings("all")
    public void containsEmptyTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        Assert.assertFalse(hashTable.contains("Hi there"));
    }

    @Test
    public void containsNullArrayItemTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("1");
        Assert.assertFalse(hashTable.contains("2"));
    }

    @Test
    public void containsOneArrayItemFalseTest() {
        MyHashTable<String> hashTable = new MyHashTable<>(1);
        hashTable.add("1");
        hashTable.add("2");
        hashTable.add("3");
        Assert.assertFalse(hashTable.contains("4"));
    }

    @Test
    public void containsOneArrayItemTrueTest() {
        MyHashTable<String> hashTable = new MyHashTable<>(1);
        hashTable.add("1");
        hashTable.add("2");
        hashTable.add("3");
        Assert.assertTrue(hashTable.contains("2"));
    }

    @Test
    public void containsNullFalseTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi there");
        Assert.assertFalse(hashTable.contains(null));
    }

    @Test
    public void containsNullTrueTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi there");
        hashTable.add(null);
        Assert.assertTrue(hashTable.contains(null));
    }

    @Test
    public void containsTrueTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("I");
        hashTable.add("like");
        hashTable.add("Java");
        Assert.assertTrue(hashTable.contains("Java"));
    }

    @Test
    public void containsFalseTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("I");
        hashTable.add("like");
        hashTable.add("Java");
        Assert.assertFalse(hashTable.contains("C++"));
    }

    @Test
    public void iteratorTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Zero");
        hashTable.add(null);
        hashTable.add("One");
        hashTable.add("Two");
        hashTable.add("Three");
        hashTable.add("Four");
        Iterator<String> iterator = hashTable.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next()).append("-");
        }
        Assert.assertEquals(sb.toString(), "null-One-Four-Two-Three-Zero-");
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorException1Test() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi there");
        Iterator<String> iterator = hashTable.iterator();
        iterator.next();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    @SuppressWarnings("unused")
    public void iteratorException2Test() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi");
        hashTable.add("there");
        for (String string : hashTable) {
            hashTable.add("next element");
        }
    }

    @Test
    public void toStringTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi");
        hashTable.add("there!");
        hashTable.add("Hi there!");
        Assert.assertEquals(hashTable.toString(), "[Hi there!, Hi, there!]");
    }

    @Test
    public void toStringNullTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi");
        hashTable.add("there!");
        hashTable.add(null);
        Assert.assertEquals(hashTable.toString(), "[null, Hi, there!]");
    }

    @Test
    public void toStringEmptyTest() {
        Assert.assertEquals(new MyHashTable<>(15).toString(), "[]");
    }

    @Test
    public void hashCode1Test() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("One");
        hashTable.add("Two");
        hashTable.add("Three");
        hashTable.add("Four");
        hashTable.add("Five");
        Assert.assertEquals(hashTable.hashCode(), 1571857435);
    }

    @Test
    public void hashCode2Test() {
        MyHashTable<String> hashTable1 = new MyHashTable<>();
        hashTable1.add("Hi");
        hashTable1.add("there");
        MyHashTable<String> hashTable2 = new MyHashTable<>();
        hashTable2.add("Hi");
        hashTable2.add("there");
        Assert.assertEquals(hashTable1.hashCode(), hashTable2.hashCode());
    }

    @Test
    public void hashCode3Test() {
        MyHashTable<String> hashTable1 = new MyHashTable<>();
        hashTable1.add("Hi");
        hashTable1.add("there");
        hashTable1.add("!!!");
        MyHashTable<String> hashTable2 = new MyHashTable<>();
        hashTable2.add("Hi");
        hashTable2.add("there");
        Assert.assertNotEquals(hashTable1.hashCode(), hashTable2.hashCode());
    }

    @Test
    public void equalsSameObjectTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi there");
        Assert.assertEquals(hashTable, hashTable);
    }

    @Test
    public void equalsDifferentClassesTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("One");
        hashTable.add("Two");
        hashTable.add("Three");
        String[] strings = {"One", "Two", "Three"};
        Assert.assertNotEquals(hashTable, strings);
    }

    @Test
    public void equalsNullObjectTest() {
        MyHashTable<String> hashTable = new MyHashTable<>();
        hashTable.add("Hi there");
        Assert.assertNotEquals(hashTable, null);
    }

    @Test
    public void equalsDifferentObjectsTest() {
        MyHashTable<String> hashTable1 = new MyHashTable<>();
        hashTable1.add("Hi");
        hashTable1.add("there");
        MyHashTable<String> hashTable2 = new MyHashTable<>();
        hashTable2.add("Hi");
        hashTable2.add("there");
        Assert.assertEquals(hashTable1, hashTable2);
    }

    @Test
    public void equalsDifferentSizesTest() {
        MyHashTable<String> hashTable1 = new MyHashTable<>();
        hashTable1.add("Hi");
        hashTable1.add("there");
        hashTable1.add("!!!");
        MyHashTable<String> hashTable2 = new MyHashTable<>();
        hashTable2.add("Hi");
        hashTable2.add("there");
        Assert.assertNotEquals(hashTable1, hashTable2);
    }

    @Test
    public void equalsEqualObjectsTest() {
        MyHashTable<String> hashTable1 = new MyHashTable<>();
        hashTable1.add("One");
        hashTable1.add("Two");
        hashTable1.add("Three");
        MyHashTable<String> hashTable2 = new MyHashTable<>();
        hashTable2.add("One");
        hashTable2.add("Two");
        hashTable2.add("Three");
        Assert.assertEquals(hashTable1, hashTable2);
    }
}
