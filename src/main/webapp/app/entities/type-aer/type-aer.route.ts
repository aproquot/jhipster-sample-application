import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TypeAer } from 'app/shared/model/type-aer.model';
import { TypeAerService } from './type-aer.service';
import { TypeAerComponent } from './type-aer.component';
import { TypeAerDetailComponent } from './type-aer-detail.component';
import { TypeAerUpdateComponent } from './type-aer-update.component';
import { TypeAerDeletePopupComponent } from './type-aer-delete-dialog.component';
import { ITypeAer } from 'app/shared/model/type-aer.model';

@Injectable({ providedIn: 'root' })
export class TypeAerResolve implements Resolve<ITypeAer> {
  constructor(private service: TypeAerService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITypeAer> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TypeAer>) => response.ok),
        map((typeAer: HttpResponse<TypeAer>) => typeAer.body)
      );
    }
    return of(new TypeAer());
  }
}

export const typeAerRoute: Routes = [
  {
    path: '',
    component: TypeAerComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TypeAers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TypeAerDetailComponent,
    resolve: {
      typeAer: TypeAerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TypeAers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TypeAerUpdateComponent,
    resolve: {
      typeAer: TypeAerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TypeAers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TypeAerUpdateComponent,
    resolve: {
      typeAer: TypeAerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TypeAers'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const typeAerPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TypeAerDeletePopupComponent,
    resolve: {
      typeAer: TypeAerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TypeAers'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
