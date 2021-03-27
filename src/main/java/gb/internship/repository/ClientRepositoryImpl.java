package gb.internship.repository;

import gb.internship.entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ClientRepositoryImpl implements ClientRepository {

    @Inject
    private Repository repository;

    public List<Client> getClients() {
        Session session = repository.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Client> query = session.createQuery("from Client", Client.class); //You will get Weayher object
        List<Client> result = query.list();
        transaction.commit();
        return result;
    }

}
