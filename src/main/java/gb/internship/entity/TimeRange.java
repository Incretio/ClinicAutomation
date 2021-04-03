package gb.internship.entity;

import javax.persistence.*;

@Entity
@Table (name = "TimeRange")
public class TimeRange {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String start;
    private String stop;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

}
