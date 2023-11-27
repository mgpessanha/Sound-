package com.example.soundplus.model;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name= "musicas")
public class Musica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false) // pro banco saber que a coluna não aceita nulo
    @NotBlank(message = "O campo nome não pode ser vazio") // msg para o usuario dizendo que o campo não pode ser nulo
    private String nome;

    @Column(nullable = false) 
    @NotBlank(message = "O campo autor não pode ser vazio") 
    private String autor;

    @Column(nullable = true)
    private LocalTime duracao;

    @ManyToOne
    @JsonIgnore // ignora a propriedade 
    private Playlist playlist; // esse telefone pertence a uma determinada pessoa.

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getDuracao() {
        return duracao;
    }

    public void setDuracao(LocalTime duracao) {
        this.duracao = duracao;
    }
    
}
