package com.example.ga_23s2.model.tree;

import java.util.Iterator;
import java.util.Stack;

public class TreeInOrderIterator<K extends Comparable<K>, V> implements Iterator<RBTreeNode<K, V>> {

  Stack<RBTreeNode<K, V>> visited;

  public TreeInOrderIterator(RBTreeNode<K, V> root) {
    visited = new Stack<>();
    getLeft(root);
  }

  private void getLeft(RBTreeNode<K, V> current) {
    while (current != null && current.isNotNIL()) {
      visited.push(current);
      current = current.left;
    }
  }

  @Override
  public boolean hasNext() {
    return !visited.empty();
  }

  /** p l r NIL NIL */
  @Override
  public RBTreeNode<K, V> next() {
    if (hasNext()) {
      RBTreeNode<K, V> current = visited.pop();
      getLeft(current.right); // stack next values on right if any;
      return current; // return l;
      // stack will then pop p
    }
    return null;
  }
}
