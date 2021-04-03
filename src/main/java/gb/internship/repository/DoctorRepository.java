package gb.internship.repository;

import gb.internship.entity.Doctor;

import java.util.List;

public interface DoctorRepository {

    List<Doctor> getDoctors();
    void delete(int doctorId);

}
