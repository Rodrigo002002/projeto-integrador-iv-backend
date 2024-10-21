package com.apiathletevision.apiathletevision.services.specifications;

import com.apiathletevision.apiathletevision.entities.Usuario;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpecification {
    public static Specification<Usuario> getFilters(String search, Boolean status){
        return (root, query, criteriaBuilder) -> {
            if(search == null && status == null ){
                return null;
            }

            Predicate combinedPredicate = criteriaBuilder.conjunction(); // equivale a 'true'

            if(search != null) {
                String searchPattern = "%" + search.toLowerCase() + "%";

                Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), searchPattern);
                Predicate uriPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), searchPattern);

                Predicate searchPredicate = criteriaBuilder.or(titlePredicate, uriPredicate);
                combinedPredicate = criteriaBuilder.and(combinedPredicate, searchPredicate);
            }

            if (status != null) {
                Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), status);
                combinedPredicate = criteriaBuilder.and(combinedPredicate, statusPredicate);
            }

            return combinedPredicate;
        };
    }
}
