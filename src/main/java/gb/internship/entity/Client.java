package gb.internship.entity;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "Client")
public class Client {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "client")
    private Set<Invoice> invoices = new HashSet<>();
    private String name;
    private String secondName;
    private String patronymic;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private char sex;

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

    public void setBirthDate(String birthDate) {
        this.birthDate = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.birthDate = formater.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Sex getSex() {
        return sex == 'm' ? Sex.MALE : Sex.FEMALE;
    }

    public void setSex(String sex) {
        this.sex = sex.equals("MALE")  ? 'm' : 'f';
    }
}

