import { MusicaModel } from './../model/musica.model';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MusicaService } from '../services/musica.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Time } from '@angular/common';

@Component({
  selector: 'app-create-musica',
  templateUrl: './create-musica.component.html',
  styleUrls: ['./create-musica.component.css'],
  providers: []
  
})
export class CreateMusicaComponent {

  nomeMusica = new FormControl('', [Validators.required]);
  autorMusica = new FormControl('', [Validators.required]);

  @Output() newMusicaEvent = new EventEmitter();
  @Input() idPlaylist:any = '';

  constructor(private musicaService: MusicaService, private snackBar: MatSnackBar){

  }

  public adicionarNovaMusica(){
    if(this.nomeMusica.hasError("required")){
      return;
    }

    if(this.autorMusica.hasError("required")){
      return;
    }

    let musica: MusicaModel = {
      nome: this.nomeMusica.value as string,
      autor: this.autorMusica.value as string,
    };

    this.musicaService.createMusica(this.idPlaylist, musica).subscribe(response => {
      this.snackBar.open("Música adicionada com sucesso", "Ok");
      this.newMusicaEvent.emit();
    });
  }
}

/* ja mexi */
