package com.apiathletevision.apiathletevision.services.specifications;

import com.apiathletevision.apiathletevision.entities.Evento;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class EventoSpecification {
    public static Specification<Evento> getFilters(String search, Boolean status) {
        return (root, query, criteriaBuilder) -> {

            Predicate combinedPredicate = criteriaBuilder.conjunction(); // equivale a 'true'

            if (search != null) {
                String searchPattern = "%" + search.toLowerCase() + "%";

                // Aqui, você deve acessar um campo String dentro de "pessoa", como "nome"
                Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), searchPattern);

                Predicate searchPredicate = criteriaBuilder.or(titlePredicate);
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
