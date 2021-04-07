package gb.internship.repository;

import gb.internship.entity.Doctor;

import java.util.List;

public interface DoctorRepository {

    List<Doctor> getDoctors();
    Doctor getDoctor(int id);
    void saveOrUpdate(Doctor doctor);
    void delete(int doctorId);

}
