package gb.internship.repository;

import gb.internship.entity.Client;

import java.util.List;

public interface ClientRepository {

    List<Client> getClients();
    void setClients(String name, String  secondName, String patronymic, String birthDate, String sex);

}
