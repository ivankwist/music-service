package com.pd2undav.musicservice;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ambito {

    @Id
    private String id_ambito;
    private String nombre;
    private String tipo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cancion_ambito",
            joinColumns = @JoinColumn(name = "id_ambito"),
            inverseJoinColumns = @JoinColumn(name = "id_cancion"))
    private Set<Cancion> canciones;
    @ManyToOne
    @JoinTable(
            name = "ambito_artista",
            joinColumns = @JoinColumn(name = "id_ambito"),
            inverseJoinColumns = @JoinColumn(name = "id_artista"))
    private Artista artista;

    protected Ambito() {}

    public Ambito(String id_ambito, String nombre, String tipo, Set<Cancion> canciones, Artista artista) {
        this.id_ambito = id_ambito;
        this.nombre = nombre;
        this.tipo = tipo;
        this.canciones = canciones;
        this.artista = artista;
    }

    public Ambito(String id_ambito, String nombre, String tipo) {
        this.id_ambito = id_ambito;
        this.nombre = nombre;
        this.tipo = tipo;
        this.canciones = null;
        this.artista = null;
    }

    @Override
    public String toString() {
        return String.format("Ambito[id=%s, nombre=%s, tipo=%s]", id_ambito, nombre, tipo);
    }

    public String getId_ambito() {
        return id_ambito;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public Set<Cancion> getCanciones() {
        return canciones;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setCanciones(Set<Cancion> canciones) {
        this.canciones = canciones;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}
