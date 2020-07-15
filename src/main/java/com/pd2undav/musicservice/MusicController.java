package com.pd2undav.musicservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class MusicController {

    private static final Logger log = LoggerFactory.getLogger(MusicController.class);

    private final CancionRepository cancionRepository;
    private final AmbitoRepository ambitoRepository;
    private final ArtistaRepository artistaRepository;

    public MusicController(CancionRepository cancionRepository, AmbitoRepository ambitoRepository, ArtistaRepository artistaRepository) {
        this.cancionRepository = cancionRepository;
        this.ambitoRepository = ambitoRepository;
        this.artistaRepository = artistaRepository;
    }

    // Entidades

    @RequestMapping("/cancion")
    public ResponseEntity<Cancion> getCancionById(@RequestParam(value = "cancionID") String cancionID) {
        Cancion cancion = cancionRepository.findById(cancionID).orElse(new Cancion());
        Cancion cancionMessage = new Cancion(cancion.getId_cancion(), cancion.getNombre());

        log.info("Found Cancion: {}", cancion.toString());

        return new ResponseEntity<Cancion>(cancionMessage, HttpStatus.OK);
    }

    @RequestMapping("/ambito")
    public ResponseEntity<Ambito> getAmbitoById(@RequestParam(value = "ambitoID") String ambitoID) {
        Ambito ambito = ambitoRepository.findById(ambitoID).orElse(new Ambito());
        Ambito ambitoMessage = new Ambito(ambito.getId_ambito(), ambito.getNombre(), ambito.getTipo());

        log.info("Found Ambito: {}", ambito.toString());

        return new ResponseEntity<Ambito>(ambitoMessage, HttpStatus.OK);
    }

    @RequestMapping("/artista")
    public ResponseEntity<Artista> getArtistaById(@RequestParam(value = "artistaID") String artistaID) {
        Artista artista = artistaRepository.findById(artistaID).orElse(new Artista());
        Artista artistaMessage = new Artista(artista.getId_artista(), artista.getNombre(), artista.getBio());

        log.info("Found Artista: {}", artista.toString());

        return new ResponseEntity<Artista>(artistaMessage, HttpStatus.OK);
    }

    // Queries

    @RequestMapping("/ambito/canciones")
    public ResponseEntity<Set<Cancion>> getAmbitoCanciones(@RequestParam(value = "ambitoID") String ambitoID) {
        Ambito ambito = ambitoRepository.findById(ambitoID).orElse(new Ambito());
        log.info("Found Ambito: {}", ambito.toString());

        Set<Cancion> canciones = ambito.getCanciones();
        for (Cancion cancion: canciones) {
            cancion.setAmbitos(null);
            cancion.setArtista(null);
            log.info("Ambito has Cancion: {}", cancion.toString());
        }

        return new ResponseEntity<Set<Cancion>>(canciones, HttpStatus.OK);
    }

    @RequestMapping("/artista/ambitos")
    public ResponseEntity<Set<Ambito>> getArtistaAmbitos(@RequestParam(value = "artistaID") String artistaID) {
        Artista artista = artistaRepository.findById(artistaID).orElse(new Artista());
        log.info("Found Artista: {}", artista.toString());

        Set<Ambito> ambitos = artista.getAmbitos();
        for (Ambito ambito: ambitos) {
            ambito.setCanciones(null);
            ambito.setArtista(null);
        }
        log.info("Artista has Ambitos: {}", ambitos.toString());

        return new ResponseEntity<Set<Ambito>>(ambitos, HttpStatus.OK);
    }

    @RequestMapping("/cancion/artista/id")
    public ResponseEntity<String> getCancionArtista(@RequestParam(value = "cancionID") String cancionID) {
        Cancion cancion = cancionRepository.findById(cancionID).orElse(new Cancion());
        log.info("Found Cancion: {}", cancion.toString());

        String artistaID = cancion.getArtista().getId_artista();
        log.info("Found Artista for Cancion({}): {}", cancion.getId_cancion(), artistaID);

        return new ResponseEntity<String>(artistaID, HttpStatus.OK);
    }

    @RequestMapping("/artista/albums")
    public ResponseEntity<List<String>> getArtistaAlbumsId(@RequestParam(value = "artistaID") String artistaID) {
        List<String> albums = ambitoRepository.findArtistaAlbums(artistaID);

        return new ResponseEntity<List<String>>(albums, HttpStatus.OK);
    }

}
