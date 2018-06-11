package central;

 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import util.GetUrl;

public class Dweet {
	

	String by;
	String the;
	Thing with[];
	public boolean fgNovaOcorrencia = false;

//my-thing-name?hello=world&foo=bar";
	
	
	public static void main(String[] args) {
		String mything = "ocorre2018";
		String lasturl ="https://dweet.io/get/dweets/for/";
		String jsonString = GetUrl.texto(lasturl + mything);  // le 5 ultimos dweets
		Dweet kkk = load (jsonString);
		printa(jsonString);
			}

	static public Dweet parse(String jsonString) 
	{
		//String jsonString;
		Gson gson = new Gson();
		//jsonString = readFile(inpath+pronac+".json");
		Dweet proj=null;
	    try {
		    proj = (Dweet) gson.fromJson(jsonString, Dweet.class);
	    } catch ( JsonSyntaxException err) {
	        err.printStackTrace();
	    }
	    return proj;
	}
	
	static public Dweet load(String filename) 
	{
		String jsonString;
		Gson gson = new Gson();
		jsonString = readFile(filename);
		Dweet proj=null;
	    try {
		    proj = (Dweet) gson.fromJson(jsonString, Dweet.class);
	    } catch ( JsonSyntaxException err) {
	        err.printStackTrace();
	    }
	    return proj;
	}
	
	static String readFile(String file) //throws IOException 
	{
	    BufferedReader reader;// = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");
	    try {
	    	reader = new BufferedReader(new FileReader (file));
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }
	        reader.close();
	        return stringBuilder.toString();
	    } catch(IOException err){
	    	err.printStackTrace();
	    	return "";
	    }
	}
	
	
	static void printa(String jsons)	
	{
		String jsonString = "";
		JsonParser parser = new JsonParser();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement el = parser.parse(jsons);
		jsonString = gson.toJson(el); // done
		System.out.println(jsonString);
	}
	
	public void insert (Thing novo)
	{
		for(int i=0; i<with.length; i++)
			if (with[i].created.equals(novo.created))
				return;
		Thing nwith[] = new Thing[with.length+1];
		nwith[0] = novo;
		fgNovaOcorrencia = true;
		for(int i=0; i<with.length; i++)
			nwith[i+1] = with[i];
		with = nwith;
		return;

	}
	
	


}
