import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAssistanceTechnique } from 'app/shared/model/assistance-technique.model';

@Component({
  selector: 'jhi-assistance-technique-detail',
  templateUrl: './assistance-technique-detail.component.html'
})
export class AssistanceTechniqueDetailComponent implements OnInit {
  assistanceTechnique: IAssistanceTechnique;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ assistanceTechnique }) => {
      this.assistanceTechnique = assistanceTechnique;
    });
  }

  previousState() {
    window.history.back();
  }
}
