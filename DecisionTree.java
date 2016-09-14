import java.util.Scanner;



public class DecisionTree {
	public static void main(String[] args) {
		 //read file to get data
		 readfile obj = new  readfile();
		 obj.data(args[2]);

		//get tree from entropy function
		 ID3 tree = new ID3(obj.element, obj.att, obj.tarAtt);
		 Node root = tree.idd();

		 // read file and get accuracy using Information gain heuristic
		 readfile obj1 = new  readfile();
		 obj1.data(args[4]);
		 Accuracy acc1 = new Accuracy(obj1.element, obj1.att, root);
		 double Max_accuracy = acc1.accuracy();
		 System.out.println("Information gain heuristic,accuracy for test data using tree from train data:  ");
		 System.out.println(Max_accuracy);


		//print tree  using Information gain heuristic
		 if(args[5].equals("YES")){
			 System.out.println("the decision tree from trainning data using Information gain heuristic");
			 root.print(0);
		 }


		 //post prunning
		 int l = Integer.parseInt(args[0]);
		 int k = Integer.parseInt(args[1]);

		 // post pruning tree and get the best tree
		 postPruning prune = new postPruning(l,k);
         Node treebest= prune.pruning(root,args[3]);

         //get accuracy for test data from pruning tree
         readfile obj7 = new  readfile();
		 obj7.data(args[4]);
		 Accuracy acc4 = new Accuracy(obj7.element, obj7.att, treebest);
		 double Max_accuracy4 = acc4.accuracy();
		 System.out.println("Information gain heuristic, get accuracy from train data for test data:  ");
		 System.out.println(Max_accuracy4);


	    //using Variance impurity heuristic:
		 //read file
		 readfile obj2 = new  readfile();
		 obj2.data(args[2]);
		 //Variance impurity heuristic
		 VarianceImpurity tree_impurity = new VarianceImpurity(obj2.element, obj2.att, obj2.tarAtt);
		 Node root_impurity = tree_impurity.vaimpurity();
		 //read file and get accuracy
		 readfile obj3 = new  readfile();
		 obj3.data(args[4]);
		 Accuracy acc2 = new Accuracy(obj3.element, obj3.att, root_impurity);
		 double Max_accuracy1 = acc2.accuracy();
		 System.out.println("Impurity heuristic,accuracy for test data using tree from train data: ");
		 System.out.println(Max_accuracy1);


		 String print_answer2 = args[5];
		//print tree
		 if(print_answer2.equals("YES")){
			 System.out.println("the decision tree for data using Impurity heuristic");
			 root_impurity.print(0);
		 }


		 //post prunning
		 String validate_path1 = args[3];
		 int ll = Integer.parseInt(args[0]);
		 int kk = Integer.parseInt(args[1]);

		 // post pruning tree and get the best tree
		 postPruning prune1 = new postPruning(ll,kk);
         Node treebest1= prune1.pruning(root_impurity,validate_path1);

       //get accuracy for test data from pruning tree
         readfile obj8 = new  readfile();
		 obj8.data(args[4]);
		 Accuracy acc5 = new Accuracy(obj8.element, obj8.att, treebest1);
		 double Max_accuracy5 = acc5.accuracy();
		 System.out.println("Impurity heuristic, get accuracy from train data for test data:  ");
		 System.out.println(Max_accuracy5);

   }
	}
