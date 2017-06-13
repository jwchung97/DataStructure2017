
public class RBtree {
	RBnode root;
	private static RBnode nullnode;
	public static int nodeNum;
	public static int insertNum;
	public static int deleteNum;
	public static int miss;
	public RBtree() {
		nullnode = new RBnode(0);
        nullnode.setLeft(nullnode);
        nullnode.setRight(nullnode);
        root = nullnode;
	}
	
	public void insert(RBnode tree, RBnode z){
		RBnode y = nullnode;
		RBnode x = tree;
		//first find the node to which z will be inserted
		while(x!=nullnode){
			y= x;
			if(z.val<x.val){
				x=x.left;
			}else if(z.val>x.val){
				x=x.right;
			}
		} //eventually node z will be inserted to node y
		z.setParent(y);
		if(y==nullnode){
			this.root= z;
		}else if(z.val<y.val){
			y.setLeft(z);
		}else{
			y.setRight(z);
		}
		z.setLeft(nullnode);
		z.setRight(nullnode);
		z.setColor("red");
		insert_fixup(root,z);
		nodeNum++;
		insertNum++;
	}
	public void insert_fixup(RBnode tree, RBnode z) {
		RBnode y = new RBnode(0);
		while(z.parent.color.equals("red")){
			if(z.parent==z.parent.parent.left){ 
				y = z.parent.parent.getRight(); //find the uncle node and denote it as y, right child of grandparent
				if(y.getColor().equals("red")){//case1: when both parent and uncle is red
					z.parent.setColor("black");
					y.setColor("black");
					z = z.parent.parent;
				}else if(y.getColor().equals("black")){
					if(z==z.parent.right){ //case2: uncle black, new node is a right child
						leftRotate(z.parent); 
					}else if(z==z.parent.left){ //case3: uncle black, new node is a left child
						z.parent.setColor("black");
						z.parent.parent.setColor("red");
						rightRotate(z.parent.parent);
					}
				}
			}else if(z.parent==z.parent.parent.right){
				y=z.parent.parent.left; //find the uncle node and denote it as y, left child of grandparent
				if(y.getColor().equals("red")){//case1: when both parent and uncle is red
					z.parent.setColor("black");
					y.setColor("black");
					z = z.parent.parent;
				}else if(y.getColor().equals("black")){
					if(z==z.parent.left){ //case2
						rightRotate(z.parent); 
					}else if(z==z.parent.right){ //case3
						z.parent.setColor("black");
						z.parent.parent.setColor("red");
						leftRotate(z.parent.parent);
					}
				}
			}
		}
		root.setColor("black");
	}
	public void rightRotate(RBnode y){
		RBnode x = y.getLeft();
		y.setLeft(x.right);
		if(x.right!=nullnode){
			x.right.setParent(x);
		}
		x.setParent(x.parent);
		if(y.parent==nullnode){
			root=x;
		}else if(y==y.parent.right){
			y.parent.setRight(x);
		}else
			y.parent.left=x;
		x.setRight(y);
		y.setParent(x);
	}
	public void leftRotate(RBnode x){
		RBnode y = x.getRight();
		x.setRight(y.getLeft());
		if(y.left!=nullnode){
			y.left.setParent(x);
		}
		y.setParent(x.parent);
		if(x.parent==nullnode){
			root=y;
		}else if(x==x.parent.left){
			x.parent.setLeft(y);
		}else
			x.parent.right=y;
		y.setLeft(x);
		x.setParent(y);
	}
	public void delete(RBnode tree, int val){
		RBnode z= search(tree,val);
		if(z==nullnode)
			miss++;
		RBnode x = nullnode;
		RBnode y = z; 
		String originalCol = y.color;
		if(z.left==nullnode){// case1: the node has only one right child
			x= z.getRight();
			transplant(z,z.right);
		}else if(z.right==nullnode){//case2: the node has only one left child
			x= z.getLeft();
			transplant(z,z.left);
		}else{ //case3: the node has two child, thus find the successor and transplant it
			y = findSuccessor(z.right); // node y denotes the successor which will move into z's position
			originalCol= y.color; //when transplanting y to z, the color might change
			x = y.right;
			if(y.parent==z){
				x.parent=y;
			}else{
				transplant(y, y.right);
				y.right =z.right;
				y.right.parent =y;
			}
			transplant(z, y);
			y.left=z.left;
			y.left.parent=y;
			y.color =z.color;
		}
		if(originalCol.equals("black"))
			delete_fixup(tree,x);
		nodeNum--;
		deleteNum++;
	}
	public void delete_fixup(RBnode tree, RBnode x) {
		RBnode w= new RBnode(0);
		while(x!=root&&x.color.equals("black")){
			if(x==x.parent.left){
				w = x.parent.right;
				if(w.color.equals("red")){
					w.setColor("black");
					x.parent.setColor("red");
					leftRotate(x.parent);
					w=x.parent.right;
				}
				if(w.left.color.equals("black") && w.right.color.equals("black")){
					w.setColor("red");
					x= x.parent;
				}else {
					if(w.right.color.equals("black")){
						w.left.setColor("black");
						w.setColor("red");
						rightRotate(w);
						w=x.parent.right;
					}
					w.setColor(x.parent.color);
					x.parent.setColor("black");
					w.right.setColor("black");
					leftRotate(x.parent);
					x=root;
				}
			}
			else{
				w = x.parent.left;
				if(w.color.equals("red")){
					w.setColor("black");
					x.parent.setColor("red");
					rightRotate(x.parent);
					w=x.parent.left;
				}
				if(w.right.color.equals("black") && w.left.color.equals("black")){
					w.setColor("red");
					x= x.parent;
				}else {
					if(w.left.color.equals("black")){
						w.right.setColor("black");
						w.setColor("red");
						leftRotate(w);
						w=x.parent.left;
					}
					w.setColor(x.parent.color);
					x.parent.setColor("black");
					w.left.setColor("black");
					rightRotate(x.parent);
					x=root;
				}
			}
		}
		x.setColor("black");
	}

	public RBnode search(RBnode tree, int val){
        if (tree == null){
            System.out.println("no such node");
        	return nullnode;
        }
        if (val == tree.val)
            return tree;
        else if (val < tree.val)
            return search(tree.left,val);
        else
            return search(tree.right,val);
    }

	public RBnode findSuccessor(RBnode successor) {
	        if (successor.left == nullnode)
	            return successor;
	        else {
	            return findSuccessor(successor.left);
	        }
	    }
	private void transplant(RBnode u, RBnode v) {
		if(u.parent==nullnode){
			root= v;
		}else if(u==u.parent.getLeft()){
			u.parent.setLeft(v);
		}else
			u.parent.setRight(v);
		if(v!=nullnode)
			v.parent=u.parent;
	}

	public void print(RBnode treeNode, int level) { 
		if (root == nullnode) {
            System.out.println("no node");
        } else {
            if (treeNode.right != nullnode)
                print(treeNode.right, level + 1);
            for (int i = 0; i < level; i++)
                System.out.print("    ");
            System.out.println(treeNode.val);
            if (treeNode.left != nullnode)
                print(treeNode.left, level + 1);
        }
	}

	public void inorder(RBnode treeNode) {
		if (treeNode == this.nullnode)
            return;
        else {
            inorder(treeNode.left);
            System.out.print(treeNode.val+" ");
            if(treeNode.color.equals("red")) System.out.println("R");
            else System.out.println("B");
            inorder(treeNode.right);
        }
	}
	public int blackNum(RBnode tree){
		int count=0;
		if(tree==nullnode)
			return count;
		count+= blackNum(tree.left);
		count+= blackNum(tree.right);
		if(tree.color.equals("black")){
			count++;
		}
		return count;
	}
	public int getBlackHeight(RBnode tree){
		int height=0;
		RBnode x=tree;
		while(x!=nullnode){
			if(x.color.equals("black")){
				height++;
			}
			x=x.left;
		}
		return height;
	}
}