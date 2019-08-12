import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
  TypeAerComponent,
  TypeAerDetailComponent,
  TypeAerUpdateComponent,
  TypeAerDeletePopupComponent,
  TypeAerDeleteDialogComponent,
  typeAerRoute,
  typeAerPopupRoute
} from './';

const ENTITY_STATES = [...typeAerRoute, ...typeAerPopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TypeAerComponent,
    TypeAerDetailComponent,
    TypeAerUpdateComponent,
    TypeAerDeleteDialogComponent,
    TypeAerDeletePopupComponent
  ],
  entryComponents: [TypeAerComponent, TypeAerUpdateComponent, TypeAerDeleteDialogComponent, TypeAerDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationTypeAerModule {}
