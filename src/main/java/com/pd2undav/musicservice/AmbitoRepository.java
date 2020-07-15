package com.pd2undav.musicservice;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AmbitoRepository extends CrudRepository<Ambito, String> {
    Optional<Ambito> findById(String id_ambito);

    @Query(value = "SELECT id_ambito FROM ambito_artista WHERE id_artista = ?1 ;", nativeQuery = true)
    List<String> findArtistaAlbums(String artistaID);
}
