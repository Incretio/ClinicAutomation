package gb.internship.service;

import gb.internship.dto.ScheduleDoctor;
import gb.internship.entity.Doctor;
import gb.internship.entity.TimeRange;
import gb.internship.entity.TimeRangeToDoctor;
import gb.internship.repository.DoctorRepository;
import gb.internship.utils.TimeRangeHelper;

import javax.inject.Inject;
import java.util.*;

public class ScheduleDoctorServiceImpl implements ScheduleDoctorService {

    @Inject
    private DoctorRepository doctorRepository;

    public Map<TimeRange, List<Boolean>> takeScheduleDoctor(int doctorId, int weekOffset) {
        Doctor doctor = doctorRepository.getDoctor(doctorId);
        Set<TimeRangeToDoctor> timeRangeToDoctors = doctor.getTimeRangeToDoctors();

        ScheduleDoctor scheduleDoctor = new ScheduleDoctor();
        int monday = TimeRangeHelper.takeMonday(weekOffset);
        for (int i = 0; i < 7; i++) {
            int dayNumber = monday + i;
            for (TimeRangeToDoctor timeRangeToDoctor : timeRangeToDoctors) {
                if (timeRangeToDoctor.getDateOfReceipt() == dayNumber) {
                    scheduleDoctor.add(i, timeRangeToDoctor);
                };
            }
        }
        scheduleDoctor.sort();

        Map<TimeRange, List<Boolean>> data = new LinkedHashMap<>();
        List<TimeRange> allTimeRange = doctorRepository.getAllTimeRange();
        for (TimeRange timeRange : allTimeRange) {
            data.put(timeRange,
                     takeSchedule(scheduleDoctor, timeRange.getStart(), timeRange.getStop()));
        }

        return data;
    }

    @Override
    public void toggleCellScheduleDoctor(int doctorId, int timeRangeId, int dateOfReceipt) {
        doctorRepository.toggleScheduleDoctor(doctorId, timeRangeId, dateOfReceipt);
    }

    private List<Boolean> takeSchedule(ScheduleDoctor scheduleDoctor, String startTime, String stopTime ) {
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            result.add(scheduleDoctor.isWorkTime(i, startTime, stopTime));
        }
        return result;
    }

}
