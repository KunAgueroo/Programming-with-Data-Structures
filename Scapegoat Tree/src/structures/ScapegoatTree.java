package structures;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
	private int upperBound;

	@Override
	public void add(T t) {
		// TODO: Implement the add() method
		if (t == null) {
			throw new NullPointerException("t is null");
		}
		upperBound++;
		BSTNode<T> newNode = new BSTNode<T>(t, null, null);
		root = addToSubtree(root, newNode);
		if (height() > Math.log(upperBound) / Math.log(3.0 / 2.0)) {
			BSTNode<T> child = newNode;
			BSTNode<T> par = newNode.parent;
			while ((double) subtreeSize(child) / subtreeSize(par) <= 2.0 / 3.0) {
				par = par.parent;
				child = child.parent;
			}
			ScapegoatTree<T> st = new ScapegoatTree<T>();
			st.root = par;
			BSTNode<T> orgParent = par.parent;
			st.balance();
			if (orgParent.getLeft() == par)
				orgParent.setLeft(st.root);
			else
				orgParent.setRight(st.root);
		}

	}

	@Override
	public boolean remove(T element) {
		// TODO: Implement the remove() method
		if (element == null) {
			throw new NullPointerException("element is null");
		}
		boolean result = contains(element);
		if (result == true) {
			root = removeFromSubtree(root, element);
		}
		if (upperBound > 2 * this.size()) {
			this.balance();
			upperBound = this.size();
		}
		return result;
	}

	// public static void main(String[] args) {
	// BSTInterface<String> tree = new ScapegoatTree<String>();
	/*
	 * You can test your Scapegoat tree here. for (String r : new String[] {"0",
	 * "1", "2", "3", "4"}) { tree.add(r);
	 * System.out.println(toDotFormat(tree.getRoot())); }
	 */

}
