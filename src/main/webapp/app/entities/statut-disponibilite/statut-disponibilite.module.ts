import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
  StatutDisponibiliteComponent,
  StatutDisponibiliteDetailComponent,
  StatutDisponibiliteUpdateComponent,
  StatutDisponibiliteDeletePopupComponent,
  StatutDisponibiliteDeleteDialogComponent,
  statutDisponibiliteRoute,
  statutDisponibilitePopupRoute
} from './';

const ENTITY_STATES = [...statutDisponibiliteRoute, ...statutDisponibilitePopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    StatutDisponibiliteComponent,
    StatutDisponibiliteDetailComponent,
    StatutDisponibiliteUpdateComponent,
    StatutDisponibiliteDeleteDialogComponent,
    StatutDisponibiliteDeletePopupComponent
  ],
  entryComponents: [
    StatutDisponibiliteComponent,
    StatutDisponibiliteUpdateComponent,
    StatutDisponibiliteDeleteDialogComponent,
    StatutDisponibiliteDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationStatutDisponibiliteModule {}
