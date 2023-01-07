package com.service;

import com.domain.Adminauthority;

import java.util.List;

public interface AuthorityService {

    public Adminauthority insert(Adminauthority adminAuthority);

    public Adminauthority get(Long id);

    public List<Adminauthority> getAll();

    public Adminauthority update(Adminauthority adminAuthority);

    public void delete(Long id);
}
