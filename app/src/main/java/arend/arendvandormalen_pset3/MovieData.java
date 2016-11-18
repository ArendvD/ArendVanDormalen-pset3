package arend.arendvandormalen_pset3;

/**
 * Created by Arend on 2016-11-15.
 * Custom data class per movie
 */

public class MovieData {

    public String title;
    public String year;
    public String plot;
    public String actors;
    public String imageLink;
    public String director;
    public String runtime;

    public MovieData(String title, String year, String imageLink){
        this.title = title;
        this.year = year;
        this.imageLink = imageLink;
    }

    // "Set"-methods
    public void setPlot(String plot){
        this.plot = plot;
    }
    public void setActors(String actors) {
        this.actors = actors;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    // "Get"-methods
    public String getTitle(){
        return title;
    }
    public String getYear() {
        return year;
    }
    public String getPlot() {
        return plot;
    }
    public String getActors() {
        return actors;
    }
    public String getImageLink(){
        return imageLink;
    }
    public String getDirector() {
        return director;
    }
    public String getRuntime() {
        return runtime;
    }
}
