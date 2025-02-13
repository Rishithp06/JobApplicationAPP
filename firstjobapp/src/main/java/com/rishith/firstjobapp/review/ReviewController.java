package com.rishith.firstjobapp.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }


    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@RequestBody Review review, @PathVariable Long companyId) {
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if (isReviewSaved) {
            return new ResponseEntity<>("Review added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not added", HttpStatus.BAD_REQUEST);
        }
    }

}
