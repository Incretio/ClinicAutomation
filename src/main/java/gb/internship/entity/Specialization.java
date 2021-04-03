package gb.internship.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "Specialization")
public class Specialization {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "specialization")
    private Set<Doctor> doctors;

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

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }
}
