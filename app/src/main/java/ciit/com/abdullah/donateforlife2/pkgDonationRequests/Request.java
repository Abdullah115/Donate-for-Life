package ciit.com.abdullah.donateforlife2.pkgDonationRequests;

/**
 * Created by Abdullah on 2/17/2018.
 */

public class Request {
    public Request(){
       /* this.setImage_id(image_id);
        this.setTitles(titles);
        this.setDetails(details);*/
    }
    private String image_id;
    private String titles,details;
    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
