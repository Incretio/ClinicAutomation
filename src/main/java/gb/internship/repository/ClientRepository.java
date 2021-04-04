package gb.internship.repository;

import gb.internship.entity.Client;

import java.util.List;

public interface ClientRepository {

    List<Client> getClients();
    void addOrUpdate(Client client);
    void delete(int clientId);

}
