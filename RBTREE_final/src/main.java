import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class main {
public static void main(String[] args) throws Exception{

		ArrayList<Integer>list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/jwchu/Desktop/input.txt"));
		BufferedReader srch = new BufferedReader(new FileReader("C:/Users/jwchu/Desktop/search.txt"));
		BufferedWriter output= new BufferedWriter(new FileWriter("C:/Users/jwchu/Desktop/output.txt"));
		RBtree rbtree = new RBtree();
		while(true){
			String line = br.readLine();
			Integer input = Integer.valueOf(line);
			list.add(input);
			if(input>0){
				rbtree.insert(rbtree.root, new RBnode(input));
			}else if(input<0){
				rbtree.delete(rbtree.root, Math.abs(input));
			}else
				break;
		}
		//rbtree.print(rbtree.root, 0);
		while(true){
			String find = srch.readLine();
			Integer findVal = Integer.valueOf(find);
			if(findVal==0){
				break;
			}else{
			int result[]=rbtree.findAdjac(list, findVal);
			for(int i=0;i<result.length;i++){
				if(result[i]==0){
					output.write("NIL ");
				}else 
					output.write(result[i]+" ");
			}
			output.write("\n");
			}
		}
		br.close();
		srch.close();
		output.close();
	}
}
