package com.repository;

import com.domain.Adminuser;
import com.domain.Registration;
import com.domain.Sellervarify;

import java.util.List;

public interface VarifyRepository {

    public List<Registration> getAll();

    public Registration create(Registration registration);

    public Registration get(Long id);

    public Registration update(Registration registration);

    public void delete(Long id);



}
