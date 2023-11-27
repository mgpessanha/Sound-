import { Router } from '@angular/router';
import { Playlist } from '../model/playlist.model';
import { PlaylistService } from './../services/playlist.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit{
  playlists: Playlist[] = [];

  constructor(private playlistService: PlaylistService, private router: Router) {

  }

  ngOnInit(): void { 
    this.playlistService.getPlaylists().subscribe(response => { 
      this.playlists = response;
    })
  }

  redirectToDetail(id: any) {
    this.router.navigate(["detail", id]);
  }

}

/* ja mexi */
