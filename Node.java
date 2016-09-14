import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Node {
	// define the structure of the tree
	String name;
	String maxclass;
	// 0
	Node  leftchildren;
	// 1
	Node  rightchildren;
	Node(String n) {
		name = n;
	}
	Node(String n,Node left, Node right){
		name = n;
		leftchildren = left;
		rightchildren = right;
	}

	public String toString() {
		return name;
	}
	//copy the tree to a new tree
	public Node copy(){
		Node left = null;
		Node right = null;
		if(this.leftchildren != null){
			left = this.leftchildren.copy();
		}
		if(this.rightchildren != null){
			right = this.rightchildren.copy();
		}
		Node newNode = new Node(name,left, right);
		newNode.maxclass = maxclass;
		return  newNode;
	}
	// get non-left nodes in a tree
	public int nonleft(){
		if (this.leftchildren == null && this.rightchildren == null) return 0;
		else {
			if (this.leftchildren != null && this.rightchildren == null)
				return 1 + this.rightchildren.nonleft();
			if (this.leftchildren == null && this.rightchildren != null)
				return 1 + this.leftchildren.nonleft();
			if (this.leftchildren != null && this.rightchildren != null)
				return 1 + this.leftchildren.nonleft() + this.rightchildren.nonleft();
		}
		return 0;
	}
	// get the order of the node in the tree, breadth-first
	public List<List<Node>> order(Node root){
		List<List<Node>> column = new ArrayList<List<Node>>();
		//define the queue
		Queue<Node> q = new LinkedList<Node>();
		if(root == null) return column;
		q.add(root);
		while(q.size() != 0){
			List<Node> row = new ArrayList<Node>();
			int l = q.size();
			for(;l>0;l--){
				Node current =q.poll();
				row.add(current);
				if(current.leftchildren != null){
					if(current.leftchildren.leftchildren== null && current.leftchildren.rightchildren == null){}
					else q.add(current.leftchildren);}
				if(current.rightchildren != null){
					if(current.rightchildren.leftchildren == null && current.rightchildren.rightchildren == null){}
					else q.add(current.rightchildren);}
			}
			column.add(row);
		}
		return column;

	}
	// print a tree
	public void print(int level) {
		if(leftchildren != null) {
			for (int l = 0; l < level; l++) { System.out.print("|  "); }
			System.out.print(name);
			System.out.print(" = 0 : ");
			if(leftchildren.name.equals("0")){System.out.print("0"+'\n');}
			else if(leftchildren.name.equals("1")){System.out.print("1"+'\n');}
			else{System.out.print('\n');}
			leftchildren.print(level + 1);
		}

		if(rightchildren != null) {
			for (int l = 0; l < level; l++) { System.out.print("|  "); }
			System.out.print(name);
			System.out.print(" = 1 : ");
			if(rightchildren.name.equals("0")){System.out.print("0"+'\n');}
			else if(rightchildren.name.equals("1")){System.out.print("1"+'\n');}
			else{System.out.print('\n');}
			rightchildren.print(level + 1);
		}
	}
}
