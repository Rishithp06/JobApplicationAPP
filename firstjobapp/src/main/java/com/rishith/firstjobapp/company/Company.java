package com.rishith.firstjobapp.company;


import com.rishith.firstjobapp.job.Job;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Company {
    private Long id;
    private String name;
    private String description;

    @OneToMany
    //every company has a job so map it
    private List<Job> jobs;

}
