package util;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ConfigAppJson {

	
	public String appName = System.getProperty("program.name");
	public 	String apppath= "/home/" + System.getProperty("user.name") + "/" + appName + "/";
	public String email = "seu.emai@jmail.com";
	public String destinarios = "seu.emai@jmail.com, outro@jmail";
	public String emailpsswd = "password";
	public String database  = "nodatabase";
	public String dbuser = System.getProperty("user.name");
	public String dbpsswd = "password";
	public String configFilename = appName+"config";
	String b = "";
	String c = "";
	
	
	public static void main(String[] args) 
	{
		System.setProperty("program.name","MaricotaApp");
		ConfigAppJson n = ConfigAppJson.loadDefault();
		//n.save();
		n.apppath = "home/" + System.getProperty("user.name");
	}
		
	public void initConfigApp(String filename)
	{
		
	}

	public void save()
	{
		Gson json = new Gson();
		String str = json.toJson(this);
		FileService.CriaSeNaoExiste(apppath);
		FileService.save(apppath + configFilename + ".json", str);
	}

	static public ConfigAppJson load(String filename) 
	{
		String jsonString;
		Gson gson = new Gson();
		jsonString = FileService.readFile(filename);
		ConfigAppJson proj=null;
	    try {
		    proj = (ConfigAppJson) gson.fromJson(jsonString, ConfigAppJson.class);
	    } catch ( JsonSyntaxException err) {
	        err.printStackTrace();
	    }
	    return proj;
	}
	
	static public ConfigAppJson loadDefault() 
	{
		String jsonString;
		Gson gson = new Gson();
		String appName = System.getProperty("program.name");
		String apppath= "/home/" + System.getProperty("user.name") + "/" + appName + "/";
		String configFilename = appName + "config";

		jsonString = FileService.readFile( apppath + configFilename + ".json");
		ConfigAppJson proj=null;
	    try {
			if(jsonString.equals("")){
				proj = new ConfigAppJson();
				proj.save();
			}
			else
				proj = (ConfigAppJson) gson.fromJson(jsonString, ConfigAppJson.class);
	    } catch ( JsonSyntaxException err) {
	        err.printStackTrace();
	    }
	    return proj;
	}


}
