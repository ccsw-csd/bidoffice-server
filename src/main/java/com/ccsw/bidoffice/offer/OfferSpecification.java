package com.ccsw.bidoffice.offer;

import java.time.LocalDateTime;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ccsw.bidoffice.common.criteria.BinarySearchCriteria;
import com.ccsw.bidoffice.offer.model.OfferEntity;

public class OfferSpecification implements Specification<OfferEntity> {

    private static final long serialVersionUID = 1L;

    private final BinarySearchCriteria criteria;

    public OfferSpecification(BinarySearchCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<OfferEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getValue() != null
                && criteria.getValue2() == null) {
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());

        } else if (criteria.getOperation().equalsIgnoreCase("between") && criteria.getValue() != null
                && criteria.getValue2() != null) {
            return criteriaBuilder.between(root.get(criteria.getKey()), ObjectToLocalDate(criteria.getValue()),
                    ObjectToLocalDate(criteria.getValue2()));

        } else if (criteria.getOperation().equalsIgnoreCase("isMember") && criteria.getValue() != null
                && criteria.getValue2() == null) {
            return criteriaBuilder.isMember(criteria.getValue(), root.get(criteria.getKey()));
        }

        return null;
    }

    private LocalDateTime ObjectToLocalDate(Object dateLocal) {
        if (dateLocal instanceof LocalDateTime) {
            return (LocalDateTime) dateLocal;
        }
        return null;
    }
}
