package gb.internship.repository;

import gb.internship.entity.Client;
import gb.internship.entity.Doctor;
import gb.internship.entity.Sex;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Singleton
public class ClientRepositoryImpl implements ClientRepository {

    @Inject
    private Repository repository;

    @Override
    public List<Client> getClients() {
        Session session = repository.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Client> query = session.createQuery("FROM Client", Client.class); //You will get Weayher object
        List<Client> result = query.list();
        transaction.commit();
        return result;
    }

    @Override
    public Client getClient(int id) {
        Session session = repository.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Client> query = session.createQuery("FROM Client where id=:id", Client.class); //You will get Weayher object
        query.setParameter("id", id);
        Client result = query.getSingleResult();
        transaction.commit();
        return result;
    }

    @Override
    public void addOrUpdate(Client client) {
        Session session = null;
        try {
            session = repository.openSession();
            Transaction transaction = session.beginTransaction();
            if (client.getId() == 0){
                session.save(client);
            }
            else {
                session.update(client);
            }
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(int clientId) {
        try (Session session = repository.openSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = session.load(Client.class, clientId);
            session.delete(client);
            transaction.commit();
        }
    }



}
