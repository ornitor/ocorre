package central;

import util.KMLGenerator;
import java.io.File;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import util.JavaMailApp;
import com.google.gson.Gson;
import util.GetUrl;
import util.FileService;

public class CityCentral
{
    
    Dweet baseOcorrencias;
    static String inpath = "/home/pi/geografic/";
    static String basefile = "ocorrenciasdb.json";
    
    static CityCentral c ;
    static String mything = "ocorre2018";
    static String lasturl = "https://dweet.io/get/dweets/for/";
    static boolean fgNovaOcorrencia = false;
    
    static String ocorrencia[] = { "buraco","acidente","cano furado","obstáculo PCD","elétrico"};
    
    public static void main(String[] args) 
    {
            setup();
            for(int i=0;;i++){
                if( i%15 ==0) 
                    System.out.println("iteração " + i);
                if( i%60 ==0) {
                    fgNovaOcorrencia = false;    // dentro de loop se atualizara este flag para true se for o caso
                    loop();
                    if(fgNovaOcorrencia)
                        JavaMailApp.send(geraKml(),"othonrocha@gmail.com");
                    }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
    
    public static void setup() 
    {
    }
    
    public static void loop() {
        c = new CityCentral();
        try{
            String jsonString = GetUrl.texto(lasturl + mything);  // le 5 ultimos dweets
            Dweet kkk = Dweet.parse (jsonString);
            c.baseOcorrencias = Dweet.load(inpath + mything + "/" + basefile);
            if (c.baseOcorrencias==null)
                c.baseOcorrencias = kkk;
            else
                for(int i=0; i < kkk.with.length; i++)
                    c.baseOcorrencias.insert(kkk.with[i]);
            if(c.baseOcorrencias.fgNovaOcorrencia)
                fgNovaOcorrencia = true;
            Gson json = new Gson();
            jsonString = json.toJson(c.baseOcorrencias);
            save(inpath + mything + "/" + basefile, jsonString);
        }  catch(Exception err){err.printStackTrace();}
        
    }
    
    static String geraKml()
    {
        c.baseOcorrencias = Dweet.load(inpath + mything + "/" + basefile);
        String str = "";
        String str1="";
        String str2[] = new String [ocorrencia.length];
        double color1[] = {1,0,0};
        double color2[] = {1,1,0};
        for(int i=0;i<ocorrencia.length;i++) { // matriz de marcadores: 5tipos x 10 cores alinhadas
            str2[i] = "";
            for(int j=0;j<KMLGenerator.markerType.length;j++){
                String color = KMLGenerator.geraCor(color1,color2,i,ocorrencia.length);
                str1 = str1+ KMLGenerator.geraStyle( "wht-" + KMLGenerator.markerType[j], color);  // id do marcador 'wht-tipo+cor'
            }
        }
        for(int i=0;i<c.baseOcorrencias.with.length;i++){ // placemarks 
            int k=0;
            if (c.baseOcorrencias.with[i].content.ocorrencia != null){
                k = Integer.parseInt(c.baseOcorrencias.with[i].content.ocorrencia) ;
                str2[k-1] = str2[k-1] + KMLGenerator.geraPlacemark(ocorrencia[k-1], 
                    c.baseOcorrencias.with[i].content.latit,c.baseOcorrencias.with[i].content.longit,
                "wht-"+KMLGenerator.markerType[(k-1)%5], KMLGenerator.geraCor(color1,color2,i,ocorrencia.length) );
            }
        }
        String str3 = "";
        for(int i=0;i<ocorrencia.length;i++) 
            str3 = str3 + KMLGenerator.folder(ocorrencia[i], str2[i]);
        str = KMLGenerator.documentoKML("Ocorre2018", str1, str3 );
        String filename = inpath + mything + "/" + FileService.getTemporalName("ocorre2018-") +".kml";
        save(filename,str);
        return filename;

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


}
