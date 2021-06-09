package hibernate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Session implements Serializable {
    private String name;
    private Date start;
    private Date end;

    public Session() {
    }

    public Session(String name, Date start, Date end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Id
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "start", nullable = true)
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end", nullable = true)
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(name, session.name) && Objects.equals(start, session.start) && Objects.equals(end, session.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, start, end);
    }
}
