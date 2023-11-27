import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MusicaModel } from '../model/musica.model';

@Injectable({
  providedIn: 'root'
})
export class MusicaService {

  constructor(private http: HttpClient) {}

  public getMusica(idPlaylist: any) : Observable <MusicaModel[]>{
    return this.http.get<MusicaModel[]>(`https://sound-222.azurewebsites.net/playlist/${idPlaylist}/musica`);
  }

  public createMusica(idPlaylist: any, musica: MusicaModel) : Observable <MusicaModel>{
    return this.http.post<MusicaModel>(`https://sound-222.azurewebsites.net/playlist/${idPlaylist}/musica`, musica);
  }
}

/* ja mexi */