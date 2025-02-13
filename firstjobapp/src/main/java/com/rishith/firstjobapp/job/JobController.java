package com.rishith.firstjobapp.job;


import com.rishith.firstjobapp.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rishith.firstjobapp.job.JobService;

import java.util.ArrayList;
import java.util.List;
//@RestController
//@RequestMapping("/api") // ✅ All endpoints in this class will start with "/jobs"
@RestController
public class JobController {
//JobService is injected into the controller using constructor-based dependency injection.

    //Spring Boot automatically injects the JobServiceImpl instance into JobController. Here's how:
//
//    Marking JobServiceImpl as a Service
//        In JobServiceImpl.java, we added the @Service annotation:
//    This tells Spring Boot to manage this class as a Spring Bean.
//
//Dependency Injection in JobController
//
//    The JobController has a constructor that takes JobService as a parameter:
//Spring Boot automatically provides an instance of JobServiceImpl when the application runs.
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public List<Job> findAll() {
        return jobService.findAll();
    }

    @PostMapping("/jobs")
    public String createJob(@RequestBody Job job) {
        jobService.createJob(job);

        return "Job created";
    }

    //we are telling that {id} olage eney bandru must be accepted
//and passed to getjobbyid as a parameter
    @GetMapping("/jobs/{id}")
    public Job getJobById(@PathVariable Long id) {

        Job job = jobService.getJobById(id);
        return job;
    }
    @DeleteMapping("jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if (deleted) {
            return ResponseEntity.ok("Job deleted"); // ✅ Returns 200 OK if job is deleted
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found"); // ✅ Returns 404 if not found
    }

    @PutMapping("jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        Job job = jobService.updateJob(id, updatedJob);
        if (job != null) {
            return ResponseEntity.ok(job); // ✅ Returns 200 OK with updated job
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // ✅ Returns 404 Not Found if job not found
    }
}
