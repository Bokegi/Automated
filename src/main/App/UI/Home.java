package main.App.UI;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import main.App.Anime;
import main.App.AnimeManager;
import main.App.AnimeState;
import main.App.Exeption.AnimeException;

public class Home {
    private AnimeManager aManager;
    private Scanner scanner = new Scanner(System.in);
    
    public Home(){
        aManager = new AnimeManager();
        aManager.constructor();
        try {
            aManager = AnimeManager.loadData();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());        
        }
        start();
    }

    private void start() {
        int choice;

        do{
        System.out.println("Welcome to the Anime DataBase");
        System.out.println("1. Insert Anime");
        System.out.println("2. Update Anime");
        System.out.println("3. Stamp");
        System.out.println("4. Exit");
        System.out.print("Choose: ");
        
        choice = scanner.nextInt();
            switch(choice){
                case 1:
                    insertAnime();
                    break;

                case 2:
                    updateAnime();
                    break;

                case 3:
                    stamp();
                    break;

                case 4:
                    System.out.println("Exiting.....");
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }  
        }while(choice != 4);
    }

    public void insertAnime() {
        System.out.println("Insert title:");
        String title = scanner.nextLine();
        System.out.println("Insert author:");
        String author = scanner.nextLine();
        System.out.println("Insert production:");
        String production = scanner.nextLine();
        System.out.println("Insert year of production:");
        int year = scanner.nextInt();
        System.out.println("Insert episode:");
        int episode = scanner.nextInt();
        System.out.println("Insert n° episode watched:");
        int nEpisodeWatched = scanner.nextInt();
        scanner.nextLine(); // Consuma il carattere di nuova linea
        
        System.out.println("Select anime state (1 = STARTED, 2 = TO_WATCH, 3 = COMPLETED): ");
        int stateInput = scanner.nextInt();
        AnimeState state;
        switch (stateInput) {
            case 1:
                state = AnimeState.STARTED;
                break;
            case 2:
                state = AnimeState.TO_WATCH;
                break;
            case 3:
                state = AnimeState.COMPLETED;
                break;
            default:
                System.out.println("Invalid selection. Please enter 1, 2, or 3.");
                return; // Esce dal metodo se la selezione è invalida
        }

        Anime newAnime = new Anime(title, author, episode, production, year, nEpisodeWatched, state);

        try {
            aManager.insertAnime(newAnime);
            aManager.saveData(aManager);
            System.out.println("Anime inserted");
        } catch (AnimeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateAnime() {
        System.out.println("Insert the title of the anime you want to update:");
        scanner.nextLine(); // Consuma il carattere di nuova linea
        String title = scanner.nextLine();
        Optional<Anime> animeOpt = aManager.getAnimeList().stream()
            .filter(a -> a.getTitle().equalsIgnoreCase(title))
            .findFirst();
    
        if (animeOpt.isPresent()) {
            Anime anime = animeOpt.get();
            System.out.println("INFO: " + anime);
            System.out.println("Would you like to modify anime state? (y/n)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                System.out.println("Insert anime state (completed, watching, to watch): ");
                String stateInput = scanner.nextLine().toUpperCase();
                try {
                    AnimeState newState = AnimeState.valueOf(stateInput);
                    anime.setState(newState);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid state entered. Please enter one of the following: COMPLETED, WATCHING, TO_WATCH.");
                }
            }
            System.out.println("Would you like to modify n° episode watched? (y/n)");
            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                System.out.println("Insert n° episode watched:");
                anime.setNEpisodeWatched(scanner.nextInt());
                scanner.nextLine(); // Consuma il carattere di nuova linea
            }
            System.out.println("Anime successfully updated!");
            try {
                aManager.saveData(aManager);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Anime not found!");
        }
    }
    
    public void stamp() {
        long completed = aManager.getAnimeList().stream()
            .filter(a -> a.getState() == AnimeState.COMPLETED)
            .count();
        long watching = aManager.getAnimeList().stream()
            .filter(a -> a.getState() == AnimeState.STARTED)
            .count();
        long toWatch = aManager.getAnimeList().stream()
            .filter(a -> a.getState() == AnimeState.TO_WATCH)
            .count();
    
        System.out.println("Statistics:");
        System.out.println("- Anime completed: " + completed);
        System.out.println("- Anime watching: " + watching);
        System.out.println("- Anime to watch: " + toWatch);
    }
    
}