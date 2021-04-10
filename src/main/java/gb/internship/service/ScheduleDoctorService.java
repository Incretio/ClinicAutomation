package gb.internship.service;

import java.util.List;
import java.util.Map;

public interface ScheduleDoctorService {

    Map<String, List<Boolean>> takeScheduleDoctor(int doctorId, int weekOffset);

}
