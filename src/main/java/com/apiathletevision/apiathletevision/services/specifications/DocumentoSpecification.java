package com.apiathletevision.apiathletevision.services.specifications;

import com.apiathletevision.apiathletevision.entities.Documento;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class DocumentoSpecification {
    public static Specification<Documento> getFilters(String search) {
        return (root, query, criteriaBuilder) -> {

            Predicate combinedPredicate = criteriaBuilder.conjunction(); // equivale a 'true'

            if (search != null) {
                String searchPattern = "%" + search.toLowerCase() + "%";

                // Aqui, você deve acessar um campo String dentro de "pessoa", como "nome"
                Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("imagem")), searchPattern);

                Predicate searchPredicate = criteriaBuilder.or(titlePredicate);
                combinedPredicate = criteriaBuilder.and(combinedPredicate, searchPredicate);
            }

            return combinedPredicate;
        };
    }
}
