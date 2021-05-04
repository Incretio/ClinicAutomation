package gb.internship.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gb.internship.entity.Doctor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorDto {
    private int id;
    private String name;
    private String secondName;
    private String patronymic;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date dateOfEmployment;
    private int specialization;

    public DoctorDto(Doctor doctor) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.secondName = doctor.getSecondName();
        this.patronymic = doctor.getPatronymic();
        this.dateOfEmployment = (doctor.getDateOfEmployment() == null) ? new Date() : doctor.getDateOfEmployment();
        this.specialization = doctor.getSpecialization();
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

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public int getSpecialization() {
        return specialization;
    }

    public void setSpecialization(int specialization) {
        this.specialization = specialization;
    }

    // format: dd-MM-yyyy
    public void setDateOfEmployment(String dateValue) throws ParseException {
        this.dateOfEmployment = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
    }

    // format: dd-MM-yyyy
    public String getDateOfEmploymentIso() throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").format(dateOfEmployment);
    }

}

