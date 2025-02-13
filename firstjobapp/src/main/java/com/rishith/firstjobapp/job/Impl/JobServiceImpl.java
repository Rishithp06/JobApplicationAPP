package com.rishith.firstjobapp.job.Impl;

import com.rishith.firstjobapp.job.Job;
import com.rishith.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Spring Boot can only inject a class if it's marked as a bean (@Service).
//Add @Service to JobServiceImpl so Spring Boot can detect and register it.
//if you are implementing an interface , it is compulsory to implement al the methods
@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();

    @Override
    public List<Job> findAll() {

        return jobs;
    }

    @Override
    public void createJob(Job job) {
    jobs.add(job);
    }
}
