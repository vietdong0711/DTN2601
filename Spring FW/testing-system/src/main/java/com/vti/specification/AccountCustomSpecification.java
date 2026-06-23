package com.vti.specification;

import com.vti.entity.Account;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@AllArgsConstructor
public class AccountCustomSpecification implements Specification<Account> {

    @NonNull
    private String field;
    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<Account> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {
        if ("username".equalsIgnoreCase(field)) {
            return criteriaBuilder.like(root.get("username"), "%"+ value + "%" ); // username like '%?%'
        }
        if ("fullName".equalsIgnoreCase(field)) {
            return criteriaBuilder.like(root.get("fullName"), "%"+ value + "%" );// fullName like '%?%'
        }
        if ("email".equalsIgnoreCase(field)) {
            return criteriaBuilder.like(root.get("email"), "%"+ value + "%" );// email like '%?%'
        }
        if ("departmentName".equalsIgnoreCase(field)) {
            return criteriaBuilder.like(root.get("department").get("name"), "%"+ value + "%" );// departmentName like '%?%'
        }
        if ("positionName".equalsIgnoreCase(field)) {
            return criteriaBuilder.like(root.get("position").get("name"), "%"+ value + "%" );// positionName like '%?%'
        }
        return null;
    }
}
