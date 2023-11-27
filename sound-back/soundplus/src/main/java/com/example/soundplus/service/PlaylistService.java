package com.example.soundplus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.soundplus.exception.PlaylistException;
import com.example.soundplus.model.Playlist;
import com.example.soundplus.repository.PlaylistRepository;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository _playlistRepository;

    @Autowired
    private AzureStorageAccountService azureStorageAccountService;
    

    // CRUD

    public List<Playlist> findAll() { // busca todas as pessoas
        return this._playlistRepository.findAll();
    }
    //--------------------------------------------------------------

    public Optional<Playlist> findById(long id) { 
        return this._playlistRepository.findById(id);
    }
    //--------------------------------------------------------------

    public Playlist save(Playlist playlist) throws PlaylistException { 

        this._playlistRepository.save(playlist);
        return playlist;
    }
    //--------------------------------------------------------------

    public Playlist update(long id, Playlist newData) throws PlaylistException { 
        Optional<Playlist> result = this._playlistRepository.findById(id); 

        if (result.isPresent() == false) { 
            throw new PlaylistException("Não encontrei a playlist a ser atualizada");
        }

        Playlist playlistASerAtualizada = result.get(); 
        playlistASerAtualizada.setNome(newData.getNome());
        playlistASerAtualizada.setAutor(newData.getAutor());
        playlistASerAtualizada.setDescricao(newData.getDescricao());
        playlistASerAtualizada.setDtCriacao(newData.getDtCriacao());

        this._playlistRepository.save(playlistASerAtualizada); 
        return playlistASerAtualizada; 
    }
    //--------------------------------------------------------------

    public void delete(long id) throws PlaylistException { 
        Optional<Playlist> playlistASerExcluida = this._playlistRepository.findById(id); 
        
        if (playlistASerExcluida.isPresent() == false) { 
            throw new PlaylistException("Não encontrei a playlist a ser deletada");
        }
        this._playlistRepository.delete(playlistASerExcluida.get());
    }
    //--------------------------------------------------------------

    public void saveMusica(Playlist playlist) {
        this._playlistRepository.save(playlist);
    }
    //--------------------------------------------------------------

    public void uploadFileToPlaylist(MultipartFile file, long id) throws PlaylistException, Exception {
        
        Optional<Playlist> opPlaylist = this._playlistRepository.findById(id);
        
        if (opPlaylist.isPresent() == false) {
            throw new PlaylistException("Não encontrei a playlist para associar a imagem");
        }

        Playlist playlist = opPlaylist.get(); 
        String ulrImage = this.azureStorageAccountService.uploadFileToAzure(file); 
        playlist.setUrlImage(ulrImage); 
        this._playlistRepository.save(playlist); 
    }

}
