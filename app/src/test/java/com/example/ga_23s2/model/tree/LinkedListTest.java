package com.example.ga_23s2.model.tree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class LinkedListTest {
  private void testHelper(Integer[] expected, List<Integer> actual) {
    for (int i = 0; i < expected.length; i++) {
      assertEquals(expected[i], actual.get(i));
    }
  }

  @Test
  public void TestEmpty() {
    LinkedList<Integer> list = new LinkedList<>();
    assertEquals(new ArrayList<>(), list.toList());
    assertEquals(new ArrayList<>(), list.toListReverse());
  }

  @Test
  public void TestAdd() {
    LinkedList<Integer> list = new LinkedList<>();
    assertTrue(list.add(new LinkedNode<>(1)));
    assertFalse(list.add(new LinkedNode<>(1)));
    assertTrue(list.add(new LinkedNode<>(10)));
    assertTrue(list.add(new LinkedNode<>(20)));
    assertFalse(list.add(new LinkedNode<>(10)));
  }

  @Test
  public void TestList() {
    LinkedList<Integer> list = new LinkedList<>();

    list.add(new LinkedNode<>(3));
    list.add(new LinkedNode<>(10));
    list.add(new LinkedNode<>(7));

    testHelper(new Integer[] {3, 10, 7}, list.toList());
    testHelper(new Integer[] {7, 10, 3}, list.toListReverse());

    list.add(new LinkedNode<>(12));
    list.add(new LinkedNode<>(80));
    list.add(new LinkedNode<>(1));

    testHelper(new Integer[] {3, 10, 7, 12, 80, 1}, list.toList());
    testHelper(new Integer[] {1, 80, 12, 7, 10, 3}, list.toListReverse());
  }

  @Test
  public void testSize() {
    LinkedList<Integer> list = new LinkedList<>();
    assertEquals(0, list.size());

    list.add(new LinkedNode<>(1));
    list.add(new LinkedNode<>(2));

    assertEquals(2, list.size());

    list.removeValue(1);
    assertEquals(1, list.size());
  }

  @Test
  public void testRemoveValue() {
    LinkedList<Integer> list = new LinkedList<>();
    list.add(new LinkedNode<>(1));
    list.add(new LinkedNode<>(2));
    list.add(new LinkedNode<>(3));

    assertTrue(list.contains(2));
    list.removeValue(2);
    assertFalse(list.contains(2));
    assertEquals(2, list.size());
  }

  @Test
  public void testContains() {
    LinkedList<Integer> list = new LinkedList<>();
    list.add(new LinkedNode<>(1));
    list.add(new LinkedNode<>(2));

    assertTrue(list.contains(1));
    assertTrue(list.contains(2));
    assertFalse(list.contains(3));
  }

  @Test
  public void testClearList() {
    LinkedList<Integer> list = new LinkedList<>();
    list.add(new LinkedNode<>(1));
    list.add(new LinkedNode<>(2));

    list.removeValue(1);
    list.removeValue(2);

    assertEquals(0, list.size());
    assertTrue(list.toList().isEmpty());
  }

  @Test
  public void testAddAfterRemove() {
    LinkedList<Integer> list = new LinkedList<>();
    list.add(new LinkedNode<>(5));
    list.add(new LinkedNode<>(10));
    list.removeValue(5);

    list.add(new LinkedNode<>(15));
    assertFalse(list.contains(5));
    assertTrue(list.contains(10));
    assertTrue(list.contains(15));
    assertEquals(2, list.size());
  }

  @Test
  public void testAddNull() {
    LinkedList<Integer> list = new LinkedList<>();
    assertTrue(list.add(new LinkedNode<>(null)));
    assertEquals(1, list.size());
    assertNull(list.get(0));
  }

  @Test
  public void testRemoveNonExistentElement() {
    LinkedList<Integer> list = new LinkedList<>();
    assertFalse(list.removeValue(999));
    assertEquals(0, list.size());
  }

  @Test
  public void testAddAndRemoveComplexScenarios() {
    LinkedList<Integer> list = new LinkedList<>();
    list.add(new LinkedNode<>(5));
    list.add(new LinkedNode<>(10));
    list.add(new LinkedNode<>(15));
    list.add(new LinkedNode<>(20));

    list.removeValue(5);
    list.removeValue(20);

    assertTrue(list.contains(10));
    assertTrue(list.contains(15));
    assertEquals(2, list.size());
  }
}
