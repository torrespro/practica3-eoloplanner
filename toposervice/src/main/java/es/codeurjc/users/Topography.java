package es.codeurjc.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "{'locale':'es', 'strength':1}")
public class Topography {

    @Id
    private String id;

    private String city;
    private String landscape;

    protected Topography() {
        // Used by SpringData
    }

    public Topography(String city, String landscape) {
        this(null, city, landscape);
    }

    public Topography(String id, String city, String landscape) {
        this.id = id;
        this.city = city;
        this.landscape = landscape;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, city='%s', landscape='%s']",
            id, city, landscape);
    }
}
