import java.util.ArrayList;
import java.util.List;

public class VarianceImpurity {
	List<List<String>> element;
	ArrayList<String> att;
	String tarAtt;
	VarianceImpurity(List<List<String>> e,ArrayList<String> a,String t){
		tarAtt = t;
		att = a;
		element =e;
	}
	public Node vaimpurity(){
		//get length of target class element
		int elelen = element.size();
		// get the length of all attributes
		int attlen = att.size();
		//create a root node for the tree
		Node root = null;
		//get the all values of target attribute
		int plus = 0;
		int minus = 0;
		//List<String> target = new ArrayList<String>();
		for(int i = 0; i < elelen; i++){
			String a = element.get(i).get(attlen-1);
			if(a.equals("1")){ plus++;}
			if(a.equals("0")){ minus++;}
			//target.add(a);
			}
	    // give decision for the condition list above
		// if all example are positive
		if(plus == elelen){
			return new Node("1");
		}
		//if all example are negative
		else if(minus== elelen){
			return new Node("0");
		}
		//if no predicting attributes
		else if(attlen-1 == 0){
			if (plus >= minus ) return new Node("1");
			if (plus <  minus ) return new Node("0");
		}else{
			 Impurity entr = new Impurity(element,att,tarAtt);
			 int [] tmp = entr.impurity();
			 int j = tmp[0];
			 root = new Node(att.get(j));
			 root.maxclass = Integer.toString(tmp[1]);
			 // get different element(vi) for the root
			 List<List<String>> value0 = new ArrayList<List<String>>();
			 List<List<String>> value1 = new ArrayList<List<String>>();
			 att.remove(j);
			 for(int i = 0; i < elelen; i++){
				 if(element.get(i).get(j).equals("0")){
					 ArrayList<String> temp0 = new ArrayList<String>();
					 for (int k = 0 ; k < attlen; k++) {
						 temp0.add(element.get(i).get(k));
					 }
					 temp0.remove(j);
					 value0.add(temp0);
				 }else
				 if (element.get(i).get(j).equals("1")){
					 ArrayList<String> temp1 = new ArrayList<String>();
					 for (int k = 0 ; k < attlen; k++) {
						 temp1.add(element.get(i).get(k));
						 }
					 value1.add(temp1);
					 temp1.remove(j);
				 }
			 }
			 if(value0.size()==0){

			 }else{
				 ArrayList<String> attCopy = (ArrayList<String>) att.clone();
				 ID3 zero = new ID3(value0,attCopy,tarAtt);
				 root.leftchildren = zero.idd();
			 }
			 if(value1.size()==0){
				 root.rightchildren = new Node("1");
			 }else{
				 ArrayList<String> attCopy = (ArrayList<String>) att.clone();
				 ID3 one = new ID3(value1,attCopy,tarAtt);
				 root.rightchildren = one.idd();
			 }
		}
		return root;
	}

}
