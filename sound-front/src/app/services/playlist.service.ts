import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Playlist } from '../model/playlist.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {

  constructor(private httpClient: HttpClient) { }

  public getPlaylists(): Observable<Playlist[]> { 
    return this.httpClient.get<Playlist[]>("https://sound-222.azurewebsites.net/playlist");
  }
  public getPlaylistsById(id: any): Observable<Playlist> {
    return this.httpClient.get<Playlist>("https://sound-222.azurewebsites.net/playlist/" + id);
  }
}

/* ja mexi */
