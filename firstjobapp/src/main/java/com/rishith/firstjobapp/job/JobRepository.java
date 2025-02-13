package com.rishith.firstjobapp.job;

import org.springframework.data.jpa.repository.JpaRepository;
//In Spring Data JPA, JpaRepository<T, ID> is a generic interface that needs two parameters:
//
//    T → The Entity Class (The table in the database)
//    ID → The Primary Key Type (The type of the primary key column)
public interface JobRepository extends JpaRepository<Job, Long> {

}
