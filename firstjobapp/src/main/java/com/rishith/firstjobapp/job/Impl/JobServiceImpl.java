package com.rishith.firstjobapp.job.Impl;

import com.rishith.firstjobapp.job.Job;
import com.rishith.firstjobapp.job.JobRepository;
import com.rishith.firstjobapp.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Spring Boot can only inject a class if it's marked as a bean (@Service).
//Add @Service to JobServiceImpl so Spring Boot can detect and register it.
//if you are implementing an interface , it is compulsory to implement al the methods
@Service
public class JobServiceImpl implements JobService {

    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    //When you use Spring Boot and JPA, you don’t have to manually create instances of repositories like JobRepository. Spring Boot automatically creates and manages these objects for you using dependency injection.
//Spring Boot detects JobRepository as a Bean (a component that is managed by Spring).
//Spring injects (autowires) this repository into your JobServiceImpl class using the constructor.
//You don’t need to manually create or initialize JobRepository—Spring does it for you.
//Spring creates an instance of JobRepository at runtime and injects it wherever needed.
//Why Do We Use Constructor-Based Injection?
//
//    Spring Automatically Injects Dependencies → No need to manually create JobRepository instances.
//    Encourages Immutable Fields (final) → private final JobRepository jobRepository; ensures that it can’t be modified after initialization.
//    Easier to Write Unit Tests → You can easily mock JobRepository when writing tests.
//    Spring Boot Best Practice → Constructor-based injection is the preferred way in Spring Boot.
    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {

        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public Job updateJob(Long id, Job updatedJob) {
        //Optional<T> is a wrapper class in Java that prevents NullPointerException (NPE) by handling missing or null values safely.
        //❌ Without Optional (Risk of Null)
        //
        //Job job = jobRepository.findById(1L);
        //System.out.println(job.getTitle()); // ❌ ERROR if job is null!
        //
        //🔴 If the job does not exist, findById(1L) returns null, and calling getTitle() crashes your program.
        //
        //✅ With Optional (Safe Handling)
        //
        //Optional<Job> jobOptional = jobRepository.findById(1L);
        //if (jobOptional.isPresent()) {  // ✅ Checks if job exists
        //    Job job = jobOptional.get();
        //    System.out.println(job.getTitle());
        //} else {
        //    System.out.println("Job not found!");
        //}
        //
        //✔ Prevents NullPointerException
        //✔ Handles missing values safely
        //ispresnt is one of its method
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());

            return jobRepository.save(job); // ✅ Save and return the updated job
        }
        return null;
    }
}
