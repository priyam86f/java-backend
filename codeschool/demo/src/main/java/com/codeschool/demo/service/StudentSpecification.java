package com.codeschool.demo.service;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.codeschool.demo.entity.Student;



@Service
public class StudentSpecification {
    public Specification<Student> rsql(final String query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (query != null && !query.isEmpty()) {
                Node node = new RSQLParser().parse(query);
                return node.accept(new JpaRsqlSpecificationVisitor<Student>(), null).toPredicate(root, criteriaQuery, criteriaBuilder);
            }
            return criteriaBuilder.conjunction(); 
        };
    }
}
