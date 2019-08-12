import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FormatDate } from 'app/shared/model/format-date.model';
import { FormatDateService } from './format-date.service';
import { FormatDateComponent } from './format-date.component';
import { FormatDateDetailComponent } from './format-date-detail.component';
import { FormatDateUpdateComponent } from './format-date-update.component';
import { FormatDateDeletePopupComponent } from './format-date-delete-dialog.component';
import { IFormatDate } from 'app/shared/model/format-date.model';

@Injectable({ providedIn: 'root' })
export class FormatDateResolve implements Resolve<IFormatDate> {
  constructor(private service: FormatDateService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFormatDate> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<FormatDate>) => response.ok),
        map((formatDate: HttpResponse<FormatDate>) => formatDate.body)
      );
    }
    return of(new FormatDate());
  }
}

export const formatDateRoute: Routes = [
  {
    path: '',
    component: FormatDateComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'FormatDates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FormatDateDetailComponent,
    resolve: {
      formatDate: FormatDateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'FormatDates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FormatDateUpdateComponent,
    resolve: {
      formatDate: FormatDateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'FormatDates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FormatDateUpdateComponent,
    resolve: {
      formatDate: FormatDateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'FormatDates'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const formatDatePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FormatDateDeletePopupComponent,
    resolve: {
      formatDate: FormatDateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'FormatDates'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
