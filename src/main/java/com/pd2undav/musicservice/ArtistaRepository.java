package com.pd2undav.musicservice;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArtistaRepository extends CrudRepository<Artista, String> {
    Optional<Artista> findById(String id_artista);
}