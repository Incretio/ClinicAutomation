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

    public List<Doctor> getDoctors() {
        Session session = repository.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Doctor> query = session.createQuery("from Doctor", Doctor.class); //You will get Weayher object
        List<Doctor> result = query.list();
        transaction.commit();
        return result;
    }

}
