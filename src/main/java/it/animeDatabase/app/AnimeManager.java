package it.animeDatabase.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


import it.animeDatabase.Exeption.AnimeException;

public class AnimeManager implements Serializable{

    private ArrayList<Anime> animeList;
    
    private final static String FILE_NAME = "saveData/animeData.ser";

    public AnimeManager() {
    }

    public void constructor() {
        this.animeList = new ArrayList<>();
    }

    public void insertAnime(Anime anime) throws AnimeException {

        for(Anime a : animeList){
            if(a.getTitle().equals(a.getTitle())){
                throw new AnimeException("Anime already present!");
            }
        }
        animeList.add(anime);
    }

    public void saveData(AnimeManager animeManager) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(animeManager);
            System.out.println("Data successfully saved!");
        } catch (Exception e) {
            System.out.println("Error during saving: " + e.getMessage());
        }
    }

    public static AnimeManager loadData() throws IOException {
        try (
            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            Object object = ois.readObject();
            if (object instanceof AnimeManager) {
                return (AnimeManager) object;
            } else {
                System.out.println("Unexpected object type: " + object.getClass().getName());
                return null;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
            return null;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return null;
        }
    }
   
    public ArrayList<Anime> getAnimeList() {
        return animeList;
    }
}
