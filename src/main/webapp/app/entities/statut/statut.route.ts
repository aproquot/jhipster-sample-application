import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Statut } from 'app/shared/model/statut.model';
import { StatutService } from './statut.service';
import { StatutComponent } from './statut.component';
import { StatutDetailComponent } from './statut-detail.component';
import { StatutUpdateComponent } from './statut-update.component';
import { StatutDeletePopupComponent } from './statut-delete-dialog.component';
import { IStatut } from 'app/shared/model/statut.model';

@Injectable({ providedIn: 'root' })
export class StatutResolve implements Resolve<IStatut> {
  constructor(private service: StatutService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IStatut> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Statut>) => response.ok),
        map((statut: HttpResponse<Statut>) => statut.body)
      );
    }
    return of(new Statut());
  }
}

export const statutRoute: Routes = [
  {
    path: '',
    component: StatutComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Statuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: StatutDetailComponent,
    resolve: {
      statut: StatutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Statuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: StatutUpdateComponent,
    resolve: {
      statut: StatutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Statuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: StatutUpdateComponent,
    resolve: {
      statut: StatutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Statuts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const statutPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: StatutDeletePopupComponent,
    resolve: {
      statut: StatutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Statuts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
