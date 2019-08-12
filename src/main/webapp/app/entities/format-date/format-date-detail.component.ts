import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormatDate } from 'app/shared/model/format-date.model';

@Component({
  selector: 'jhi-format-date-detail',
  templateUrl: './format-date-detail.component.html'
})
export class FormatDateDetailComponent implements OnInit {
  formatDate: IFormatDate;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ formatDate }) => {
      this.formatDate = formatDate;
    });
  }

  previousState() {
    window.history.back();
  }
}
