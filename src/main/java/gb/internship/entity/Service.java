package gb.internship.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "Service")
public class Service {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    @ManyToMany(mappedBy = "services")
    private Set<ScheduleRecord> scheduleRecords;

    public Set<ScheduleRecord> getScheduleRecords() {
        return scheduleRecords;
    }

    public void setScheduleRecords(Set<ScheduleRecord> scheduleRecords) {
        this.scheduleRecords = scheduleRecords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
