package main.App;
import java.io.Serializable;

public class Anime implements Serializable{
    
    private String title;
    private String author;
    private String production;
    private int year, episode;
    private int nEpisodeWatched;
    private AnimeState state;

    public Anime(String title, String author, int episode, String production, int year, int nEpisodeWatched, AnimeState state) {
        this.title = title;
        this.author = author;
        this.episode = episode;
        this.production = production;
        this.year = year;
        this.nEpisodeWatched = nEpisodeWatched;
        this.state = state.STARTED;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = Title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getnEpisodeWatched() {
        return nEpisodeWatched;
    }

    public void setNEpisodeWatched(int nEpisodeWatched) {
        this.setNEpisodeWatched(nEpisodeWatched);
        if (nEpisodeWatched == episode) {
            state = AnimeState.COMPLETED;
        } 
    }
     
    public AnimeState getState() {
        return this.state;
    }

    public void setState(AnimeState newState) {
        this.state = newState;
    }

    @Override
    public String toString() {
        return "Anime{" + "title=" + title + ", author=" + author + ", episode=" + episode +
                 ", production=" + production + ", year=" + year + ", nEpisodeWatched=" + getnEpisodeWatched() +
                  ", state=" + state + '}';
    }
}