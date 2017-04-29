package twitter;


import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Entity
public class Tweet {

    @Column("_id")
    private long id;

    @Column
    private String user;

    @Column
    private String text;

    @Column
    private Date createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Tweet(String user, String text, Date createdAt) {
        this.user = user;
        this.text = text;
        this.createdAt = createdAt;
    }
}
