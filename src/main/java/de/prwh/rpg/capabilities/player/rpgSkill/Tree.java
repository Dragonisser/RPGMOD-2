package de.prwh.rpg.capabilities.player.rpgSkill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tree<T> implements Serializable {
	public static class Node<T> implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private T data;
		private Node<T> parent;
		private List<Node<T>> children = new ArrayList<Node<T>>();
		
		public Node(T data) {
			this.data = data;
		}

		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public Node<T> getParent() {
			return parent;
		}
		public void setParent(Node<T> parent) {
			this.parent = parent;
		}
		public List<Node<T>> getChildren() {
			return children;
		}
	}
	
	private static final long serialVersionUID = 1L;
	
	private Node<T> root;
	
	public Tree(T root) {
		this.root = new Node<T>(root);
	}
	
	public Node<T> getRoot() {
		return root;
	}
}
