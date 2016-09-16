package be.occam.android.minimaxi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sven on 14/09/16.
 */
public class Adventure implements Parcelable {

    public static Parcelable.Creator CREATOR
            = new Parcelable.Creator<Adventure>() {

            public Adventure createFromParcel(Parcel in) {

                Adventure adventure
                        = new Adventure();

                adventure.setDate( in.readString() );
                adventure.setTitle( in.readString() );
                adventure.setDescription( in.readString() );
                adventure.setMedia( in.readString() );
                adventure.setType( in.readString() );

                return adventure;

            }

            public Adventure[] newArray(int size) {
                return new Adventure[size];
            }

    };

    protected String media;
    protected String type;
    protected String title;
    protected String description;
    protected String date;

    public Adventure() {

    }

    private Adventure(Parcel in) {
        title = in.readString();
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

    public void setMedia(String media) {
        this.media = media;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( this.getDate() );
        dest.writeString( this.getTitle() );
        dest.writeString( this.getDescription() );
        dest.writeString( this.getMedia() );
        dest.writeString( this.getType() );
    }

}
