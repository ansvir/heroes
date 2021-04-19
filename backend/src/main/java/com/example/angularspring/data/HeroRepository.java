package com.example.angularspring.data;

import com.example.angularspring.domain.Hero;
import org.springframework.data.repository.CrudRepository;

public interface HeroRepository extends CrudRepository<Hero, Long> {
    Iterable<Hero> findByName(String name);
}
