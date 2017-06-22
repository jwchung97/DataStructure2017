
public class RBnode {
	int val;
	RBnode left,right,parent;
	String color;
	
	public RBnode(int newval) {
		this.val= newval;
		this.color="black";
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public RBnode getLeft() {
		return left;
	}

	public void setLeft(RBnode left) {
		this.left = left;
	}

	public RBnode getRight() {
		return right;
	}

	public void setRight(RBnode right) {
		this.right = right;
	}

	public RBnode getParent() {
		return parent;
	}

	public void setParent(RBnode parent) {
		this.parent = parent;
	}
	
}
