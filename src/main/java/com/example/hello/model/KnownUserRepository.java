package com.example.hello.model;

import com.googlecode.objectify.ObjectifyService;

import java.util.List;

public class KnownUserRepository {

    public void saveKnownUser(KnownUser user) {
        ObjectifyService.run(() -> {
            ObjectifyService.ofy().save().entity(user).now();
            return null;
        });
    }

    public KnownUser getKnownUser(Long id) {
        return ObjectifyService.run(() -> ObjectifyService.ofy().load().type(KnownUser.class).id(id).now());
    }

    public List<KnownUser> getAllKnownUsers() {
        return ObjectifyService.run(() -> ObjectifyService.ofy().load().type(KnownUser.class).list());
    }

    public void deleteKnownUser(Long id) {
        ObjectifyService.run(() -> {
            ObjectifyService.ofy().delete().type(KnownUser.class).id(id).now();
            return null;
        });
    }
}
