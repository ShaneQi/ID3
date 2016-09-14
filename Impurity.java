import java.util.List;

public class Impurity {
	List<List<String>> element;
	List<String> att;
	String tarAtt;

	Impurity(List<List<String>> e, List<String> a,String t){
		element =e;
		att = a;
		tarAtt = t;
	}

	public int[] impurity(){
		//get length of all data;
		int elelen = element.size();
		// get the length of all attributes
		int attlen = att.size();
		// for every attribute calculate their entropy
		int[] att0left = new int[attlen];
		int[] att0right = new int[attlen];
		int[] att1left = new int[attlen];
		int[] att1right = new int[attlen];
		for(int j = 0; j < attlen-1;j++){
			for(int i = 0; i < elelen; i++){
				if(element.get(i).get(j).equals("0")){
					if(element.get(i).get(attlen-1).equals("0")) att0left[j]++;//att==0 class==0
					if(element.get(i).get(attlen-1).equals("1")) att0right[j]++;//att ==0 class ==1
				}
				if(element.get(i).get(j).equals("1")){
					if(element.get(i).get(attlen-1).equals("0")) att1left[j]++;//att==1 class ==0
					if(element.get(i).get(attlen-1).equals("1")) att1right[j]++;//att==1 class==1
				}
			}
			}
		// get the children of smallest entropy
		    int target = 0;
		    double entropy = 2;
		    double entrol  = 0;
		    double entror  = 0;
		for(int j = 0; j < attlen-1;j++){
			if(att0left[j] != 0|| att0right[j] !=0){
			double xl = (double)att0left[j]/(att0left[j]+att0right[j]);
			double yl = (double)att0right[j]/(att0left[j]+att0right[j]);
			//impurity
			if (xl == 0 || yl == 0) entrol = 0;
			else entrol = att0left[j]*att0right[j]/Math.sqrt(att0left[j]+att0right[j]);
			}
			if(att1left[j] != 0 || att1right[j] !=0){
			double xr = (double)att1left[j]/(att1left[j]+att1right[j]);
			double yr = (double)att1right[j]/(att1left[j]+att1right[j]);
			//impurity
			if (xr == 0 || yr == 0)  entror = 0;
			else entror = att1left[j]*att1right[j]/Math.sqrt(att1left[j]+att1right[j]);
			}
			double aa = att0left[j]+att0right[j];
			double bb = att1left[j]+att1right[j];
			double cc = aa+bb;
			double aaa = aa/cc;
			double bbb = bb/cc;
			double res = aaa * entrol + bbb * entror;
			if(res < entropy){entropy = res;  target = j;}
		}
		int max;
		if(att0left[target]+att1left[target]>=att0right[target]+att1right[target]) max = 0;
		else max = 1;
		return new int[] {target, max};
	}

}
