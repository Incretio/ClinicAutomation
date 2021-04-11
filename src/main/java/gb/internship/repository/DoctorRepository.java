package gb.internship.repository;

import gb.internship.entity.Doctor;
import gb.internship.entity.TimeRange;

import java.util.List;

public interface DoctorRepository {

    List<Doctor> getDoctors();
    Doctor getDoctor(int id);
    void saveOrUpdate(Doctor doctor);
    void delete(int doctorId);
    List<TimeRange> getAllTimeRange();
    void toggleScheduleDoctor(int doctorId, int timeRangeId, int dateOfReceipt);

}
