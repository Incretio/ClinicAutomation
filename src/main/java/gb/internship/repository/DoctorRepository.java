package gb.internship.repository;

import gb.internship.dto.DoctorDto;
import gb.internship.entity.Doctor;
import gb.internship.entity.ScheduleRecord;
import gb.internship.entity.TimeRange;
import gb.internship.entity.TimeRangeToDoctor;

import java.util.List;

public interface DoctorRepository {

    List<Doctor> getDoctors();
    Doctor getDoctor(int id);
    void saveOrUpdate(Doctor doctor);
    void delete(int doctorId);
    List<TimeRange> getAllTimeRange();
    void toggleScheduleDoctor(int doctorId, int timeRangeId, int dateOfReceipt);
    List<TimeRangeToDoctor> getAllTimeRangeToDoctor();
    List<ScheduleRecord> getAllScheduleRecord();

}
