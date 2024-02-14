package com.ccsw.bidoffice.offer;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.*;

import com.ccsw.bidoffice.person.model.PersonEntity;
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

        if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getValue() != null && criteria.getValue2() == null) {
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        } else if (criteria.getOperation().equalsIgnoreCase("between") && criteria.getValue() != null && criteria.getValue2() != null) {
            return criteriaBuilder.between(root.get(criteria.getKey()), ObjectToLocalDate(criteria.getValue()), ObjectToLocalDate(criteria.getValue2()));
        } else if (criteria.getOperation().equalsIgnoreCase("isMember") && criteria.getValue() != null && !((List<?>) criteria.getValue()).isEmpty() && criteria.getValue2() == null) {
            return criteriaBuilder.in(root.get(criteria.getKey())).value(((List<?>) criteria.getValue()));
        } else if (criteria.getOperation().equalsIgnoreCase("inIdList") && criteria.getValue() != null && criteria.getValue2() == null) {
            return criteriaBuilder.in(root.join(criteria.getKey()).get("id")).value(criteria.getValue());
        } else if (criteria.getOperation().equalsIgnoreCase("like") && criteria.getValue() != null) {
            return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        }

        return null;
    }

    private LocalDate ObjectToLocalDate(Object dateLocal) {
        if (dateLocal instanceof LocalDate) {
            return (LocalDate) dateLocal;
        }
        return null;
    }
}
