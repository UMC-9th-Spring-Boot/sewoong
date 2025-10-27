package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.entity.QReview;
import com.example.umc.domain.store.entity.QStore;
import com.example.umc.domain.user.entity.QUser;
import com.example.umc.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl {

  private final EntityManager em;

  public List<Review> searchMyReviews(Predicate predicate) {
    JPAQueryFactory queryFactory = new JPAQueryFactory(em);
    QReview review = QReview.review;
    QUser user = QUser.user;
    QStore store = QStore.store;

    return queryFactory
        .selectFrom(review)
        .leftJoin(review.user, user)
        .leftJoin(review.store, store)
        .where(predicate)
        .fetch();
  }
}
