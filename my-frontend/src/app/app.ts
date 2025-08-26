import { Component, signal } from '@angular/core';
import { RouterOutlet, Routes, provideRouter, RouterLink } from '@angular/router';
import { EmployeeForm } from './employee-form/employee-form';
import { EmployeeList } from './employee-list/employee-list';
import { CommonModule } from '@angular/common';

const routes: Routes = [
  { path: '', redirectTo: 'add', pathMatch: 'full' },  // default
  { path: 'add', component: EmployeeForm},
  { path: 'list', component: EmployeeList}
];

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('my-frontend');
}

export const appConfig = {
  providers: [provideRouter(routes)]
};
