import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'agence',
        loadChildren: () => import('./agence/agence.module').then(m => m.JhipsterSampleApplicationAgenceModule)
      },
      {
        path: 'type-aer',
        loadChildren: () => import('./type-aer/type-aer.module').then(m => m.JhipsterSampleApplicationTypeAerModule)
      },
      {
        path: 'statut-disponibilite',
        loadChildren: () =>
          import('./statut-disponibilite/statut-disponibilite.module').then(m => m.JhipsterSampleApplicationStatutDisponibiliteModule)
      },
      {
        path: 'statut',
        loadChildren: () => import('./statut/statut.module').then(m => m.JhipsterSampleApplicationStatutModule)
      },
      {
        path: 'groupe',
        loadChildren: () => import('./groupe/groupe.module').then(m => m.JhipsterSampleApplicationGroupeModule)
      },
      {
        path: 'client',
        loadChildren: () => import('./client/client.module').then(m => m.JhipsterSampleApplicationClientModule)
      },
      {
        path: 'aer',
        loadChildren: () => import('./aer/aer.module').then(m => m.JhipsterSampleApplicationAerModule)
      },
      {
        path: 'disponibilite',
        loadChildren: () => import('./disponibilite/disponibilite.module').then(m => m.JhipsterSampleApplicationDisponibiliteModule)
      },
      {
        path: 'assistance-technique',
        loadChildren: () =>
          import('./assistance-technique/assistance-technique.module').then(m => m.JhipsterSampleApplicationAssistanceTechniqueModule)
      },
      {
        path: 'utilisateur',
        loadChildren: () => import('./utilisateur/utilisateur.module').then(m => m.JhipsterSampleApplicationUtilisateurModule)
      },
      {
        path: 'role',
        loadChildren: () => import('./role/role.module').then(m => m.JhipsterSampleApplicationRoleModule)
      },
      {
        path: 'format-date',
        loadChildren: () => import('./format-date/format-date.module').then(m => m.JhipsterSampleApplicationFormatDateModule)
      },
      {
        path: 'rv',
        loadChildren: () => import('./rv/rv.module').then(m => m.JhipsterSampleApplicationRvModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationEntityModule {}
