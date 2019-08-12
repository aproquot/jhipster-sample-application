import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStatutDisponibilite } from 'app/shared/model/statut-disponibilite.model';

type EntityResponseType = HttpResponse<IStatutDisponibilite>;
type EntityArrayResponseType = HttpResponse<IStatutDisponibilite[]>;

@Injectable({ providedIn: 'root' })
export class StatutDisponibiliteService {
  public resourceUrl = SERVER_API_URL + 'api/statut-disponibilites';

  constructor(protected http: HttpClient) {}

  create(statutDisponibilite: IStatutDisponibilite): Observable<EntityResponseType> {
    return this.http.post<IStatutDisponibilite>(this.resourceUrl, statutDisponibilite, { observe: 'response' });
  }

  update(statutDisponibilite: IStatutDisponibilite): Observable<EntityResponseType> {
    return this.http.put<IStatutDisponibilite>(this.resourceUrl, statutDisponibilite, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStatutDisponibilite>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStatutDisponibilite[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
