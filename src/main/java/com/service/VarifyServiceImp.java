package com.service;


import com.domain.Registration;
import com.domain.Sellervarify;
import com.repository.VarifyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VarifyServiceImp implements  VarifyService{

    private VarifyRepository varifyRepository;

    public VarifyServiceImp(VarifyRepository varifyRepository) {
        this.varifyRepository = varifyRepository;
    }

    @Transactional
    public Registration insert(Registration registration) {
        return varifyRepository.create(registration);
    }

    @Transactional(readOnly = true)
    public Registration get(Long id) {
        return varifyRepository.get(id);
    }

    @Transactional(readOnly = true)
    public List<Registration> getAll() {
        return varifyRepository.getAll();
    }

    @Transactional
    public Registration update(Registration registration) {
        return varifyRepository.update(registration);
    }

    @Transactional
    public void delete(Long id) {
        varifyRepository.delete(id);
    }
}
