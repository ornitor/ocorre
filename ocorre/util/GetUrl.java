package util;

import java.net.*;
import java.io.*;

import java.net.URL;
import java.net.URLConnection;


public class GetUrl {

	

	public static String texto(String qurl)
	{
		String texto = "";
	    try {
	        URL url = new URL(qurl);  
	    	System.out.println("Vou ler..."+qurl);
	        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	        String str;
	        while ((str = in.readLine()) != null) {
	        	texto = texto + str;
	            //fala (str); // str is one line of text; readLine() strips the newline character(s)
	        }
	        in.close();
	    } catch (MalformedURLException e) { e.printStackTrace();
	    } catch (IOException e) { e.printStackTrace();
	    }
        return texto;   
	}
	
}
