package gb.internship.service;

import gb.internship.dto.ScheduleCell;
import gb.internship.entity.Client;
import gb.internship.entity.Doctor;
import gb.internship.entity.TimeRange;

import java.util.List;
import java.util.Map;

public interface ScheduleService {

    List<Map<Integer, ScheduleCell>> takeSchedule(Client client, int dateOfReceipt);
    List<TimeRange> takeAllTimeRange();
    List<Doctor> takeAllDoctors();

}
