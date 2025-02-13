package com.rishith.firstjobapp.review;


import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReviewService {



    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId ,Review review);

}
