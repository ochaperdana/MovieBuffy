package id.sch.smktelkom_mlg.privateassignment.xirpl125.moviebuffy;

import java.io.Serializable;

/**
 * Created by Smktelkom on 0012, 5/12/2017.
 */

public class UpcomingItem implements Serializable {
    private String imageUrl;
    private String title;

    public UpcomingItem(String imageUrl, String title) {
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
