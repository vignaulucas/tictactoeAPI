package com.postegresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.postegresql.model.Game;

@RepositoryRestResource
public interface GameRepository extends JpaRepository<Game, Long> {
}

