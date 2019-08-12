import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeAer } from 'app/shared/model/type-aer.model';

@Component({
  selector: 'jhi-type-aer-detail',
  templateUrl: './type-aer-detail.component.html'
})
export class TypeAerDetailComponent implements OnInit {
  typeAer: ITypeAer;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ typeAer }) => {
      this.typeAer = typeAer;
    });
  }

  previousState() {
    window.history.back();
  }
}
