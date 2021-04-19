package gb.internship.dto;

import gb.internship.entity.Doctor;
import gb.internship.entity.Specialization;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class DoctorDtoTest {

    private DateTime testDateTime;
    private DoctorDto testDoctorDto;
    private final String TEST = "Test";

    @BeforeEach
    void setUp() {
        testDateTime = new DateTime(2000, 1, 1, 0, 0);

        Doctor doctor = new Doctor();
        doctor.setId(0);
        doctor.setPatronymic("");
        doctor.setName("");
        doctor.setSecondName("");
        Specialization spec = new Specialization();
        spec.setId(0);
        doctor.setSpecialization(spec);
        testDoctorDto = new DoctorDto(doctor);
    }

    @Test
    void setId() {
        testDoctorDto.setId(1);
        Assertions.assertEquals(1, testDoctorDto.getId());
    }

    @Test
    void setName() {
        testDoctorDto.setName(TEST);
        Assertions.assertEquals(TEST, testDoctorDto.getName());
    }

    @Test
    void setSecondName() {
        testDoctorDto.setSecondName(TEST);
        Assertions.assertEquals(TEST, testDoctorDto.getSecondName());
    }

    @Test
    void setPatronymic() {
        testDoctorDto.setPatronymic(TEST);
        Assertions.assertEquals(TEST, testDoctorDto.getPatronymic());
    }

    @Test
    void setDateOfEmployment() throws ParseException {
        testDoctorDto.setDateOfEmployment(testDateTime.toDate());
        Assertions.assertEquals(testDateTime.toDate(), testDoctorDto.getDateOfEmployment());

        String testDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd").format(testDateTime.toDate());
        testDoctorDto.setDateOfEmployment(testDateTimeFormat);
        Assertions.assertEquals(testDateTimeFormat, testDoctorDto.getDateOfEmploymentIso());
    }

    @Test
    void setSpecialization() {
        testDoctorDto.setSpecialization(1);
        Assertions.assertEquals(1, testDoctorDto.getSpecialization());
    }
}