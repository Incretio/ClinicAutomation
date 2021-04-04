package gb.internship.service;

import gb.internship.dto.DoctorDto;

import java.util.List;

public interface DoctorService {

    List<DoctorDto> getDoctors();
    void setDoctor(String name, String  secondName, String patronymic, String dateOfEmployment, String specialization);
    void delete(int doctorId);
}
