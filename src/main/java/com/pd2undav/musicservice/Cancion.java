package com.pd2undav.musicservice;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cancion {

    @Id
    private String id_cancion;
    private String nombre;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cancion_ambito",
            joinColumns = @JoinColumn(name = "id_cancion"),
            inverseJoinColumns = @JoinColumn(name = "id_ambito"))
    private Set<Ambito> ambitos;
    @ManyToOne
    @JoinTable(
            name = "cancion_artista",
            joinColumns = @JoinColumn(name = "id_cancion"),
            inverseJoinColumns = @JoinColumn(name = "id_artista"))
    private Artista artista;

    protected Cancion() {}

    public Cancion(String id_cancion, String nombre, Set<Ambito> ambitos, Artista artista) {
        this.id_cancion = id_cancion;
        this.nombre = nombre;
        this.ambitos = ambitos;
        this.artista = artista;
    }

    public Cancion(String id_cancion, String nombre) {
        this.id_cancion = id_cancion;
        this.nombre = nombre;
        this.ambitos = null;
        this.artista = null;
    }

    @Override
    public String toString() {
        return String.format("Cancion[id=%s, nombre=%s]", id_cancion, nombre);
    }

    public String getId_cancion() {
        return id_cancion;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<Ambito> getAmbitos() {
        return ambitos;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setAmbitos(Set<Ambito> ambitos) {
        this.ambitos = ambitos;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}
