package com.repository;

import com.domain.Adminuser;

import java.util.List;

public interface UserRepository {

    public List<Adminuser> getAll();

    public Adminuser create(Adminuser adminUser);

    public Adminuser get(Long id);

    public Adminuser update(Adminuser adminUser);

    public void delete(Long id);

    public Adminuser getByUsername(String username);
    public Adminuser updatepassword(Adminuser adminUser);

}
