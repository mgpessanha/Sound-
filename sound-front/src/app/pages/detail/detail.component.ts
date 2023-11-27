import { ActivatedRoute } from '@angular/router';
import { PlaylistService } from './../../services/playlist.service';
import { Component, OnInit } from '@angular/core';
import { Playlist } from 'src/app/model/playlist.model';
import { MusicaService } from 'src/app/services/musica.service';
import { MusicaModel } from 'src/app/model/musica.model';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit{

  playlist?: Playlist;
  musica?: MusicaModel[];
  showAdicionarMusica = false;

  constructor(private playlistService: PlaylistService,
    private musicaService: MusicaService,
    private route: ActivatedRoute){

  }

  ngOnInit(): void {

    let idPlaylist = this.route.snapshot.params["idPlaylist"];
    this.playlistService.getPlaylistsById(idPlaylist).subscribe(response => {
      this.playlist = response;
    });

    this.carregaMusica();
  }

  private carregaMusica(){

    let idPlaylist = this.route.snapshot.params["idPlaylist"];
    this.musicaService.getMusica(idPlaylist).subscribe(response => {
      this.musica = response;
    });
  }

  public mostrarAdicionarMusica(){
    this.showAdicionarMusica = true;
  }

  public atualizarMusicas(){
    this.carregaMusica();
  }
}

/* ja mexi */
