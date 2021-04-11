package gb.internship.service;

import gb.internship.entity.TimeRange;

import java.util.List;
import java.util.Map;

public interface ScheduleDoctorService {

    Map<TimeRange, List<Boolean>> takeScheduleDoctor(int doctorId, int weekOffset);
    void toggleCellScheduleDoctor(int doctorId, int timeRangeId, int dateOfReceipt);

}
