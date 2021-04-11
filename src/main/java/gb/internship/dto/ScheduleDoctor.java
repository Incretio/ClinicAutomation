package gb.internship.dto;

import gb.internship.entity.Doctor;
import gb.internship.entity.TimeRangeToDoctor;

import java.util.*;

public class ScheduleDoctor {

    private Doctor doctor;
    private Map<Integer, List<TimeRangeToDoctor>> data = new TreeMap<>();

    public void add(int dayOfWeek, TimeRangeToDoctor timeRangeToDoctor) {
        List<TimeRangeToDoctor> currentValue = data.getOrDefault(dayOfWeek, new ArrayList<>());
        currentValue.add(timeRangeToDoctor);
        data.put(dayOfWeek, currentValue);
        if (dayOfWeek < 0 || dayOfWeek > 6) {
            throw new RuntimeException(String.format("Bad number day of week (%d)", dayOfWeek));
        }
    }

    public void sort() {
        data.values().forEach(day -> day.sort(Comparator.comparing(o -> o.getTimeRange().getStart())));
    }

    public boolean isWorkTime(int dayOfWeek, String startTime, String stopTime) {
        if (!data.containsKey(dayOfWeek)) {
            return false;
        }
        for (TimeRangeToDoctor timeRangeToDoctor : data.get(dayOfWeek)) {
            if (timeRangeToDoctor.getTimeRange().getStart().equals(startTime) &&
                timeRangeToDoctor.getTimeRange().getStop().equals(stopTime)) {
                return true;
            }
        }
        return false;
    }
}
