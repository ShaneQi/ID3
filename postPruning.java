import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class postPruning {
	int l;
	int k;
	postPruning(int ll, int kk){
		l=ll;
		k=kk;
	}
   public Node pruning(Node root,String path){
	   Node treebest = root;
	   //evaluate the accuracy of D on the validation set;
	   readfile obj1 = new  readfile();
	   obj1.data(path);
	   Accuracy acc1 = new Accuracy(obj1.element, obj1.att, treebest);
	   double Max_accuracy = acc1.accuracy();
	   //System.out.println("before the post pruning, the accuracy for validate data: "+ Max_accuracy);
	   for(int i = 0; i < l; i++){
		   // copy the tree to a new tree
	       Node newtree = root.copy();
	       // m is a random number between 1 to k
	       Random r = new Random();
	       int m = r.nextInt(k)+1;
	       //System.out.print(" m:"+m);
	       // get the number of non left in tree
	       int n = newtree.nonleft();
	       	for(int j = 0; j < m; j++){
	       		//order the non-left in new tree;
	       		List<List<Node>> tree_order = new ArrayList<List<Node>>();
	       	    tree_order = newtree.order(newtree);
	       		//p is a random number between 1 to N
	       		 int p = r.nextInt(n)+1;
	       		 //System.out.print(" p: "+p);
	       		 //replace the subtree rooted at P in D' by a leaf node
	       		 int len = tree_order.size();
	       		 int count = 0;
	       		 for(int ii = 0; ii <len; ii++){
	       			 int alen = tree_order.get(ii).size();
	       			 for(int jj = 0; jj< alen;jj++){
	       				 count++;
	       				 if(count == p){
	       					 Node tmp = tree_order.get(ii).get(jj);
	       					 tree_order.get(ii).get(jj).leftchildren = null;
      						 tree_order.get(ii).get(jj).rightchildren = null;
      						 String temp =tree_order.get(ii).get(jj).maxclass;
	       					 if (temp.equals("0")){
	       						 tree_order.get(ii).get(jj).name ="0";
	       					 }else{
	       						tree_order.get(ii).get(jj).name = "1";
	       					 }
	       				}
	       			 }
	       		  }
	       	}
	       		  //evaluate the accuracy of D' on the validation set;
	       		  readfile obj2 = new  readfile();
	    		  obj2.data(path);
	    		  Accuracy acc2 = new Accuracy(obj2.element, obj2.att, newtree);
	    		  double b = acc2.accuracy();
	    		  if(b>Max_accuracy){treebest = newtree; Max_accuracy=b;}
	  }
	   //System.out.println("after the post pruning, the accuracy for validate data:"+ Max_accuracy);
	   return treebest;
   }
}
