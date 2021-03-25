package com.switchfully.jsonbourne.service.adminService;

import com.switchfully.jsonbourne.domain.repository.admin.AdminRepository;
import com.switchfully.jsonbourne.infrastructure.exceptions.NotAuthorizedException;
import org.springframework.stereotype.Service;

@Service
public class DefaultAdminService implements AdminService {

    private final AdminRepository adminRepository;

    public DefaultAdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public boolean isAdmin(String uuid) {
        if (!adminRepository.isAdmin(uuid)) {
            throw new NotAuthorizedException("you are not authorized");
        }
        return true;
    }
}
