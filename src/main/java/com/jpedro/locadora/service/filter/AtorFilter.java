package com.jpedro.locadora.service.filter;

import com.jpedro.locadora.domain.Ator_;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AtorFilter implements BaseFilter{

    private Long id;
    private String nome;

    @Override
    public Specification filter() {
        return (root, cq, cb) ->
                cb.and(getPredicates(root, cb).toArray(new Predicate[0]));
    }

    private List<Predicate> getPredicates(Root root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (id != null) {
            Predicate predicate = cb.equal(root.get(Ator_.id), id);
            predicates.add(predicate);
        }

        if (!StringUtils.isEmpty(nome)) {
            Predicate predicate = cb.like(cb.lower(root.get(Ator_.nome)), "%" + nome.toLowerCase() + "%");
            predicates.add(predicate);
        }

        return predicates;
    }
}
