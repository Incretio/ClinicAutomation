package gb.internship.service;

import gb.internship.dto.ClientDto;
import gb.internship.repository.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ClientService {
    public List<ClientDto> getClients() {
        return new ClientRepository().getClients()
                                     .stream()
                                     .map(ClientDto::new)
                                     .collect(Collectors.toList());
    }
}
