package com.switchfully.jsonbourne.domain.repository.admin;

import com.switchfully.jsonbourne.domain.models.member.Admin;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Repository
public class LocalAdminRepository implements AdminRepository {

    private final Set<Admin> admins = new HashSet<>();


    public LocalAdminRepository() {
       this.init();
    }

    private void init(){
        admins.add(new Admin(UUID.fromString("d774eceb-03c5-4f63-9e92-aa1025b2257f"),"","","admin@digibooky.be"));
    }

    @Override
    public boolean isAdmin(String uuuid) {
        return admins.stream().filter(admin -> admin.getId().toString().equals(uuuid)).count() > 0;
    }
}
