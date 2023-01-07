package com.service;

import com.domain.Adminauthority;
import com.repository.AuthorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorityImplService implements AuthorityService {

    private AuthorityRepository authorityRepository;

    public AuthorityImplService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    public Adminauthority insert(Adminauthority adminAuthority) {
        return authorityRepository.create(adminAuthority);
    }

    @Transactional(readOnly = true)
    public Adminauthority get(Long id) {
        return authorityRepository.get(id);
    }

    @Transactional(readOnly = true)
    public List<Adminauthority> getAll() {
        return authorityRepository.getAll();
    }

    @Transactional
    public Adminauthority update(Adminauthority adminAuthority) {
        return authorityRepository.update(adminAuthority);
    }

    @Transactional
    public void delete(Long id) {
        authorityRepository.delete(id);
    }
}
