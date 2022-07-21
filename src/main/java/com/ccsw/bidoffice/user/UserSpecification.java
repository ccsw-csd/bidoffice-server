package com.ccsw.bidoffice.user;

import com.ccsw.bidoffice.common.criteria.TernarySearchCriteria;
import com.ccsw.bidoffice.user.model.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class UserSpecification implements Specification<UserEntity> {

    private static final long serialVersionUID = 1L;

    private final TernarySearchCriteria criteria;

    public UserSpecification(TernarySearchCriteria criteria){
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(":")&& criteria.getValue() != null) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        else if(criteria.getOperation().equalsIgnoreCase("concat :")&& criteria.getValue() != null){
            Expression<String> exp = builder.concat(root.<String>get(criteria.getKey()), " ");
            exp = builder.concat(exp, root.<String>get(criteria.getKey2()));
            return builder.like(exp,"%" + criteria.getValue() + "%");
        }
        else if(criteria.getOperation().equalsIgnoreCase("concat concat :")&& criteria.getValue() != null){
            Expression<String> exp = builder.concat(root.<String>get(criteria.getKey()), " ");
            exp = builder.concat(exp, root.<String>get(criteria.getKey2()));
            exp = builder.concat(exp, " ");
            exp = builder.concat(exp, root.<String>get(criteria.getKey3()));
            query.orderBy(builder.asc(root.<String>get(criteria.getKey())), builder.desc(root.<String>get(criteria.getKey2())));
            return builder.like(exp,"%" + criteria.getValue() + "%");
        }
        return null;
    }
}
