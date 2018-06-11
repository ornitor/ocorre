package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileService {

	static String inpath = "/home/" + System.getProperty("user.name");
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	static public  void save(String fname, String content) {
		File aFile = new File(fname);
		System.out.println("Salvando "+aFile.getName()+"...");
	    try {
	      PrintStream p = new PrintStream(aFile);
	      p.println(content);
	      p.close();

	    } catch (Exception e) {
	      e.printStackTrace();
	      System.err.println(aFile);
	    }
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
	
	public static  void CriaSeNaoExiste(String path)
	{
	         File dir = new File(path);
	   	  boolean isDirCreated = dir.mkdirs();
	   	  if (isDirCreated)
	   		  System.out.println("Path ok!");
	   	  else
	   	      System.out.println("Falha ao criar path "+path);
}

	static public String getTemporalName(String nome) {
	    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
	    Date date = new Date();
	    return nome+dateFormat.format(date);
	}


}
