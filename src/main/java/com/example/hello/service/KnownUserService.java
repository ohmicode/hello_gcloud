package com.example.hello.service;

import com.example.hello.model.KnownUser;
import com.example.hello.model.KnownUserRepository;

import java.util.List;

public class KnownUserService {
    private final KnownUserRepository repository = new KnownUserRepository();

    public void appendUser(String name) {
        KnownUser entity = new KnownUser();
        entity.setName(name != null ? name : "Anonymous");
        repository.saveKnownUser(entity);
    }

    public List<KnownUser> getUsers() {
        return repository.getAllKnownUsers();
    }
}
