package be.occam.android.minimaxi;

/**
 * Created by sven on 14/09/16.
 */
public class Adventure {

    protected String media;
    protected String type;
    protected String title;
    protected String description;
    protected String date;

    public Adventure() {

    }

    public Adventure( String title ) {
        this.title = title;
    }

    public String getMedia() {
        return media;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

}
