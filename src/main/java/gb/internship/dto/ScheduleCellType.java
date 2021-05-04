package gb.internship.dto;

import gb.internship.entity.Client;
import gb.internship.entity.ScheduleRecord;
import gb.internship.entity.TimeRangeToDoctor;

import java.util.Collection;

public enum ScheduleCellType {

    MY_RECORD, ANY_RECORD, FREE_RECORD, NOT_WORK;

    public static ScheduleCellType defineType(
            Collection<TimeRangeToDoctor> timeRangeToDoctorList,
            Collection<ScheduleRecord> scheduleRecordList,
            Integer timeRangeId,
            ScheduleCell scheduleCell,
            Client client) {
        if (currentClient(timeRangeId, scheduleCell, client)) {
            return MY_RECORD;
        }
        if (anyClient(scheduleRecordList, timeRangeId, scheduleCell)) {
            return ANY_RECORD;
        }
        if (notRecorded(timeRangeToDoctorList, timeRangeId, scheduleCell)) {
            return FREE_RECORD;
        }

        return NOT_WORK;
    }

    private static boolean currentClient(
            Integer timeRangeId,
            ScheduleCell scheduleCell,
            Client client) {
        for (ScheduleRecord scheduleRecord : client.getScheduleRecords()) {
            TimeRangeToDoctor timeRangeToDoctor = scheduleRecord.getTimeRangeToDoctor();
            if (timeRangeToDoctor.getTimeRange().getId() == timeRangeId &&
                timeRangeToDoctor.getDateOfReceipt() == scheduleCell.getDateOfReceipt() &&
                timeRangeToDoctor.getDoctor().getId() == scheduleCell.getDoctor().getId()) {
                return true;
            }
        }
        return false;
    }

    private static boolean anyClient(Collection<ScheduleRecord> scheduleRecordList, Integer timeRangeId, ScheduleCell scheduleCell) {
        for (ScheduleRecord scheduleRecord : scheduleRecordList) {
            if (timeRangeId == scheduleRecord.getTimeRangeToDoctor().getTimeRange().getId() &&
                scheduleRecord.getTimeRangeToDoctor().getDateOfReceipt() == scheduleCell.getDateOfReceipt() &&
                scheduleRecord.getTimeRangeToDoctor().getDoctor().getId() == scheduleCell.getDoctor().getId()) {
                return true;
            }
        }
        return false;
    }

    private static boolean notRecorded(Collection<TimeRangeToDoctor> timeRangeToDoctorList, Integer timeRangeId, ScheduleCell scheduleCell) {
        for (TimeRangeToDoctor timeRangeToDoctor : timeRangeToDoctorList) {
            if (timeRangeToDoctor.getTimeRange().getId() == timeRangeId &&
                timeRangeToDoctor.getDateOfReceipt() == scheduleCell.getDateOfReceipt()) {
                return true;
            }
        }
        return false;
    }

}
