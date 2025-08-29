import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatFormFieldModule,
    MatInputModule,
    RouterModule,
    MatButtonModule,
    MatSnackBarModule
  ],
  templateUrl: './employee-form.html',
  styleUrls: ['./employee-form.css']
})
export class EmployeeForm {
  employee = {
    name: '',
    position: '',
    department: ''
  };

  constructor(private http: HttpClient, private snackBar: MatSnackBar) {}

  addEmployee() {
    if (!this.employee.name.trim() || !this.employee.position.trim() || !this.employee.department.trim()) {
      this.snackBar.open('⚠️ Please fill all fields before submitting!', 'Close', {
        duration: 3000,      // auto-close after 3s
        panelClass: ['error-snackbar']
      });
      return;
    }

    this.http.post('/api/employees', this.employee)
      .subscribe({
        next: (res) => {
          console.log('Employee added:', res);
          this.snackBar.open('✅ Employee added successfully!', 'Close', {
            duration: 3000,
            panelClass: ['success-snackbar']
          });
          this.employee = { name: '', position: '', department: '' };
        },
        error: (err) => {
          console.error('Error:', err);
          this.snackBar.open('❌ Failed to add employee!', 'Close', {
            duration: 3000,
            panelClass: ['error-snackbar']
          });
        }
      });
  }
}
