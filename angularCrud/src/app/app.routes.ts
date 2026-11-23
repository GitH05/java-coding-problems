import { Routes } from '@angular/router';
import { UsersComponent } from './pages/users/users.component';
export const routes: Routes = [{ path: '', component: UsersComponent }, { path: '**', redirectTo: '' }];
