import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRv } from 'app/shared/model/rv.model';

type EntityResponseType = HttpResponse<IRv>;
type EntityArrayResponseType = HttpResponse<IRv[]>;

@Injectable({ providedIn: 'root' })
export class RvService {
  public resourceUrl = SERVER_API_URL + 'api/rvs';

  constructor(protected http: HttpClient) {}

  create(rv: IRv): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rv);
    return this.http
      .post<IRv>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(rv: IRv): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rv);
    return this.http
      .put<IRv>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRv>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRv[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(rv: IRv): IRv {
    const copy: IRv = Object.assign({}, rv, {
      dateRv: rv.dateRv != null && rv.dateRv.isValid() ? rv.dateRv.format(DATE_FORMAT) : null,
      dateCreation: rv.dateCreation != null && rv.dateCreation.isValid() ? rv.dateCreation.format(DATE_FORMAT) : null,
      dateModification: rv.dateModification != null && rv.dateModification.isValid() ? rv.dateModification.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateRv = res.body.dateRv != null ? moment(res.body.dateRv) : null;
      res.body.dateCreation = res.body.dateCreation != null ? moment(res.body.dateCreation) : null;
      res.body.dateModification = res.body.dateModification != null ? moment(res.body.dateModification) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((rv: IRv) => {
        rv.dateRv = rv.dateRv != null ? moment(rv.dateRv) : null;
        rv.dateCreation = rv.dateCreation != null ? moment(rv.dateCreation) : null;
        rv.dateModification = rv.dateModification != null ? moment(rv.dateModification) : null;
      });
    }
    return res;
  }
}
