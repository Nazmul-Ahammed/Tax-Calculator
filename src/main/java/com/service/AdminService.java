package com.service;

import com.domain.Variables;
import com.repository.TaxCalcuRepository;
import com.repository.TaxRateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Service
@Transactional
public class AdminService {
    private TaxRateRepository taxRateRepository;
    private TaxCalcuRepository taxCalcuRepository;

    private DataSource dataSource;
    public AdminService(TaxCalcuRepository taxCalcuRepository,TaxRateRepository taxRateRepository) {
        this.taxCalcuRepository=taxCalcuRepository;
        this.taxRateRepository = taxRateRepository;
    }
    @Transactional(readOnly = true)
    public List<Variables> get() {
        return taxCalcuRepository.getAll();
    }
    @Transactional
    public Variables update(Variables variables) {
        return taxCalcuRepository.update(variables);
    }
    @Transactional
    public void delete(Long id) {
        taxCalcuRepository.delete(id);
    }
    @Transactional(readOnly = true)
    public Variables getid(Long id) {
        return taxCalcuRepository.get(id);
    }
}
