package central;

import util.KMLGenerator;


import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import util.ConfigAppJson;
import util.FileService;
import util.GetUrl;
import util.JavaMailApp;

public class Arruma{
    
    static String path = "/home/root/ocorrePi2018/ocorre2018/";
    
public static void main(String args[]) {
        Dweet baseocu = new Dweet();;
        try{
            Dweet kkk = Dweet.load(path + "ocorrenciasdb.json");
            baseocu = Dweet.load(path + "ocorenciasdbseg.json");
            if (baseocu==null)
                baseocu = kkk;
            else
                for(int i=0; i < kkk.with.length; i++)
                    baseocu.insert(kkk.with[i]);
            Gson json = new Gson();
            String str= json.toJson(baseocu);
            FileService.save(path + "ocorrenciasdbter.json",str);
        }catch(Exception err){err.printStackTrace();}
        
    }
    
}
