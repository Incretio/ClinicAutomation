package gb.internship.repository;

import gb.internship.dto.DoctorDto;
import gb.internship.entity.Doctor;
import gb.internship.entity.ScheduleRecord;
import gb.internship.entity.TimeRange;
import gb.internship.entity.TimeRangeToDoctor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class DoctorRepositoryImpl implements DoctorRepository {

    @Inject
    private Repository repository;

    @Override
    public List<Doctor> getDoctors() {
        try (Session session = repository.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Doctor> query = session.createQuery("from Doctor", Doctor.class); //You will get Weayher object
            List<Doctor> result = query.list();
            result.forEach(doctor -> Hibernate.initialize(doctor.getTimeRangeToDoctors()));
            transaction.commit();
            return result;
        }
    }

    @Override
    public Doctor getDoctor(int id) {
        try (Session session = repository.openSession()) {
            Doctor doctor = session.get(Doctor.class, id);
            doctor.getTimeRangeToDoctors().size();
            return doctor;
        }
    }

    @Override
    public void saveOrUpdate(Doctor doctor) {
        try (Session session = repository.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(doctor);
            transaction.commit();
        }
    }

    @Override
    public void delete(int doctorId) {
        try (Session session = repository.openSession()) {
            Transaction transaction = session.beginTransaction();
            Doctor doctor = session.load(Doctor.class, doctorId);
            session.delete(doctor);
            transaction.commit();
        }
    }

    @Override
    public List<TimeRange> getAllTimeRange() {
        try (Session session = repository.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<TimeRange> query = session.createQuery("from TimeRange", TimeRange.class); //You will get Weayher object
            List<TimeRange> result = query.list();
            transaction.commit();
            return result;
        }
    }

    @Override
    public void toggleScheduleDoctor(int doctorId, int timeRangeId, int dateOfReceipt) {
        List<TimeRangeToDoctor> timeRangeToDoctorList = getTimeRangeToDoctor(doctorId, timeRangeId, dateOfReceipt);
        if (timeRangeToDoctorList.isEmpty()) {
            saveOrUpdateTimeRangeToDoctor(doctorId, timeRangeId, dateOfReceipt);
        } else {
            deleteTimeRangeToDoctor(timeRangeToDoctorList.get(0).getId());
        }
    }

    @Override
    public List<TimeRangeToDoctor> getAllTimeRangeToDoctor() {
        try (Session session = repository.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<TimeRangeToDoctor> query = session.createQuery("from TimeRangeToDoctor", TimeRangeToDoctor.class); //You will get Weayher object
            List<TimeRangeToDoctor> result = query.list();
            transaction.commit();
            return result;
        }
    }

    @Override
    public List<ScheduleRecord> getAllScheduleRecord() {
        try (Session session = repository.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<ScheduleRecord> query = session.createQuery("from ScheduleRecord", ScheduleRecord.class); //You will get Weayher object
            List<ScheduleRecord> result = query.list();
            transaction.commit();
            return result;
        }
    }

    private void saveOrUpdateTimeRangeToDoctor(int doctorId, int timeRangeId, int dateOfReceipt) {
        try (Session session = repository.openSession()) {
            Transaction transaction = session.beginTransaction();
            Doctor doctor = session.load(Doctor.class, doctorId);
            TimeRange timeRange = session.load(TimeRange.class, timeRangeId);
            TimeRangeToDoctor timeRangeToDoctor = new TimeRangeToDoctor();
            timeRangeToDoctor.setDoctor(doctor);
            timeRangeToDoctor.setTimeRange(timeRange);
            timeRangeToDoctor.setDateOfReceipt(dateOfReceipt);
            session.saveOrUpdate(timeRangeToDoctor);
            transaction.commit();
        }
    }

    private List<TimeRangeToDoctor> getTimeRangeToDoctor(int doctorId, int timeRangeId, int dateOfReceipt) {
        try (Session session = repository.openSession()) {
            Query<TimeRangeToDoctor> query = session.createQuery(
                "from TimeRangeToDoctor where doctorId = :doctorId and timeRangeId = :timeRangeId and dateOfReceipt = :dateOfReceipt",
                TimeRangeToDoctor.class);
            query.setParameter("doctorId", doctorId);
            query.setParameter("timeRangeId", timeRangeId);
            query.setParameter("dateOfReceipt", dateOfReceipt);
            return query.list();
        }
    }

    private void deleteTimeRangeToDoctor(int id) {
        try (Session session = repository.openSession()) {
            Transaction transaction = session.beginTransaction();
            TimeRangeToDoctor timeRangeToDoctor = session.load(TimeRangeToDoctor.class, id);
            session.delete(timeRangeToDoctor);
            transaction.commit();
        }
    }

}
