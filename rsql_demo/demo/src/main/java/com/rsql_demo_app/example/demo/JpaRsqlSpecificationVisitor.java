package com.rsql_demo_app.example.demo;

import cz.jirutka.rsql.parser.ast.*;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;

public class JpaRsqlSpecificationVisitor<T> implements RSQLVisitor<Specification<T>, Void> {

    @Override
    public Specification<T> visit(AndNode node, Void param) {
        return (root, query, criteriaBuilder) -> {
            Predicate[] predicates = new Predicate[node.getChildren().size()];
            for (int i = 0; i < node.getChildren().size(); i++) {
                predicates[i] = node.getChildren().get(i).accept(this, param).toPredicate(root, query, criteriaBuilder);
            }
            return criteriaBuilder.and(predicates);
        };
    }

    @Override
    public Specification<T> visit(OrNode node, Void param) {
        return (root, query, criteriaBuilder) -> {
            Predicate[] predicates = new Predicate[node.getChildren().size()];
            for (int i = 0; i < node.getChildren().size(); i++) {
                predicates[i] = node.getChildren().get(i).accept(this, param).toPredicate(root, query, criteriaBuilder);
            }
            return criteriaBuilder.or(predicates);
        };
    }

    @Override
    public Specification<T> visit(ComparisonNode node, Void param) {
        return (root, query, criteriaBuilder) -> {
            String selector = node.getSelector();
            Object argument = node.getArguments().get(0);
            switch (node.getOperator().getSymbol()) {
                case "==":
                    return criteriaBuilder.equal(root.get(selector), argument);
                case "!=":
                    return criteriaBuilder.notEqual(root.get(selector), argument);
                case ">":
                    return criteriaBuilder.greaterThan(root.get(selector), (Comparable) argument);
                case ">=":
                    return criteriaBuilder.greaterThanOrEqualTo(root.get(selector), (Comparable) argument);
                case "<":
                    return criteriaBuilder.lessThan(root.get(selector), (Comparable) argument);
                case "<=":
                    return criteriaBuilder.lessThanOrEqualTo(root.get(selector), (Comparable) argument);
                default:
                    throw new IllegalArgumentException("Operator " + node.getOperator().getSymbol() + " not supported.");
            }
        };
    }

    // Implement other visit methods as needed...
}
