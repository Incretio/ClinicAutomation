package gb.internship.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gb.internship.entity.Client;
import gb.internship.entity.Sex;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientDto {
    private int id;
    private String name;
    private String secondName;
    private String patronymic;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date birthDate;
    private Sex sex;

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.secondName = client.getSecondName();
        this.patronymic = client.getPatronymic();
        this.birthDate = client.getBirthDate();
        this.sex = client.getSex();
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

    // format: dd-MM-yyyy
    public String getBirthDateIso() throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").format(birthDate);
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}

