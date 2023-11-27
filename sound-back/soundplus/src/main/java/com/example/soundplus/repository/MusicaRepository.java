package com.example.soundplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.soundplus.model.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
    
}
