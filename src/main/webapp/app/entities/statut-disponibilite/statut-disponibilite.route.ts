import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StatutDisponibilite } from 'app/shared/model/statut-disponibilite.model';
import { StatutDisponibiliteService } from './statut-disponibilite.service';
import { StatutDisponibiliteComponent } from './statut-disponibilite.component';
import { StatutDisponibiliteDetailComponent } from './statut-disponibilite-detail.component';
import { StatutDisponibiliteUpdateComponent } from './statut-disponibilite-update.component';
import { StatutDisponibiliteDeletePopupComponent } from './statut-disponibilite-delete-dialog.component';
import { IStatutDisponibilite } from 'app/shared/model/statut-disponibilite.model';

@Injectable({ providedIn: 'root' })
export class StatutDisponibiliteResolve implements Resolve<IStatutDisponibilite> {
  constructor(private service: StatutDisponibiliteService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IStatutDisponibilite> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<StatutDisponibilite>) => response.ok),
        map((statutDisponibilite: HttpResponse<StatutDisponibilite>) => statutDisponibilite.body)
      );
    }
    return of(new StatutDisponibilite());
  }
}

export const statutDisponibiliteRoute: Routes = [
  {
    path: '',
    component: StatutDisponibiliteComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'StatutDisponibilites'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: StatutDisponibiliteDetailComponent,
    resolve: {
      statutDisponibilite: StatutDisponibiliteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'StatutDisponibilites'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: StatutDisponibiliteUpdateComponent,
    resolve: {
      statutDisponibilite: StatutDisponibiliteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'StatutDisponibilites'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: StatutDisponibiliteUpdateComponent,
    resolve: {
      statutDisponibilite: StatutDisponibiliteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'StatutDisponibilites'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const statutDisponibilitePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: StatutDisponibiliteDeletePopupComponent,
    resolve: {
      statutDisponibilite: StatutDisponibiliteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'StatutDisponibilites'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
