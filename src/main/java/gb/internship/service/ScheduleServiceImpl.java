package gb.internship.service;

import gb.internship.dto.ScheduleCell;
import gb.internship.dto.ScheduleCellType;
import gb.internship.entity.*;
import gb.internship.repository.DoctorRepository;
import org.glassfish.jersey.process.internal.RequestScoped;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@RequestScoped
public class ScheduleServiceImpl implements ScheduleService {

    @Inject
    private DoctorRepository doctorRepository;

    @Override
    public List<Map<Integer, ScheduleCell>> takeSchedule(Client client, int dateOfReceipt) {
        List<Map<Integer, ScheduleCell>> scheduleByDay = takeDoctorScheduleByDay(dateOfReceipt);
        fillScheduleCellType(scheduleByDay, client);
        return scheduleByDay;
    }

    public List<TimeRange> takeAllTimeRange() {
        return doctorRepository.getAllTimeRange();
    }

    @Override
    public List<Doctor> takeAllDoctors() {
        return doctorRepository.getDoctors();
    }

    private void fillScheduleCellType(List<Map<Integer, ScheduleCell>> scheduleByDay, Client client) {
        List<ScheduleRecord> allScheduleRecord = doctorRepository.getAllScheduleRecord();

        for (Map<Integer, ScheduleCell> timeRangeScheduleCellMap : scheduleByDay) {
            for (Map.Entry<Integer, ScheduleCell> timeRangeScheduleCellEntry : timeRangeScheduleCellMap.entrySet()) {
                Integer timeRangeId = timeRangeScheduleCellEntry.getKey();
                ScheduleCell scheduleCell = timeRangeScheduleCellEntry.getValue();
                ScheduleCellType scheduleCellType = ScheduleCellType.defineType(
                    scheduleCell.getDoctor().getTimeRangeToDoctors(), allScheduleRecord, timeRangeId, scheduleCell, client);
                timeRangeScheduleCellEntry.getValue().setScheduleCellType(scheduleCellType);
            }
        }
    }

    private List<Map<Integer, ScheduleCell>> takeDoctorScheduleByDay(int daysAfter2000) {
        List<Map<Integer, ScheduleCell>> result = new ArrayList<>();
        for (Doctor doctor : takeDoctorsByDay(daysAfter2000)) {
            Map<Integer, ScheduleCell> data = new LinkedHashMap<>();
            for (TimeRange timeRange : doctorRepository.getAllTimeRange()) {
                ScheduleCell scheduleCell = new ScheduleCell();
                scheduleCell.setDoctor(doctor);
                scheduleCell.setDateOfReceipt(daysAfter2000);
                // На этом этапе тип записи ещё не известен, так как он напрямую связан с клиентом
                // scheduleCell.setScheduleCellType();
                data.put(timeRange.getId(), scheduleCell);
            }
            result.add(data);
        }
        return result;
    }

    // Получаем список врачей. Фильтруем расписание на день daysAfter2000
    private List<Doctor> takeDoctorsByDay(int daysAfter2000) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor doctor : doctorRepository.getDoctors()) {
            Set<TimeRangeToDoctor> currentTimeRangeList =
                doctor.getTimeRangeToDoctors().stream()
                    .filter(timeRangeToDoctor -> timeRangeToDoctor.getDateOfReceipt() == daysAfter2000)
                    .collect(Collectors.toSet());
            doctor.setTimeRangeToDoctors(currentTimeRangeList);
            result.add(doctor);
        }
        return result;
    }

}
