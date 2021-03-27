package gb.internship.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "Invoice")
public class Invoice {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn (name = "clientId", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn (name = "doctorId", nullable = false)
    private Doctor doctor;
    @ManyToOne
    @JoinColumn (name = "timeRangeToDoctorId", nullable = false)
    private TimeRangeToDoctor timeRangeToDoctor;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "InvoiceToService",
        joinColumns = { @JoinColumn(name = "invoiceId") },
        inverseJoinColumns = { @JoinColumn(name = "serviceId") }
    )
    private Set<Service> services;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TimeRangeToDoctor getTimeRangeToDoctor() {
        return timeRangeToDoctor;
    }

    public void setTimeRangeToDoctor(TimeRangeToDoctor timeRangeToDoctor) {
        this.timeRangeToDoctor = timeRangeToDoctor;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
