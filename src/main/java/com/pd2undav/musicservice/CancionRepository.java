package com.pd2undav.musicservice;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CancionRepository extends CrudRepository<Cancion, String> {
    List<Cancion> findByArtista(Artista id_artista);
    
    Optional<Cancion> findById(String id_cancion);
}
