package com.service;

import com.dto.ApiResponse;
import com.entity.Client;
import com.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public ApiResponse edit(Integer id, Client client) {
        Optional<Client> byId = clientRepository.findById(id);
        Client client1 = byId.get();

        client1.setName(client.getName());
        client1.setPhone_number(client.getPhone_number());

        clientRepository.save(client1);
        return new ApiResponse("Saved", true);
    }
}
