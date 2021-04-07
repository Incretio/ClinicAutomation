package gb.internship.service;

import gb.internship.dto.ClientDto;
import gb.internship.entity.Client;

import java.util.List;

public interface ClientService {

    List<ClientDto> getClients();
    Client getClient(int id);
    void saveOrUpdate(int id, String name, String  secondName, String patronymic, String birthDate, String sex);
    void delete(int clientId);

}
