import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssistanceTechnique } from 'app/shared/model/assistance-technique.model';
import { AssistanceTechniqueService } from './assistance-technique.service';
import { AssistanceTechniqueComponent } from './assistance-technique.component';
import { AssistanceTechniqueDetailComponent } from './assistance-technique-detail.component';
import { AssistanceTechniqueUpdateComponent } from './assistance-technique-update.component';
import { AssistanceTechniqueDeletePopupComponent } from './assistance-technique-delete-dialog.component';
import { IAssistanceTechnique } from 'app/shared/model/assistance-technique.model';

@Injectable({ providedIn: 'root' })
export class AssistanceTechniqueResolve implements Resolve<IAssistanceTechnique> {
  constructor(private service: AssistanceTechniqueService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAssistanceTechnique> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AssistanceTechnique>) => response.ok),
        map((assistanceTechnique: HttpResponse<AssistanceTechnique>) => assistanceTechnique.body)
      );
    }
    return of(new AssistanceTechnique());
  }
}

export const assistanceTechniqueRoute: Routes = [
  {
    path: '',
    component: AssistanceTechniqueComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'AssistanceTechniques'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AssistanceTechniqueDetailComponent,
    resolve: {
      assistanceTechnique: AssistanceTechniqueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'AssistanceTechniques'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AssistanceTechniqueUpdateComponent,
    resolve: {
      assistanceTechnique: AssistanceTechniqueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'AssistanceTechniques'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AssistanceTechniqueUpdateComponent,
    resolve: {
      assistanceTechnique: AssistanceTechniqueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'AssistanceTechniques'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const assistanceTechniquePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AssistanceTechniqueDeletePopupComponent,
    resolve: {
      assistanceTechnique: AssistanceTechniqueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'AssistanceTechniques'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
