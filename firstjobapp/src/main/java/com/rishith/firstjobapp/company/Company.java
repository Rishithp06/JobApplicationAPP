package com.rishith.firstjobapp.company;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rishith.firstjobapp.job.Job;
import com.rishith.firstjobapp.review.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @JsonIgnore
    //@OneToMany means one company can have many jobs (a one-to-many relationship).
//mappedBy = "company" tells Hibernate that the company field in the Job entity owns the relationship.
    //How Does This Work?
//
//    Every company can have multiple jobs.
//
//    The job table will have a company_id column that links to the company table.
    //job table will get company id which maps compamnyiod with job , it wont create a new table which does it , if you didnt do
    //mapped by and then give value a separate table would have been created
    @OneToMany(mappedBy = "company")
    //every company has a job so map it
    private List<Job> jobs;
//Why are we passing company in the JSON even though it's not directly mentioned in the schema? 🤔
//
//Even though the Job entity does not have company_id explicitly mentioned, it is still required because of the @ManyToOne relationship with Company
    @OneToMany(mappedBy = "company")
    private List<Review>reviews;

    public Company() {
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
