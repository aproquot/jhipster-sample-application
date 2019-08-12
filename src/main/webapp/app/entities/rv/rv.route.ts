import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Rv } from 'app/shared/model/rv.model';
import { RvService } from './rv.service';
import { RvComponent } from './rv.component';
import { RvDetailComponent } from './rv-detail.component';
import { RvUpdateComponent } from './rv-update.component';
import { RvDeletePopupComponent } from './rv-delete-dialog.component';
import { IRv } from 'app/shared/model/rv.model';

@Injectable({ providedIn: 'root' })
export class RvResolve implements Resolve<IRv> {
  constructor(private service: RvService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRv> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Rv>) => response.ok),
        map((rv: HttpResponse<Rv>) => rv.body)
      );
    }
    return of(new Rv());
  }
}

export const rvRoute: Routes = [
  {
    path: '',
    component: RvComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Rvs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RvDetailComponent,
    resolve: {
      rv: RvResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Rvs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RvUpdateComponent,
    resolve: {
      rv: RvResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Rvs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RvUpdateComponent,
    resolve: {
      rv: RvResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Rvs'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const rvPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: RvDeletePopupComponent,
    resolve: {
      rv: RvResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Rvs'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
