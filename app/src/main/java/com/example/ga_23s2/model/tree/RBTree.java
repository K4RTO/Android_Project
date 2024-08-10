package com.example.ga_23s2.model.tree;

import java.util.ArrayList;
import java.util.List;

public class RBTree<K extends Comparable<K>, V> {
  RBTreeNode<K, V> root;

  //  int size = 0;
  public RBTree() {
    this.root = null;
  }

  public int count = 0;

  public boolean insert(K key, V value) {
    RBTreeNode<K, V> prev = null;
    RBTreeNode<K, V> current = root;
    int comp = 0;
    while (current != null && current.isNotNIL()) {
      prev = current;
      comp = key.compareTo(current.key);
      if (comp == 0) return false;
      else if (comp < 0) current = current.left;
      else current = current.right;
    }
    RBTreeNode<K, V> node = new RBTreeNode<>(key, value);
    node.parent = prev;
    if (prev == null) root = node;
    else if (comp < 0) prev.left = node;
    else prev.right = node;
    insertFix(node);
    count += 1;
    return true;
  }

  public void insertFix(RBTreeNode<K, V> z) {
    while (z.parent != null && z.parent.red()) {
      if (z.parent == z.parent.parent.left) {
        RBTreeNode<K, V> uncle = z.parent.parent.right;
        if (uncle != null && uncle.isNotNIL() && uncle.red()) {
          z.parent.setBlack();
          uncle.setBlack();
          z.parent.parent.setRed();
          z = z.parent.parent;
        } else {
          if (z == z.parent.right) {
            z = z.parent;
            leftRotate(z);
          }
          z.parent.setBlack();
          z.parent.parent.setRed();
          rightRotate(z.parent.parent);
        }
      } else {
        RBTreeNode<K, V> uncle = z.parent.parent.left;
        if (uncle != null && uncle.isNotNIL() && uncle.red()) {
          z.parent.setBlack();
          uncle.setBlack();
          z.parent.parent.setRed();
          z = z.parent.parent;
        } else {
          if (z.parent.left != null && z.key.equals(z.parent.left.key)) {
            z = z.parent;
            rightRotate(z);
          }
          z.parent.setBlack();
          z.parent.parent.setRed();
          leftRotate(z.parent.parent);
        }
      }
    }
    root.setBlack();
  }

  private void rightRotate(RBTreeNode<K, V> node) {
    if (node.left == null || node.left.key == null) return;
    RBTreeNode<K, V> leftNode = node.left;
    node.left = leftNode.right;
    if (leftNode.right != null) leftNode.right.parent = node;
    // update parent
    leftNode.parent = node.parent;
    if (node.parent == null) root = node.left;
    else if (node == node.parent.right) node.parent.right = leftNode;
    else node.parent.left = leftNode;

    node.parent = leftNode;
    leftNode.right = node;
  }

  /** node right right node rightLeft rightLeft */
  private void leftRotate(RBTreeNode<K, V> node) {
    if (node.right == null || node.right.key == null) return;
    RBTreeNode<K, V> rightNode = node.right;
    node.right = rightNode.left;
    if (rightNode.left != null) rightNode.left.parent = node;
    // update parent
    rightNode.parent = node.parent;
    if (node.parent == null) root = rightNode;
    else if (node == node.parent.right) node.parent.right = rightNode;
    else node.parent.left = rightNode;

    node.parent = rightNode;
    rightNode.left = node;
  }

  public V find(K key) {
    RBTreeNode<K, V> current = root;
    while (current != null) {
      int comp = current.key.compareTo(key);
      if (comp == 0) return current.value;
      else if (comp < 0) current = current.left;
      else current = current.right;
    }
    return null;
  }

  RBTreeNode<K, V> getRoot() {
    return root;
  }

  public int size() {
    return size(root);
  }

  private int size(RBTreeNode<K, V> node) {
    if (node == null) {
      return 0;
    }
    return 1 + size(node.left) + size(node.right);
  }

  public List<V> getList() {
    List<V> result = new ArrayList<>();
    TreeInOrderIterator<K, V> iter = new TreeInOrderIterator<>(root);
    while (iter.hasNext()) {
      result.add(iter.next().value);
    }
    return result;
  }

  public List<V> getReverseList() {
    List<V> result = new ArrayList<>();
    TreeReverseOrderIterator<K, V> iter = new TreeReverseOrderIterator<>(root);
    while (iter.hasNext()) {
      result.add(iter.next().value);
    }
    return result;
  }
}
