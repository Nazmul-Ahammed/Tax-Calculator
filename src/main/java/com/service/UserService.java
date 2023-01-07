package com.service;

import com.domain.Adminuser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public Adminuser insert(Adminuser adminUser);

    public Adminuser get(Long id);

    public List<Adminuser> getAll();

    public Adminuser update(Adminuser adminUser);

    public void delete(Long id);

    public Adminuser getByUsername(String username);
    public Adminuser updatepassword(Adminuser adminUser);

}
