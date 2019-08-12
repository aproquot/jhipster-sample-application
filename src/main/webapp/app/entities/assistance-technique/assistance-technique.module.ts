import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
  AssistanceTechniqueComponent,
  AssistanceTechniqueDetailComponent,
  AssistanceTechniqueUpdateComponent,
  AssistanceTechniqueDeletePopupComponent,
  AssistanceTechniqueDeleteDialogComponent,
  assistanceTechniqueRoute,
  assistanceTechniquePopupRoute
} from './';

const ENTITY_STATES = [...assistanceTechniqueRoute, ...assistanceTechniquePopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AssistanceTechniqueComponent,
    AssistanceTechniqueDetailComponent,
    AssistanceTechniqueUpdateComponent,
    AssistanceTechniqueDeleteDialogComponent,
    AssistanceTechniqueDeletePopupComponent
  ],
  entryComponents: [
    AssistanceTechniqueComponent,
    AssistanceTechniqueUpdateComponent,
    AssistanceTechniqueDeleteDialogComponent,
    AssistanceTechniqueDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationAssistanceTechniqueModule {}
