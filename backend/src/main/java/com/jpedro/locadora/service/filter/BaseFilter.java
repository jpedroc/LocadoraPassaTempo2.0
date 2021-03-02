package com.jpedro.locadora.service.filter;

import org.springframework.data.jpa.domain.Specification;

public interface BaseFilter<T> {
    Specification<T> filter();
}
