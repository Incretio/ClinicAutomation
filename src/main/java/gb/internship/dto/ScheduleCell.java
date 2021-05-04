package gb.internship.dto;

import gb.internship.entity.Doctor;

public class ScheduleCell {
    private Doctor doctor;
    private int dateOfReceipt;
    private ScheduleCellType scheduleCellType;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(int dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public ScheduleCellType getScheduleCellType() {
        return scheduleCellType;
    }

    public void setScheduleCellType(ScheduleCellType scheduleCellType) {
        this.scheduleCellType = scheduleCellType;
    }
}
