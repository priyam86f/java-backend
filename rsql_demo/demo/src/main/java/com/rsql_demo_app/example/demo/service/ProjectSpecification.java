package com.rsql_demo_app.example.demo.service;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rsql_demo_app.example.demo.JpaRsqlSpecificationVisitor;
import com.rsql_demo_app.example.demo.entity.Project;

@Service
public class ProjectSpecification {
    public Specification<Project> rsql(final String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (query != null && !query.isEmpty()) {
                Node node = new RSQLParser().parse(query);
                return node.accept(new JpaRsqlSpecificationVisitor<Project>(), null).toPredicate(root, criteriaQuery, criteriaBuilder);
            }
            return criteriaBuilder.conjunction(); 
        };
    }
}
