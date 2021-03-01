package com.jpedro.locadora.service.filter;

import com.jpedro.locadora.domain.Ator_;
import com.jpedro.locadora.domain.Classe_;
import com.jpedro.locadora.domain.Diretor_;
import com.jpedro.locadora.domain.Titulo_;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TituloFilter implements BaseFilter{

    private Long id;
    private String nome;
    private String categoria;
    private String sinopse;
    private Long idDiretor;
    private Long idClasse;
    private Long idAtor;

    @Override
    public Specification filter() {
        return (root, cq, cb) ->
                cb.and(getPredicates(root, cb).toArray(new Predicate[0]));
    }

    private List<Predicate> getPredicates(Root root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (id != null) {
            Predicate predicate = cb.equal(root.get(Titulo_.id), id);
            predicates.add(predicate);
        }

        if (!StringUtils.isEmpty(nome)) {
            Predicate predicate = cb.like(cb.lower(root.get(Titulo_.nome)), "%" + nome.toLowerCase() + "%");
            predicates.add(predicate);
        }

        if (!StringUtils.isEmpty(categoria)) {
            Predicate predicate = cb.like(cb.lower(root.get(Titulo_.categoria)), "%" + categoria.toLowerCase() + "%");
            predicates.add(predicate);
        }

        if (!StringUtils.isEmpty(sinopse)) {
            Predicate predicate = cb.like(cb.lower(root.get(Titulo_.sinopse)), "%" + sinopse.toLowerCase() + "%");
            predicates.add(predicate);
        }

        if (idClasse != null) {
            Predicate predicate = cb.equal(root.join(Titulo_.classe).get(Classe_.id), idClasse);
            predicates.add(predicate);
        }

        if (idDiretor != null) {
            Predicate predicate = cb.equal(root.join(Titulo_.diretor).get(Diretor_.id), idDiretor);
            predicates.add(predicate);
        }

        if (idAtor != null) {
            Join<Titulo_, Ator_> join = root.join(Titulo_.atores, JoinType.INNER);
            Predicate predicate = cb.equal(join.get(Ator_.ID).as(Long.class), idAtor);
            predicates.add(predicate);
        }

        return predicates;
    }
}
