import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class readfile {
	ArrayList<List<String>> element;
	ArrayList<String> att;
	String tarAtt;
	// read file and save data as 2D array
	public void data(String filename){
		String csvFile = filename;
		BufferedReader br = null;
		String line="";
		String csvSplitBy=",";
		element = new ArrayList<List<String>>();
		// save all element
		try{
			br = new BufferedReader( new FileReader(csvFile));
			// get first line
			String attribute = br.readLine();
			String[] attri = attribute.split(csvSplitBy);// get all elements in first line
			// get the target attribute
		    tarAtt = attri[attri.length-1];
			// get all attributes of first line into list
			att = new ArrayList<String>(Arrays.asList(attri));
			//att.remove(att.size()-1);
			// save all element into List<List<String>> except the first line
			while((line = br.readLine()) != null ){
			String[] datas = line.split(csvSplitBy);
			List<String> data = new ArrayList<String>(Arrays.asList(datas));
			element.add(data);
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
		    e.printStackTrace();
		} finally {
			if(br != null ){
				try{
					br.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
}
