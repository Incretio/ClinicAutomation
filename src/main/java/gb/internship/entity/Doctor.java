package gb.internship.entity;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Table (name = "Doctor")
public class Doctor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String secondName;
    private String patronymic;
    @ManyToOne
    @JoinColumn(name = "specializationId", nullable = false)
    private Specialization specialization;
    @OneToMany(mappedBy = "doctor")
    private Set<Invoice> invoices;
    private Date dateOfEmployment;
    @OneToMany(mappedBy = "doctor")
    private Set<TimeRangeToDoctor> timeRangeToDoctors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getSpecialization() {
        return specialization.getId();
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public Set<TimeRangeToDoctor> getTimeRangeToDoctors() {
        return timeRangeToDoctors;
    }

    public void setTimeRangeToDoctors(Set<TimeRangeToDoctor> timeRangeToDoctors) {
        this.timeRangeToDoctors = timeRangeToDoctors;
    }
}
