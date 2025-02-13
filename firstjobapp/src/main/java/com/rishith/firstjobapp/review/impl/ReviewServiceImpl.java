package com.rishith.firstjobapp.review.impl;

import com.rishith.firstjobapp.company.Company;
import com.rishith.firstjobapp.company.CompanyService;
import com.rishith.firstjobapp.review.Review;
import com.rishith.firstjobapp.review.ReviewRepository;
import com.rishith.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;


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
}
