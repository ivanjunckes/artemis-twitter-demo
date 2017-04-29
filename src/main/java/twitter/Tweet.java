package twitter;


import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Tweet {

    @Column("_id")
    private String id;

    @Column
    private String user;

    @Column
    private String text;

    @Column
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Tweet(){

    }

    public Tweet(String id, String user, String text, String createdAt) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.createdAt = createdAt;
    }
}
