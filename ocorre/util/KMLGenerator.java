package util;

public class KMLGenerator {
	
		
public static String markerType[] = {
			"blank","diamond","stars","square","circle",
	};
	
	
	
	public static String documentoKML(String nome, String style, String places)
	{
			return preKml(nome) + style + places + posKml();
	}
	
	public static String folder(String nome, String str)
	{
			return prefolder(nome) + str + posfolder();
	}
	
	
	public static String prefolder(String nome)
	{
		String str = "";
		 str = str + "\t<Folder>\r\n";
		 str = str + "\t\t<name>" + nome + "</name>\r\n";
		 str = str + "\t\t<open>0</open>\r\n";
		return str;
	}
	
	public static String posfolder()
	{
		String str = "";
		 str = str + "\t</Folder>\r\n";
		return str;
		
	}
	
	public static String preKml(String nome)
	{
		String str = "";
		 str = str + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
		 str = str + "<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\" xmlns:kml=\"http://www.opengis.net/kml/2.2\" xmlns:atom=\"http://www.w3.org/2005/Atom\">\r\n";
		 str = str + "<Document>\r\n";
		 str = str + "\t<name>"+nome+"</name>\r\n";
		return str;
	}
	
	
	public static String posKml()
	{
		String str = "";
		 str = str + "</Document>\r\n";
		 str = str + "</kml>\r\n";

		return str;
		
	}
	

	
	public static String geraStyle(String nome, double []color)
	{
		String str = "";
		str = str + geraStyleMap(nome, color);
		str = str + geraStyleIcon(nome, color);
		return str;
	}
	
	public static String geraStyle(String nome, String color)
	{
		String str = "";
		str = str + geraStyleMap(nome, color);
		str = str + geraStyleIcon(nome, color);
		return str;
	}
	
	
	
	static public String geraCor(double[] color)
	{
		for(int i=0;i<color.length;i++){
			if(color[i] > 1)
				color[i] = 1;
			if(color[i] < 0)
				color[i] = 0;
		}
		String str = String.format("FF%02X%02X%02X", (int)(255*color[2]),(int)(255*color[1]),(int)(255*color[0]));
		return str;
	}
	
	static public String geraCor(double[] color1,double[] color2, int j, int n)
	{
		double color[] = new double[3];
		for(int i=0;i<color.length;i++){
			color[i] = (color2[i]*j + color1[i]*(n-j))/n;
			if(color[i] > 1)
				color[i] = 1;
			if(color[i] < 0)
				color[i] = 0;
		}
		String str = String.format("FF%02X%02X%02X", (int)(255*color[2]),(int)(255*color[1]),(int)(255*color[0]));
		return str;
	}
	
	public static String geraStyleIcon(String nome, double []color)
	{
		String str = "";
		str = str + "\t<Style id=\"sn_"+ nome + geraCor(color) + "\">\n";
		str = str + "\t\t<IconStyle>\n";
		str = str + "\t\t\t<color>"+ geraCor(color) +"</color>\n";
		str = str + "\t\t\t<scale>1.1</scale>\n";
		str = str + "\t\t\t<Icon>\n"; 
		str = str + "\t\t\t\t<href>http://maps.google.com/mapfiles/kml/paddle/"+ nome +".png</href>\n";
		str = str + "\t\t\t</Icon>\n";
		str = str + "\t\t\t<hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\n";
		str = str + "\t\t</IconStyle>\n";
		str = str + "\t\t<ListStyle>\n";
		str = str + "\t\t\t<ItemIcon>\n";
		str = str + "\t\t\t\t<href>http://maps.google.com/mapfiles/kml/paddle/"+ nome +"-lv.png</href>\n";
		str = str + "\t\t\t</ItemIcon>\n";
		str = str + "\t\t</ListStyle>\n";
		str = str + "\t</Style>\n";

		str = str + "\t<Style id=\"sh_"+ nome + geraCor(color) + "\">\n";
		str = str + "\t\t<IconStyle>\n";
		str = str + "\t\t\t<color>"+ geraCor(color) +"</color>\n";
		str = str + "\t\t\t<scale>1.3</scale>\n";
		str = str + "\t\t\t<Icon>\n";
		str = str + "\t\t\t\t<href>http://maps.google.com/mapfiles/kml/paddle/"+ nome +".png</href>\n";
		str = str + "\t\t\t</Icon>\n";
		str = str + "\t\t\t<hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\n";
		str = str + "\t\t</IconStyle>\n";
		str = str + "\t\t<ListStyle>\n";
		str = str + "\t\t\t<ItemIcon>\n";
		str = str + "\t\t\t\t<href>http://maps.google.com/mapfiles/kml/paddle/"+ nome +"-lv.png</href>\n";
		str = str + "\t\t\t</ItemIcon>\n";
		str = str + "\t\t</ListStyle>\n";
		str = str + "\t</Style>\n";
		return str;
	}

	public static String geraStyleIcon(String nome, String color)
	{
		String str = "";
		str = str + "\t<Style id=\"sn_"+ nome + color + "\">\n";
		str = str + "\t\t<IconStyle>\n";
		str = str + "\t\t\t<color>"+ color +"</color>\n";
		str = str + "\t\t\t<scale>1.1</scale>\n";
		str = str + "\t\t\t<Icon>\n"; 
		str = str + "\t\t\t\t<href>http://maps.google.com/mapfiles/kml/paddle/"+ nome +".png</href>\n";
		str = str + "\t\t\t</Icon>\n";
		str = str + "\t\t\t<hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\n";
		str = str + "\t\t</IconStyle>\n";
		str = str + "\t\t<ListStyle>\n";
		str = str + "\t\t\t<ItemIcon>\n";
		str = str + "\t\t\t\t<href>http://maps.google.com/mapfiles/kml/paddle/"+ nome +"-lv.png</href>\n";
		str = str + "\t\t\t</ItemIcon>\n";
		str = str + "\t\t</ListStyle>\n";
		str = str + "\t</Style>\n";

		str = str + "\t<Style id=\"sh_"+ nome + color + "\">\n";
		str = str + "\t\t<IconStyle>\n";
		str = str + "\t\t\t<color>"+ color +"</color>\n";
		str = str + "\t\t\t<scale>1.3</scale>\n";
		str = str + "\t\t\t<Icon>\n";
		str = str + "\t\t\t\t<href>http://maps.google.com/mapfiles/kml/paddle/"+ nome +".png</href>\n";
		str = str + "\t\t\t</Icon>\n";
		str = str + "\t\t\t<hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\n";
		str = str + "\t\t</IconStyle>\n";
		str = str + "\t\t<ListStyle>\n";
		str = str + "\t\t\t<ItemIcon>\n";
		str = str + "\t\t\t\t<href>http://maps.google.com/mapfiles/kml/paddle/"+ nome +"-lv.png</href>\n";
		str = str + "\t\t\t</ItemIcon>\n";
		str = str + "\t\t</ListStyle>\n";
		str = str + "\t</Style>\n";
		return str;
	}

	public static String geraStyleMap(String nome, double []color)
	{
		String str = "";
		str = str + "\t<StyleMap id=\"msn_"+ nome + geraCor(color)  +"\">\n";
		str = str + "\t\t<Pair>\n";
		str = str + "\t\t\t<key>normal</key>\n";
		str = str + "\t\t\t<styleUrl>#sn_"+ nome + geraCor(color) + "</styleUrl>\n";
		str = str + "\t\t</Pair>\n";
		str = str + "\t\t<Pair>\n";
		str = str + "\t\t\t<key>highlight</key>\n";
		str = str + "\t\t\t<styleUrl>#sh_"+ nome + geraCor(color) + "</styleUrl>\n";
		str = str + "\t\t</Pair>\n";
		str = str + "\t</StyleMap>\n";

		return str;
	}
	
	public static String geraStyleMap(String nome, String color)
	{
		String str = "";
		str = str + "\t<StyleMap id=\"msn_"+ nome + color  +"\">\n";
		str = str + "\t\t<Pair>\n";
		str = str + "\t\t\t<key>normal</key>\n";
		str = str + "\t\t\t<styleUrl>#sn_"+ nome + color + "</styleUrl>\n";
		str = str + "\t\t</Pair>\n";
		str = str + "\t\t<Pair>\n";
		str = str + "\t\t\t<key>highlight</key>\n";
		str = str + "\t\t\t<styleUrl>#sh_"+ nome + color + "</styleUrl>\n";
		str = str + "\t\t</Pair>\n";
		str = str + "\t</StyleMap>\n";

		return str;
	}
	
	public static String geraPlacemark(String nome, double latit, double longit, String pinname, double[]color)
	{
		nome = nome.replace("&", "e");
		String str = "";
		 str = str + "\t\t<Placemark>\r\n";
		 str = str + "\t\t\t<name>"+nome+"</name>\r\n";
		 str = str + "\t\t\t<LookAt>\r\n";
		 str = str + "\t\t\t\t<longitude>"+longit+"</longitude>\r\n";
		 str = str + "\t\t\t\t<latitude>"+latit+"</latitude>\r\n";
		 str = str + "\t\t\t\t<altitude>0</altitude>\r\n";
		 str = str + "\t\t\t\t<heading>4.634987011374514</heading>\r\n";
		 str = str + "\t\t\t\t<tilt>0</tilt>\r\n";
		 str = str + "\t\t\t\t<range>55008.78450351994</range>\r\n";
		 str = str + "\t\t\t\t<altitudeMode>relativeToGround</altitudeMode>\r\n";
		 str = str + "\t\t\t\t<gx:altitudeMode>relativeToSeaFloor</gx:altitudeMode>\r\n";
		 str = str + "\t\t\t</LookAt>\r\n";
		 str = str + "\t\t\t<styleUrl>#msn_"+ pinname + geraCor(color)  +"</styleUrl>\r\n";
		 str = str + "\t\t\t<Point>\r\n";
		 str = str + "\t\t\t\t<altitudeMode>clampToGround</altitudeMode>\r\n";
		 str = str + "\t\t\t\t<gx:altitudeMode>clampToSeaFloor</gx:altitudeMode>\r\n";
		 str = str + "\t\t\t\t<coordinates>"+longit+", "+latit+" </coordinates>\r\n";
		 str = str + "\t\t\t</Point>\r\n";
		 str = str + "\t\t</Placemark>\r\n";
			return str;
	}

	public static String geraPlacemark(String nome, String latit, String longit, String pinname, String color)
	{
		nome = nome.replace("&", "e");
		String str = "";
		 str = str + "\t\t<Placemark>\r\n";
		 str = str + "\t\t\t<name>"+nome+"</name>\r\n";
		 str = str + "\t\t\t<LookAt>\r\n";
		 str = str + "\t\t\t\t<longitude>"+longit+"</longitude>\r\n";
		 str = str + "\t\t\t\t<latitude>"+latit+"</latitude>\r\n";
		 str = str + "\t\t\t\t<altitude>0</altitude>\r\n";
		 str = str + "\t\t\t\t<heading>4.634987011374514</heading>\r\n";
		 str = str + "\t\t\t\t<tilt>0</tilt>\r\n";
		 str = str + "\t\t\t\t<range>55008.78450351994</range>\r\n";
		 str = str + "\t\t\t\t<altitudeMode>relativeToGround</altitudeMode>\r\n";
		 str = str + "\t\t\t\t<gx:altitudeMode>relativeToSeaFloor</gx:altitudeMode>\r\n";
		 str = str + "\t\t\t</LookAt>\r\n";
		 str = str + "\t\t\t<styleUrl>#msn_"+ pinname + color  +"</styleUrl>\r\n";
		 str = str + "\t\t\t<Point>\r\n";
		 str = str + "\t\t\t\t<altitudeMode>clampToGround</altitudeMode>\r\n";
		 str = str + "\t\t\t\t<gx:altitudeMode>clampToSeaFloor</gx:altitudeMode>\r\n";
		 str = str + "\t\t\t\t<coordinates>"+longit+", "+latit+" </coordinates>\r\n";
		 str = str + "\t\t\t</Point>\r\n";
		 str = str + "\t\t</Placemark>\r\n";
		 return str;
	}
	
	public static String geraPlacemark(String nome, double latit, double longit, String pinname, String color)
	{
			return geraPlacemark(nome, String.valueOf(latit), String.valueOf(longit), pinname, color);
	}
	


}
