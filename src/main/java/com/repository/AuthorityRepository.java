package com.repository;

import com.domain.Adminauthority;

import java.util.List;

public interface AuthorityRepository {

    public List<Adminauthority> getAll();

    public Adminauthority create(Adminauthority adminAuthority);

    public Adminauthority get(Long id);

    public Adminauthority update(Adminauthority adminAuthority);

    public void delete(Long id);
}
