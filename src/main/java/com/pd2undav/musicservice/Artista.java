package com.pd2undav.musicservice;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Artista {

    @Id
    private String id_artista;
    private String nombre;
    private String bio;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cancion_artista",
            joinColumns = @JoinColumn(name = "id_artista"),
            inverseJoinColumns = @JoinColumn(name = "id_cancion"))
    private Set<Cancion> canciones;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ambito_artista",
            joinColumns = @JoinColumn(name = "id_artista"),
            inverseJoinColumns = @JoinColumn(name = "id_ambito"))
    private Set<Ambito> ambitos;

    public Artista() {}

    public Artista(String id_artista, String nombre, String bio, Set<Cancion> canciones, Set<Ambito> ambitos) {
        this.id_artista = id_artista;
        this.nombre = nombre;
        this.bio = bio;
        this.canciones = canciones;
        this.ambitos = ambitos;
    }

    public Artista(String id_artista, String nombre, String bio) {
        this.id_artista = id_artista;
        this.nombre = nombre;
        this.bio = bio;
        this.canciones = null;
        this.ambitos = null;
    }

    @Override
    public String toString() {
        return String.format("Artista[id=%s, nombre=%s]", id_artista, nombre);
    }

    public String getId_artista() {
        return id_artista;
    }

    public String getNombre() {
        return nombre;
    }

    public String getBio() {
        return bio;
    }

    public Set<Cancion> getCanciones() {
        return canciones;
    }

    public Set<Ambito> getAmbitos() {
        return ambitos;
    }
}
