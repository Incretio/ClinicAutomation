package gb.internship.repository;

import gb.internship.entity.Doctor;
import gb.internship.entity.Specialization;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;


@Singleton
public class SpecializationRepositoryImpl implements SpecializationRepository{

    @Inject
    private Repository repository;

    @Override
    public List<Specialization> getSpecializations() {
        try (Session session = repository.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Specialization> query = session.createQuery("from Specialization", Specialization.class); //You will get Weayher object
            List<Specialization> result = query.list();
            transaction.commit();
            return result;
        }
    }
}