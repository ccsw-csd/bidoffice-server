package com.ccsw.bidoffice.user;

import com.ccsw.bidoffice.common.criteria.SearchCriteria;
import com.ccsw.bidoffice.user.model.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class UserSpecification implements Specification<UserEntity> {

    private static final long serialVersionUID = 1L;

    private final SearchCriteria criteria;

    public UserSpecification(SearchCriteria criteria){
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        else if(criteria.getOperation().equalsIgnoreCase("concat")){
            if(root.get(criteria.getKey()).getJavaType() == String.class){
                Expression<String> exp = builder.concat(root.<String>get(criteria.getKey()), " ");
                exp = builder.concat(exp, root.<String>get("last_name"));
                return builder.like(exp,"%" + criteria.getValue() + "%");
            }
        }
        return null;
    }
}
