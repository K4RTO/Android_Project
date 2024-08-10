package com.example.ga_23s2.model.tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class RBTreeTest {

  @Test
  public void testNodeInitialization() {
    RBTreeNode<Integer, Integer> node = new RBTreeNode<>(5, 50);

    assertEquals(Integer.valueOf(5), node.key);
    assertTrue(node.list.contains(50));
    assertTrue(node.red());
  }

  @Test
  public void testNodeChildrenInitialization() {
    RBTreeNode<Integer, Integer> node = new RBTreeNode<>(5, 50);

    assertNull(node.left.key);
    assertNull(node.right.key);
  }

  @Test
  public void testNodeParent() {
    RBTreeNode<Integer, Integer> node = new RBTreeNode<>(5, 50);
    assertNull(node.parent);
  }

  @Test
  public void testRed() {
    RBTreeNode<Integer, Integer> node = new RBTreeNode<>(5, 50);
    assertTrue(node.red());
    node.setRed();
    assertTrue(node.red());
  }

  @Test
  public void testSetBlack() {
    RBTreeNode<Integer, Integer> node = new RBTreeNode<>(5, 50);
    node.setRed();
    assertTrue(node.red());

    node.setBlack();
    assertFalse(node.red());
  }

  @Test
  public void testAddValue() {
    RBTreeNode<Integer, Integer> node = new RBTreeNode<>(5, 50);

    assertTrue(node.addValue(55));
    assertTrue(node.list.contains(55));

    assertFalse(node.addValue(50));
    assertEquals(2, node.list.size());
  }

  @Test
  public void testRemoveValue() {
    RBTreeNode<Integer, Integer> node = new RBTreeNode<>(5, 50);
    node.addValue(55);
    node.addValue(60);

    assertTrue(node.list.contains(55));
    assertTrue(node.list.contains(60));

    assertTrue(node.removeValue(55));
    assertFalse(node.list.contains(55));
    assertEquals(2, node.list.size());

    assertTrue(node.removeValue(60));
    assertFalse(node.list.contains(60));
    assertEquals(1, node.list.size());
  }

  @Test
  public void testRemoveNonExistentValue() {
    RBTreeNode<Integer, Integer> node = new RBTreeNode<>(5, 50);
    assertFalse(node.removeValue(60));
    assertEquals(1, node.list.size());
  }

  @Test
  public void testNodeColoring() {
    RBTreeNode<Integer, Integer> node = new RBTreeNode<>(5, 50);

    node.setBlack();
    assertFalse(node.red());

    node.setRed();
    assertTrue(node.red());
  }

  @Test
  public void testNodeLinkedListBehavior() {
    RBTreeNode<Integer, Integer> node = new RBTreeNode<>(5, 50);

    node.addValue(55);
    node.addValue(60);

    assertEquals(3, node.list.size());

    assertTrue(node.removeValue(55));
    assertEquals(2, node.list.size());

    assertFalse(node.removeValue(100));
  }
}
