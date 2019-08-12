import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
  StatutComponent,
  StatutDetailComponent,
  StatutUpdateComponent,
  StatutDeletePopupComponent,
  StatutDeleteDialogComponent,
  statutRoute,
  statutPopupRoute
} from './';

const ENTITY_STATES = [...statutRoute, ...statutPopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [StatutComponent, StatutDetailComponent, StatutUpdateComponent, StatutDeleteDialogComponent, StatutDeletePopupComponent],
  entryComponents: [StatutComponent, StatutUpdateComponent, StatutDeleteDialogComponent, StatutDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationStatutModule {}
