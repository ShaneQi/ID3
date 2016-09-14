import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Accuracy {
	List<List<String>> element;
	ArrayList<String> att;
	Node root;
	Accuracy(List<List<String>> e,ArrayList<String> a,Node r){
		root = r;
		att = a;
		element =e;
	}
	//get the accuracy between the decision tree and data
	 public double accuracy(){
		 double a =0;
		 // save attribute name into hash set
		 HashMap<String,Integer> map = new HashMap<String,Integer>();
		 int att_len = att.size();
		 for(int j =0; j< att_len; j++){
		  map.put(att.get(j),j);
		 }
		 // check all lines of data
		 int right = 0;
		 int element_len = element.size();
		 Node nodeTmp = root;
		 for(int i = 0; i < element_len; i++){
			while(!(nodeTmp.name.equals("0")||nodeTmp.name.equals("1"))){
				int jj = map.get(nodeTmp.name);
				if(element.get(i).get(jj).equals("0")){
					nodeTmp = nodeTmp.leftchildren;}
				if(element.get(i).get(jj).equals("1")){
					nodeTmp = nodeTmp.rightchildren;}
			 }
			if(nodeTmp.name.equals(element.get(i).get(att_len-1))) right++;
			nodeTmp = root;
		 }
		 a = (double)right/element_len;
		return a;
	 }

}
