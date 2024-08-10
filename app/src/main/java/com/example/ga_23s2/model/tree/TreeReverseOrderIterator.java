package com.example.ga_23s2.model.tree;

import java.util.Iterator;
import java.util.Stack;

public class TreeReverseOrderIterator<K extends Comparable<K>, V>
    implements Iterator<RBTreeNode<K, V>> {

  Stack<RBTreeNode<K, V>> visited;

  public TreeReverseOrderIterator(RBTreeNode<K, V> root) {
    visited = new Stack<>();
    getRight(root);
  }

  private void getRight(RBTreeNode<K, V> current) {
    while (current != null && current.isNotNIL()) {
      visited.push(current);
      current = current.right;
    }
  }

  @Override
  public boolean hasNext() {
    return !visited.empty();
  }

  @Override
  public RBTreeNode<K, V> next() {
    if (hasNext()) {
      RBTreeNode<K, V> current = visited.pop();
      getRight(current.left);
      return current;
    }
    return null;
  }
}
