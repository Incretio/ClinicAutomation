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
    public void addDoctor(Doctor doctor) {
        Session session = null;
        try {
            session = repository.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(doctor);
            transaction.commit();
        } finally {
            if (session != null) session.close();
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
