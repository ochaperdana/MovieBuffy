package id.sch.smktelkom_mlg.privateassignment.xirpl125.moviebuffy;

import java.io.Serializable;

/**
 * Created by Smktelkom on 0010, 5/10/2017.
 */

public class DataItem implements Serializable {
    private String imageUrl;
    private String title;

    public DataItem(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }
}
