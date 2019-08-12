import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRv } from 'app/shared/model/rv.model';

@Component({
  selector: 'jhi-rv-detail',
  templateUrl: './rv-detail.component.html'
})
export class RvDetailComponent implements OnInit {
  rv: IRv;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ rv }) => {
      this.rv = rv;
    });
  }

  previousState() {
    window.history.back();
  }
}
