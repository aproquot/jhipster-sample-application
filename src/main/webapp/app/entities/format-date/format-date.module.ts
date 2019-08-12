import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
  FormatDateComponent,
  FormatDateDetailComponent,
  FormatDateUpdateComponent,
  FormatDateDeletePopupComponent,
  FormatDateDeleteDialogComponent,
  formatDateRoute,
  formatDatePopupRoute
} from './';

const ENTITY_STATES = [...formatDateRoute, ...formatDatePopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    FormatDateComponent,
    FormatDateDetailComponent,
    FormatDateUpdateComponent,
    FormatDateDeleteDialogComponent,
    FormatDateDeletePopupComponent
  ],
  entryComponents: [FormatDateComponent, FormatDateUpdateComponent, FormatDateDeleteDialogComponent, FormatDateDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationFormatDateModule {}
