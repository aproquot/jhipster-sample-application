import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
  DisponibiliteComponent,
  DisponibiliteDetailComponent,
  DisponibiliteUpdateComponent,
  DisponibiliteDeletePopupComponent,
  DisponibiliteDeleteDialogComponent,
  disponibiliteRoute,
  disponibilitePopupRoute
} from './';

const ENTITY_STATES = [...disponibiliteRoute, ...disponibilitePopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DisponibiliteComponent,
    DisponibiliteDetailComponent,
    DisponibiliteUpdateComponent,
    DisponibiliteDeleteDialogComponent,
    DisponibiliteDeletePopupComponent
  ],
  entryComponents: [
    DisponibiliteComponent,
    DisponibiliteUpdateComponent,
    DisponibiliteDeleteDialogComponent,
    DisponibiliteDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationDisponibiliteModule {}
