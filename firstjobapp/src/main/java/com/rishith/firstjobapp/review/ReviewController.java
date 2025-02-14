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

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReview(companyId, reviewId), HttpStatus.OK);

    }

    @PutMapping("reviews/{reviewId}")
    //@PathVariable Long companyId
    //
    //    📌 Extracts companyId from the URL.
    //    Example URL: PUT /companies/1/reviews/5
    //    companyId = 1 (Company that owns the review)
    //
    //@PathVariable Long reviewId
    //
    //    📌 Extracts reviewId from the URL.
    //    Example URL: PUT /companies/1/reviews/5
    //    reviewId = 5 (Specific review being updated)
    //
    //@RequestBody Review review
    //
    //    📌 Takes the JSON request body and converts it into a Review object.
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        boolean isReviewSaved = reviewService.updateReview(companyId, reviewId, review);
        if (isReviewSaved) {
            return new ResponseEntity<>("Review updated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isReviewDeleted=reviewService.deleteReview(companyId,reviewId);
        if (isReviewDeleted) {
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
        }
    }



}
