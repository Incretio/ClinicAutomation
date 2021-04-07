package gb.internship.repository;

import gb.internship.entity.Doctor;
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
            transaction.commit();
            return result;
        }
    }

    @Override
    public Doctor getDoctor(int id) {
        Session session = repository.openSession();
        return session.get(Doctor.class, id);
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

}
