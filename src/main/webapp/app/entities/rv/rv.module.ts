import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
  RvComponent,
  RvDetailComponent,
  RvUpdateComponent,
  RvDeletePopupComponent,
  RvDeleteDialogComponent,
  rvRoute,
  rvPopupRoute
} from './';

const ENTITY_STATES = [...rvRoute, ...rvPopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [RvComponent, RvDetailComponent, RvUpdateComponent, RvDeleteDialogComponent, RvDeletePopupComponent],
  entryComponents: [RvComponent, RvUpdateComponent, RvDeleteDialogComponent, RvDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationRvModule {}
