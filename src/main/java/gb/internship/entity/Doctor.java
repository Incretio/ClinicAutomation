package gb.internship.entity;

import javax.persistence.*;
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
    private Date dateOfEmployment;

    @ManyToOne
    @JoinColumn(name = "specializationId", nullable = false)
    private Specialization specialization;
//    @OneToMany(mappedBy = "doctor")
//    private Set<ScheduleRecord> scheduleRecords;
    @OneToMany(mappedBy = "doctor")
    private Set<TimeRangeToDoctor> timeRangeToDoctors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Set<ScheduleRecord> getScheduleRecords() {
//        return scheduleRecords;
//    }

//    public void setScheduleRecords(Set<ScheduleRecord> scheduleRecords) {
//        this.scheduleRecords = scheduleRecords;
//    }

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
