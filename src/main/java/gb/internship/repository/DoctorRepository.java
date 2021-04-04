package gb.internship.repository;

import gb.internship.entity.Client;
import gb.internship.entity.Doctor;

import java.util.List;

public interface DoctorRepository {

    List<Doctor> getDoctors();
    void addDoctor(Doctor doctor);
    void delete(int doctorId);

}
