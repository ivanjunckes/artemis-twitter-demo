package twitter;


import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;


@Entity
public class Tweet {

    @Column("_id")
    private long id;

    @Column
    private String user;

    @Column
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
