package com.example.soundplus.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "playlists")
public class Playlist {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // identificação da tabela
    private long id;

    @Column(nullable = false) // pro banco saber que a coluna não aceita nulo
    @NotBlank(message = "O campo nome não pode ser vazio") // msg para o usuario dizendo que o campo não pode ser nulo
    private String nome;

    @Column(nullable = false) 
    @NotBlank(message = "O campo autor não pode ser vazio") 
    private String autor;

    @Column(nullable = true)
    private LocalDateTime dtCriacao;

    @Column(nullable = true)
    private String descricao;

    @Column(nullable = true)
    private String urlImage;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) // cascade - quando deletar uma pessoa, deleta todos os telefones. fech lazy - só carrega os telefones se for pedido em determinado trecho do codigo, diferentemente do eager que ja carrega tudo na hr.
    @JoinColumn(name = "playlist_id") // especifica que ele vai pegar os telefones por essa chave.
    private List<Musica> musicas;

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
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

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDateTime dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void addMusica(Musica musica) {
        this.musicas.add(musica);
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

}

