package gb.internship.service;

import gb.internship.dto.DoctorDto;
import gb.internship.entity.Doctor;

import java.util.Date;
import java.util.List;

public interface DoctorService {

    List<DoctorDto> getDoctors();
    Doctor getDoctor(int id);
    Doctor getZeroDoctor();
    void saveOrUpdate(int id, String name, String  secondName, String patronymic, Date dateOfEmployment, int specialization);
    void delete(int doctorId);
}
