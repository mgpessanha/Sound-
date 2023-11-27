import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMusicaComponent } from './create-musica.component';

describe('CreateMusicaComponent', () => {
  let component: CreateMusicaComponent;
  let fixture: ComponentFixture<CreateMusicaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateMusicaComponent]
    });
    fixture = TestBed.createComponent(CreateMusicaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
