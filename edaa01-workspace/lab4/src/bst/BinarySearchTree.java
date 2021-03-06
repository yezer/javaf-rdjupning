package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	int size;

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
	}

	public static void main(String[] args) {
		// First tree
		BinarySearchTree<Integer> myTree = new BinarySearchTree<Integer>();
		myTree.add(8);
		myTree.add(8);
		myTree.add(6);
		myTree.add(2);
		myTree.add(15);
		System.out.println("The height of my tree is " + myTree.height());
		System.out.println("The size of my tree is " + myTree.size());
		myTree.printTree();
		BSTVisualizer visualizer = new BSTVisualizer("My First Tree", 300, 400);
		visualizer.drawTree(myTree);

		// Second tree
		BinarySearchTree<Integer> mySecondTree = new BinarySearchTree<Integer>();
		mySecondTree.add(6);
		mySecondTree.add(8);
		mySecondTree.add(2);
		mySecondTree.add(3);
		mySecondTree.add(690);
		mySecondTree.add(4);
		mySecondTree.add(98);
		mySecondTree.add(66);
		mySecondTree.add(500);
		mySecondTree.add(709);
		mySecondTree.add(1007);
		mySecondTree.add(5279);
		mySecondTree.add(53279052);
		mySecondTree.add(532);
		mySecondTree.add(5392);
		mySecondTree.add(4322);
		mySecondTree.add(9432);
		mySecondTree.add(577);
		System.out.println("The height of my second tree is "
				+ mySecondTree.height());
		System.out.println("The size of my second tree is "
				+ mySecondTree.size());
		mySecondTree.printTree();
		BSTVisualizer visualizer2 = new BSTVisualizer("My Second Tree", 300,
				400);
		visualizer2.drawTree(mySecondTree);

		// Third tree
		BinarySearchTree<Integer> myThirdTree = new BinarySearchTree<Integer>();
		myThirdTree.add(78);
		myThirdTree.add(5);
		myThirdTree.add(9);
		myThirdTree.add(42);
		myThirdTree.add(94);
		myThirdTree.add(34);
		myThirdTree.add(7);
		myThirdTree.add(100);
		myThirdTree.add(83);
		myThirdTree.add(99);
		myThirdTree.add(86);
		myThirdTree.add(42);
		System.out.println("The height of my third tree is "
				+ myThirdTree.height());
		System.out
				.println("The size of my third tree is " + myThirdTree.size());
		myThirdTree.printTree();
		BSTVisualizer visualizer3 = new BSTVisualizer("My Third Tree", 300, 400);
		visualizer3.drawTree(myThirdTree);

		// Rebuild
		mySecondTree.rebuild();
		BSTVisualizer visualizer4 = new BSTVisualizer("My Rebuilt Tree", 300,
				400);
		visualizer4.drawTree(mySecondTree);
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} else {
			return add(root, x);
		}
	}

	private boolean add(BinaryNode<E> n, E x) {
		int compResult = x.compareTo(n.element);
		if (compResult == 0) {
			return false;
		}
		if (compResult < 0) {
			if (n.left == null) {
				n.left = new BinaryNode<E>(x);
				size=size+1;
				return true;
			} else {
				return add(n.left, x);
			}
		} else {
			if (n.right == null) {
				n.right = new BinaryNode<E>(x);
				size=size+1;
				return true;
			} else {
				return add(n.right, x);
			}
		}
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return helpHeight(root);
	}

	private int helpHeight(BinaryNode<E> node) {
		int height;
		if (node == null) {
			return 0;
		}
		BinaryNode<E> left = node.left;
		BinaryNode<E> right = node.right;
		int leftTreeHeight = helpHeight(left); // går sedan igenom koden nedan
		// för värdet på leftTreeHeight
		// efter varje return call
		int rightTreeHeight = helpHeight(right);
	/*	if (leftTreeHeight <= rightTreeHeight) {
			height = 1 + rightTreeHeight;
		} else {
			height = 1 + leftTreeHeight;
		}*/
		return Math.max(leftTreeHeight+1, rightTreeHeight+1);
//		return height;
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		if (root != null) {
			if (root.left != null) {
				root.left.print();
			}
			System.out.println(root.element);
			if (root.right != null) {
				root.right.print();
			}
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] myArray = (E[]) new Comparable[size];
		System.out.println("These are my values for the ascending array");
		int index = ToArray(root, myArray, 0);
		root = buildTree(myArray, 0, index - 1);
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder (ascending order)
	 * to the array a starting at a[index]. Returns the index of the last
	 * inserted element + 1 (the first empty position in a).
	 */

	private int ToArray(BinaryNode<E> n, E[] a, int index) {
		if (n.left != null) {
			index = ToArray(n.left, a, index);
		}
		if (n != null) {
			a[index] = n.element;
			index++;
		}
		if (n.right != null) {
			index = ToArray(n.right, a, index);
		}
		return index;
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in
	 * the array a are assumed to be in ascending order. Returns the root of
	 * tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		int middle = Math.round((first + last) / 2);
		BinaryNode<E> newNode = new BinaryNode(a[middle]);
		if (first != middle) {
			newNode.left = buildTree(a, first, middle - 1);
		}
		if (last != middle) {
			newNode.right = buildTree(a, middle + 1, last);
		}
		return newNode;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}

		private void print() {
			if (left != null) {
				left.print();
			}
			System.out.println(element);
			if (right != null) {
				right.print();
			}
		}
	}
}