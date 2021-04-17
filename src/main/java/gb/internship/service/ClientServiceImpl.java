package gb.internship.service;

import gb.internship.dto.ClientDto;
import gb.internship.entity.Client;
import gb.internship.repository.ClientRepository;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ClientServiceImpl implements ClientService {

    private Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Inject
    private ClientRepository clientRepository;

    public List<ClientDto> getClients() {
        return clientRepository.getClients().stream()
                .map(ClientDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Client getClient(int id) {
        return clientRepository.getClient(id);
    }
    @Override
    public Client getZeroClient() {
        Client client = new Client();
        client.setId(0);
        client.setPatronymic("");
        client.setName("");
        client.setSecondName("");
        client.setBirthDate(new Date());
        client.setSex("MALE");
        return client;
    }

    @Override
    public void saveOrUpdate(int id, String name, String secondName, String patronymic, Date birthDate, String sex) {
        Client client = new Client();
        client.setId(id);
        client.setName(name);
        client.setSecondName(secondName);
        client.setPatronymic(patronymic);
        client.setBirthDate(birthDate);
        client.setSex(sex);
        clientRepository.saveOrUpdate(client);
    }

    @Override
    public void delete(int clientId) {
        try {
            clientRepository.delete(clientId);
        } catch (EntityNotFoundException e) {
            logger.error("Client by Id ERROR", e);
        }
    }

}


