package com.rishith.firstjobapp.company.impl;

import com.rishith.firstjobapp.company.Company;
import com.rishith.firstjobapp.company.CompanyRepository;
import com.rishith.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {


    private final CompanyRepository CompanyRepository;
    private final CompanyRepository companyRepository;


    public CompanyServiceImpl(CompanyRepository CompanyRepository, CompanyRepository companyRepository) {
        this.CompanyRepository = CompanyRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return CompanyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = CompanyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setName(company.getName());
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setJobs(company.getJobs());
            CompanyRepository.save(companyToUpdate);
            return true;

        } else {
            return false;
        }
    }

    @Override
    public void createCompany(Company company) {
        CompanyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if (CompanyRepository.existsById(id)) {
            CompanyRepository.deleteById(id);
            return true;

        } else {
            return false;
        }

    }

    @Override
    public Company getCompanyById(Long id) {
        return CompanyRepository.findById(id).orElse(null);
    }
}
