import java.io.Serializable;

public class Session implements Serializable {
    private String name;
    private DateTime start;
    private DateTime end;
    public Session(){}
    public Session(String name,DateTime start,DateTime end)
    {
        this.name=name;
        this.start=start;
        this.end=end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }
}
