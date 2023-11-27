package com.example.soundplus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.soundplus.exception.MusicaException;
import com.example.soundplus.model.Musica;
import com.example.soundplus.model.Playlist;
import com.example.soundplus.repository.MusicaRepository;


@Service
public class MusicaService {
    @Autowired
    MusicaRepository musicaRepository;

    @Autowired
    PlaylistService playlistService;

    // CRUD

    public List<Musica> findAll() {
        return this.musicaRepository.findAll();
    }

    public Optional<Musica> findById(long id) {
        return this.musicaRepository.findById(id);
    }

    public Musica create(long idPlaylist, Musica newMusica) throws MusicaException, Exception {
        Optional<Playlist> opPlaylist = this.playlistService.findById(idPlaylist);

        if (opPlaylist.isPresent() == false) {
            throw new MusicaException("Não encontrei a playlist para adicionar essa musica");
        }

        Playlist playlist = opPlaylist.get(); 
        playlist.addMusica(newMusica);
        this.playlistService.save(playlist); 

        Musica result = playlist.getMusicas().get(playlist.getMusicas().size() - 1);
        return result;
    }

    public Musica update(long id, Musica newData) throws MusicaException {
        Optional<Musica> existingItemOptional = musicaRepository.findById(id);

        if (existingItemOptional.isPresent() == false)
            throw new MusicaException("Não encontrei a musica a ser atualizada");

        Musica existingItem = existingItemOptional.get();

        existingItem.setNome(newData.getNome());
        existingItem.setAutor(newData.getAutor());
        existingItem.setDuracao(newData.getDuracao());
        

        musicaRepository.save(existingItem);
        return existingItem;
    }

    public void delete(long id) throws MusicaException {
        Optional<Musica> musica = this.musicaRepository.findById(id);

        if (musica.isPresent() == false)
            throw new MusicaException("Não encontrei a musica a ser deletada");

        this.musicaRepository.delete(musica.get());
    }

}

