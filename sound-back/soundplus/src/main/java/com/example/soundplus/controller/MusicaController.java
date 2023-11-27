package com.example.soundplus.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.soundplus.exception.MusicaException;
import com.example.soundplus.model.Musica;
import com.example.soundplus.model.Playlist;
import com.example.soundplus.service.MusicaService;
import com.example.soundplus.service.PlaylistService;

@RestController
@RequestMapping("/playlist/{idPlaylist}/musica")
@CrossOrigin
class resourceNameController {

    @Autowired
    MusicaService musicaService;

    @Autowired PlaylistService playlistService;

     @GetMapping
    public ResponseEntity<List<Musica>> getAll(@PathVariable("idPlaylist") long idPlaylist) {
        try {
            Optional<Playlist> opPlaylist = this.playlistService.findById(idPlaylist);

            if (opPlaylist.isPresent() == false) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);    
            }

            return new ResponseEntity<>(opPlaylist.get().getMusicas(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<Musica> getById(@PathVariable("id") long id) {
        Optional<Musica> existingItemOptional = musicaService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Musica> create(@PathVariable("idPlaylist") long idPlaylist, @RequestBody Musica item) throws MusicaException, Exception {
        Musica savedItem = musicaService.create(idPlaylist, item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Musica> update(@PathVariable("id") long id, @RequestBody Musica item) throws MusicaException {
        return new ResponseEntity<>(musicaService.update(id, item), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws MusicaException {
        musicaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
