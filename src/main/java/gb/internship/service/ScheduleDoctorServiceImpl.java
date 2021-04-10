package gb.internship.service;

import gb.internship.dto.ScheduleDoctor;
import gb.internship.entity.Doctor;
import gb.internship.entity.TimeRangeToDoctor;
import gb.internship.repository.DoctorRepository;
import gb.internship.utils.TimeRangeHelper;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.util.*;

public class ScheduleDoctorServiceImpl implements ScheduleDoctorService {

    @Inject
    private DoctorRepository doctorRepository;

    public Map<String, List<Boolean>> takeScheduleDoctor(int doctorId) {
        Doctor doctor = doctorRepository.getDoctor(doctorId);
        Set<TimeRangeToDoctor> timeRangeToDoctors = doctor.getTimeRangeToDoctors();
        int now = TimeRangeHelper.toDaysPast(new Date());

        ScheduleDoctor scheduleDoctor = new ScheduleDoctor();
        int monday = now - (new DateTime().getDayOfWeek() - 1);
        for (int i = 0; i < 7; i++) {
            int dayNumber = monday + i;
            System.out.println(dayNumber);
            for (TimeRangeToDoctor timeRangeToDoctor : timeRangeToDoctors) {
                if (timeRangeToDoctor.getDateOfReceipt() == dayNumber) {
                    scheduleDoctor.add(i, timeRangeToDoctor);
                };
            }
        }
        scheduleDoctor.sort();

        Map<String, List<Boolean>> data = new LinkedHashMap<>();
        data.put("09:00 - 10:00", takeSchedule(scheduleDoctor, "09:00", "10:00"));
        data.put("10:00 - 11:00", takeSchedule(scheduleDoctor, "10:00", "11:00"));
        data.put("11:00 - 12:00", takeSchedule(scheduleDoctor, "11:00", "12:00"));
        data.put("12:00 - 13:00", takeSchedule(scheduleDoctor, "12:00", "13:00"));
        data.put("13:00 - 14:00", takeSchedule(scheduleDoctor, "13:00", "14:00"));
        data.put("14:00 - 15:00", takeSchedule(scheduleDoctor, "14:00", "15:00"));
        data.put("15:00 - 16:00", takeSchedule(scheduleDoctor, "15:00", "16:00"));
        data.put("16:00 - 17:00", takeSchedule(scheduleDoctor, "16:00", "17:00"));
        data.put("17:00 - 18:00", takeSchedule(scheduleDoctor, "17:00", "18:00"));
        data.put("18:00 - 19:00", takeSchedule(scheduleDoctor, "18:00", "19:00"));
        data.put("19:00 - 20:00", takeSchedule(scheduleDoctor, "19:00", "20:00"));
        data.put("20:00 - 21:00", takeSchedule(scheduleDoctor, "20:00", "21:00"));

        return data;
    }



    private List<Boolean> takeSchedule(ScheduleDoctor scheduleDoctor, String startTime, String stopTime ) {
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            result.add(scheduleDoctor.isWorkTime(i, startTime, stopTime));
        }
        return result;
    }
}
