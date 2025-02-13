package com.rishith.firstjobapp.job;


import org.springframework.stereotype.Service;

import java.util.List;
//Interfaces should NOT have @Service annotations, only their implementations should.
//it has mehtods defined , it says whoever implements this , avru en en implement madbeku antha
public interface  JobService {

    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    Job updateJob(Long id, Job updatedJob);
}
