package com.rishith.firstjobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }



    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        boolean updated = companyService.updateCompany(company, id);
        if (updated) {
            return ResponseEntity.ok("Updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
    }
    @PostMapping
    public ResponseEntity<String> daddCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean idDeleted = companyService.deleteCompanyById(id);
        if (idDeleted) {
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
    }
@GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
     Company company = companyService.getCompanyById(id);
     if (company != null) {
         return new ResponseEntity<>(company, HttpStatus.OK);
     }
     else {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

    }
}
