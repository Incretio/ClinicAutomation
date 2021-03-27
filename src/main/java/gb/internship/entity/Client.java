package gb.internship.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "Client")
public class Client {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(mappedBy = "client")
    private Set<Invoice> invoices = new HashSet<>();
    private String name;
    private String secondName;
    private String patronymic;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private char sex;

    public enum Sex {
        MALE, FEMALE
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex == 'm' ? Sex.MALE : Sex.FEMALE;
    }

    public void setSex(Sex sex) {
        this.sex = sex == Sex.MALE ? 'm' : 'f';
    }
}

