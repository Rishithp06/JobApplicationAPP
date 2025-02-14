package com.rishith.firstjobapp.review.impl;

import com.rishith.firstjobapp.company.Company;
import com.rishith.firstjobapp.company.CompanyService;
import com.rishith.firstjobapp.review.Review;
import com.rishith.firstjobapp.review.ReviewRepository;
import com.rishith.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.spi.ToolProvider.findFirst;


@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        //the below findbycompanyid is my own and not jpa built
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null); // ✅ Removed misplaced semicolon and corrected syntax
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review Updatedreview) {
        if (companyService.getCompanyById(companyId) != null) {
            Updatedreview.setCompany(companyService.getCompanyById(companyId));
            Updatedreview.setId(reviewId);
            reviewRepository.save(Updatedreview);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {

            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null) ;
            companyService.updateCompany(company, companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        } else {
            return false;
        }
    }


}
