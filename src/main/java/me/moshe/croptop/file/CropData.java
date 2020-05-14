package me.moshe.croptop.file;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public class CropData {
    public static HashMap<UUID, Integer> cropMap = new HashMap<UUID, Integer>();
    public static String Path = "plugins/CropTop" + File.separator + "CropData.dat";

    public static void setup(){
        File file = new File(Path);
        new File("plugins/CropTop").mkdir();
        if(file.exists()){
            cropMap = load();
        }
        if(cropMap == null){
            cropMap = new HashMap<UUID, Integer>();
        }
    }

    public static void save(){
        File file = new File(Path);
        new File("plugins/CropTop").mkdir();
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Path));
            oos.writeObject(cropMap);
            oos.flush();
            oos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static HashMap<UUID, Integer> load(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Path));
            Object result = ois.readObject();
            ois.close();
            return (HashMap<UUID,Integer>)result;
        }catch(Exception e){
            return null;
        }
    }
}
