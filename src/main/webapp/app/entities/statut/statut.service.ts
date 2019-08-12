import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStatut } from 'app/shared/model/statut.model';

type EntityResponseType = HttpResponse<IStatut>;
type EntityArrayResponseType = HttpResponse<IStatut[]>;

@Injectable({ providedIn: 'root' })
export class StatutService {
  public resourceUrl = SERVER_API_URL + 'api/statuts';

  constructor(protected http: HttpClient) {}

  create(statut: IStatut): Observable<EntityResponseType> {
    return this.http.post<IStatut>(this.resourceUrl, statut, { observe: 'response' });
  }

  update(statut: IStatut): Observable<EntityResponseType> {
    return this.http.put<IStatut>(this.resourceUrl, statut, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStatut>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStatut[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
