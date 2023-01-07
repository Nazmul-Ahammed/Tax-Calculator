package com.service;



import com.domain.Registration;
import com.domain.Sellervarify;

import java.util.List;

public interface VarifyService {

    public Registration insert(Registration registration);

    public Registration get(Long id);

    public List<Registration> getAll();

    public Registration update(Registration registration);

    public void delete(Long id);
}
