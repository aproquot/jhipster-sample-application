import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStatutDisponibilite } from 'app/shared/model/statut-disponibilite.model';

@Component({
  selector: 'jhi-statut-disponibilite-detail',
  templateUrl: './statut-disponibilite-detail.component.html'
})
export class StatutDisponibiliteDetailComponent implements OnInit {
  statutDisponibilite: IStatutDisponibilite;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ statutDisponibilite }) => {
      this.statutDisponibilite = statutDisponibilite;
    });
  }

  previousState() {
    window.history.back();
  }
}
