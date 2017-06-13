import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class main {
public static void main(String[] args) throws Exception {
		File dir = new File("C:/Users/jwchu/Desktop/input.txt");
		if(dir.isFile()) System.out.println("file name= "+ dir.getName());
		
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/jwchu/Desktop/input.txt"));
		RBtree rbtree = new RBtree();
		while(true){
			String line = br.readLine();
			Integer input = Integer.valueOf(line);
			if(input>0){
				rbtree.insert(rbtree.root, new RBnode(input));
			}else if(input<0){
				rbtree.delete(rbtree.root, Math.abs(input));
			}else
				break;
		}
		System.out.println("total= "+rbtree.nodeNum);
		System.out.println("insert= "+rbtree.insertNum);
		System.out.println("delete= "+rbtree.deleteNum);
		System.out.println("miss= "+rbtree.miss);
		System.out.println("nb= "+rbtree.blackNum(rbtree.root));
		System.out.println("bh= "+rbtree.getBlackHeight(rbtree.root));
		rbtree.inorder(rbtree.root);
		br.close();
	
	}
}
