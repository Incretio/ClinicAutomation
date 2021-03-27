package gb.internship.repository;

import gb.internship.entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClientRepository {

    public List<Client> getClients() {
        Session session = new Repository().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Client> query = session.createQuery("from Client", Client.class); //You will get Weayher object
        List<Client> result = query.list();
        transaction.commit();
        return result;
    }

}
