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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.soundplus.exception.PlaylistException;
import com.example.soundplus.model.Playlist;
import com.example.soundplus.service.PlaylistService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/playlist")
@Tag(name = "Playlist", description = "")
@CrossOrigin
public class PlaylistController {

    @Autowired
    private PlaylistService _playlistService;

    @GetMapping 
    public ResponseEntity<List<Playlist>> getAll() {
        try {
            return new ResponseEntity<>(this._playlistService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping 
    public ResponseEntity<Playlist> create(@Valid @RequestBody Playlist item) throws PlaylistException{ 
        Playlist result = this._playlistService.save(item);
        return new ResponseEntity<>(result, HttpStatus.CREATED);   
    }

    @GetMapping("{id}") 
    public ResponseEntity<Playlist> getById(@PathVariable("id") long id) {

        Optional<Playlist> result = this._playlistService.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } 
            
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }

    @PutMapping("{id}") 
    public ResponseEntity<Playlist> update(@PathVariable("id") long id, @Valid @RequestBody Playlist playlistNovosDados) throws PlaylistException{ 
        Playlist result = this._playlistService.update(id, playlistNovosDados);
        return new ResponseEntity<>(result, HttpStatus.OK); 
    }

    @DeleteMapping("{id}") 
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws PlaylistException{
        this._playlistService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}")
    public ResponseEntity<String> uploadPlaylistImage(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) throws PlaylistException, Exception{
        _playlistService.uploadFileToPlaylist(file, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

