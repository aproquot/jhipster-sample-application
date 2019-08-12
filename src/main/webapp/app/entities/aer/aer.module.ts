import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
  AerComponent,
  AerDetailComponent,
  AerUpdateComponent,
  AerDeletePopupComponent,
  AerDeleteDialogComponent,
  aerRoute,
  aerPopupRoute
} from './';

const ENTITY_STATES = [...aerRoute, ...aerPopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [AerComponent, AerDetailComponent, AerUpdateComponent, AerDeleteDialogComponent, AerDeletePopupComponent],
  entryComponents: [AerComponent, AerUpdateComponent, AerDeleteDialogComponent, AerDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationAerModule {}
