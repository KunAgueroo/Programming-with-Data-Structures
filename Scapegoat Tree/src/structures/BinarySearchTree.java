//Harshavardhan Reddipalli
//CS 187 Project 6

package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  protected BSTNode<T> root;

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return subtreeSize(root);
  }

  protected int subtreeSize(BSTNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
    }
  }

  public boolean contains(T t) {
    // TODO: Implement the contains() method
    if (t == null) {
      throw new NullPointerException("elemnt is null");
    }
    return get(t) != null;
  }

  public boolean remove(T t) {
    if (t == null) {
      throw new NullPointerException("element is null");
    }
    boolean result = contains(t);
    if (result == true) {
      root = removeFromSubtree(root, t);
    }
    return result;
  }

  protected BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {

    int result = t.compareTo(node.getData());
    if (result < 0) {
      node.setLeft(removeFromSubtree(node.getLeft(), t));
      return node;
    } else if (result > 0) {
      node.setRight(removeFromSubtree(node.getRight(), t));
      return node;
    } else {
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else {
        T predecessorValue = getHighestValue(node.getLeft());
        node.setLeft(removeRightmost(node.getLeft()));
        node.setData(predecessorValue);
        return node;
      }
    }
  }

  private T getHighestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getData();
    } else {
      return getHighestValue(node.getRight());
    }
  }

  private BSTNode<T> removeRightmost(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getLeft();
    } else {
      node.setRight(removeRightmost(node.getRight()));
      return node;
    }
  }

  public T get(T t) {
    // TODO: Implement the get() method
    if (t == null) {
      throw new NullPointerException("element is null");
    }
    T newelem = null;
    BSTNode<T> current;
    current = root;
    int i;
    while (current != null) {
      i = t.compareTo(current.getData());
      if (i == 0) {
        newelem = current.getData();
        return newelem;
      } else {
        if (i > 0)
          current = current.getRight();
        else {
          if (i < 0)
            current = current.getLeft();
        }
      }
    }
    return newelem;
  }

  public void add(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    root = addToSubtree(root, new BSTNode<T>(t, null, null));
  }

  protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
    if (node == null) {
      return toAdd;
    }
    int result = toAdd.getData().compareTo(node.getData());
    if (result <= 0) {
      node.setLeft(addToSubtree(node.getLeft(), toAdd));
    } else {
      node.setRight(addToSubtree(node.getRight(), toAdd));
    }
    return node;
  }

  @Override
  public T getMinimum() {
    // TODO: Implement the getMinimum() method
    if (root == null)
      return null;
    return getLowestValue(root);
  }

  private T getLowestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getLeft() == null) {
      return node.getData();
    } else {
      return getLowestValue(node.getLeft());
    }
  }
  /*
   * private T getHighestValue(BSTNode<T> node) { // node must not be null if
   * (node.getRight() == null) { return node.getData(); } else { return
   * getHighestValue(node.getRight()); }
   */

  @Override
  public T getMaximum() {
    // TODO: Implement the getMaximum() method
    if (root == null)
      return null;
    return getHighestValue(root);

  }

  @Override
  public int height() {
    // TODO: Implement the height() method
    return height(root);
  }

  private int height(BSTNode<T> tree) {
    if (tree == null) {
      return -1;
    } else {
      return (1 + Math.max(height(tree.getLeft()), height(tree.getRight())));
    }
  }

  public Iterator<T> preorderIterator() {
    // TODO: Implement the preorderIterator() method
    Queue<T> queue = new LinkedList<T>();
    preorderTraverse(queue, root);
    return queue.iterator();
  }

  private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      queue.add(node.getData());
      preorderTraverse(queue, node.getLeft());
      preorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> inorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, root);
    return queue.iterator();
  }

  private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      inorderTraverse(queue, node.getLeft());
      queue.add(node.getData());
      inorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> postorderIterator() {
    // TODO: Implement the postorderIterator() method //To Remember:almost same code
    // as inorderIterator
    Queue<T> queue = new LinkedList<T>();
    postorderTraverse(queue, root);
    return queue.iterator();
  }

  private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      postorderTraverse(queue, node.getLeft());
      postorderTraverse(queue, node.getRight());
      queue.add(node.getData());
    }
  }

  @Override
  public boolean equals(BSTInterface<T> other) {
    // TODO: Implement the equals() method
    if (other == null) {
      throw new NullPointerException("other is null");
    } else {
      BSTNode<T> root2 = other.getRoot();
      return equals(root, root2);
    }
  }

  private boolean equals(BSTNode<T> node1, BSTNode<T> node2) {
    if (node1 == null && node2 == null) {
      return true;
    } else {
      if ((node1 != null && node2 == null) || (node1 == null && node2 != null))
        return false;
      else {
        if (!node1.getData().equals(node2.getData()))
          return false;
        return equals(node1.getLeft(), node2.getLeft()) && equals(node1.getRight(), node2.getRight());
      }
    }
  }

  @Override
  public boolean sameValues(BSTInterface<T> other) {
    // TODO: Implement the sameValues() method
    if (other == null) {
      throw new NullPointerException("other is null");
    }
    Iterator<T> Iterator1 = this.inorderIterator();
    Iterator<T> Iterator2 = other.inorderIterator();

    while (Iterator1.hasNext()) {
      if (!Iterator1.next().equals(Iterator2.next())) {
        return false;
      }
    }
    if (Iterator1.hasNext() || Iterator2.hasNext()) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public boolean isBalanced() {
    // TODO: Implement the isBalanced() method
    if (isEmpty())
      return true;
    else {
      boolean balanced = false;
      double x = Math.pow(2, height());
      double y = Math.pow(2, height() + 1);
      if (size() >= x && size() < y)
        balanced = true;
      else
        balanced = false;
      return balanced;
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public void balance() {
    // TODO: Implement the balanceHelper() method
    Iterator<T> iter = this.inorderIterator();
    T[] array1 = (T[]) new Comparable[size()];
    int i = 0;
    while (iter.hasNext()) {
      array1[i] = iter.next();
      i++;
    }
    root = balanceHelper(array1, 0, array1.length - 1);
  }

  private BSTNode<T> balanceHelper(T[] array2, int low, int up) {
    if (low > up)
      return null;
    int middle = (low + up) / 2;
    BSTNode<T> node = new BSTNode<T>(array2[middle], balanceHelper(array2, low, middle - 1), balanceHelper(array2, middle + 1, up));
    return node;
  }

  @Override
  public BSTNode<T> getRoot() {
    // DO NOT MODIFY
    return root;
  }

  public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
    // header
    int count = 0;
    String dot = "digraph G { \n";
    dot += "graph [ordering=\"out\"]; \n";
    // iterative traversal
    Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
    queue.add(root);
    BSTNode<T> cursor;
    while (!queue.isEmpty()) {
      cursor = queue.remove();
      if (cursor.getLeft() != null) {
        // add edge from cursor to left child
        dot += cursor.getData().toString() + " -> " + cursor.getLeft().getData().toString() + ";\n";
        queue.add(cursor.getLeft());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
      if (cursor.getRight() != null) {
        // add edge from cursor to right child
        dot += cursor.getData().toString() + " -> " + cursor.getRight().getData().toString() + ";\n";
        queue.add(cursor.getRight());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
    }
    dot += "};";
    return dot;
  }

  public static void main(String[] args) {
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      BSTInterface<String> tree = new BinarySearchTree<String>();
      for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
        tree.add(s);
      }
      Iterator<String> iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.preorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.postorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();

      System.out.println(tree.remove(r));

      iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
    }

    BSTInterface<String> tree = new BinarySearchTree<String>();
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      tree.add(r);
    }
    System.out.println(toDotFormat(tree.getRoot()));
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    tree.balance();
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
  }
}
