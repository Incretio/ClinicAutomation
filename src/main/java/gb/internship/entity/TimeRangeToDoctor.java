package gb.internship.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "TimeRangeToDoctor")
public class TimeRangeToDoctor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn (name = "doctorId", nullable = false)
    private Doctor doctor;
    @ManyToOne
    @JoinColumn (name = "timeRangeId", nullable = false)
    private TimeRange timeRange;
    private int dateOfReceipt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
    }

    public int getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(int dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }
}
