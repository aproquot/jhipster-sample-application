import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITypeAer } from 'app/shared/model/type-aer.model';

type EntityResponseType = HttpResponse<ITypeAer>;
type EntityArrayResponseType = HttpResponse<ITypeAer[]>;

@Injectable({ providedIn: 'root' })
export class TypeAerService {
  public resourceUrl = SERVER_API_URL + 'api/type-aers';

  constructor(protected http: HttpClient) {}

  create(typeAer: ITypeAer): Observable<EntityResponseType> {
    return this.http.post<ITypeAer>(this.resourceUrl, typeAer, { observe: 'response' });
  }

  update(typeAer: ITypeAer): Observable<EntityResponseType> {
    return this.http.put<ITypeAer>(this.resourceUrl, typeAer, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeAer>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeAer[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
