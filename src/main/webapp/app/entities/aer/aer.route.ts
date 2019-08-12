import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Aer } from 'app/shared/model/aer.model';
import { AerService } from './aer.service';
import { AerComponent } from './aer.component';
import { AerDetailComponent } from './aer-detail.component';
import { AerUpdateComponent } from './aer-update.component';
import { AerDeletePopupComponent } from './aer-delete-dialog.component';
import { IAer } from 'app/shared/model/aer.model';

@Injectable({ providedIn: 'root' })
export class AerResolve implements Resolve<IAer> {
  constructor(private service: AerService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAer> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Aer>) => response.ok),
        map((aer: HttpResponse<Aer>) => aer.body)
      );
    }
    return of(new Aer());
  }
}

export const aerRoute: Routes = [
  {
    path: '',
    component: AerComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Aers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AerDetailComponent,
    resolve: {
      aer: AerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Aers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AerUpdateComponent,
    resolve: {
      aer: AerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Aers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AerUpdateComponent,
    resolve: {
      aer: AerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Aers'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const aerPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AerDeletePopupComponent,
    resolve: {
      aer: AerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Aers'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
