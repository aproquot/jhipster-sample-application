import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAer } from 'app/shared/model/aer.model';

@Component({
  selector: 'jhi-aer-detail',
  templateUrl: './aer-detail.component.html'
})
export class AerDetailComponent implements OnInit {
  aer: IAer;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ aer }) => {
      this.aer = aer;
    });
  }

  previousState() {
    window.history.back();
  }
}
