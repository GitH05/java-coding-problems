
import { CommonModule } from '@angular/common';
import {
  Component,
  OnInit,
  inject,
  ChangeDetectorRef
} from '@angular/core'; 
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { User } from '../../core/models/user.model';
import { UserService } from '../../core/services/user.service';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss'
})
export class UsersComponent implements OnInit {

  private fb = inject(FormBuilder);
  private userService = inject(UserService);
  private cdr = inject(ChangeDetectorRef);

  users: User[] = [];
  loading = true;
  saving = false;
  modalOpen = false;
  editingId: number | null = null;

  selectedFile: File | null = null;
  preview: string | null = null;

  search = '';
  statusFilter: 'all' | 'active' | 'inactive' = 'all';

  page = 1;
  pageSize = 8;

  toast: { kind: 'success' | 'error'; message: string } | null = null;
  private toastTimer?: ReturnType<typeof setTimeout>;

  form = this.fb.group({
    first_name: ['', [Validators.required, Validators.maxLength(60)]],
    last_name: ['', [Validators.required, Validators.maxLength(60)]],
    email: ['', [Validators.required, Validators.email]],
    password: ['']
  });

  ngOnInit(): void {
    this.loadUsers();
  }

loadUsers(): void {
  this.loading = true;
  this.cdr.detectChanges();

  this.userService.getAll().subscribe({
    next: (res) => {
      this.users = res?.data ?? [];
      this.page = 1;
      this.loading = false;

      this.cdr.detectChanges();
    },
    error: (err) => {
      console.error('API ERROR:', err);
      this.loading = false;

      this.notify(
        'error',
        err?.error?.message || 'Could not load users.'
      );

      this.cdr.detectChanges();
    }
  });
}

  get activeCount(): number {
    return this.users.filter(u => u.active).length;
  }

  get inactiveCount(): number {
    return this.users.length - this.activeCount;
  }

  get filteredUsers(): User[] {
    const query = this.search.trim().toLowerCase();

    return this.users.filter(user => {
      const matchesQuery =
        !query ||
        `${user.firstName} ${user.lastName} ${user.email}`
          .toLowerCase()
          .includes(query);

      const matchesStatus =
        this.statusFilter === 'all' ||
        (this.statusFilter === 'active' ? user.active : !user.active);

      return matchesQuery && matchesStatus;
    });
  }

  get pageCount(): number {
    return Math.max(1, Math.ceil(this.filteredUsers.length / this.pageSize));
  }

  get pageNumbers(): number[] {
    return Array.from({ length: this.pageCount }, (_, i) => i + 1);
  }

  get pagedUsers(): User[] {
    const start = (this.page - 1) * this.pageSize;
    return this.filteredUsers.slice(start, start + this.pageSize);
  }

  get rangeStart(): number {
    return this.filteredUsers.length
      ? (this.page - 1) * this.pageSize + 1
      : 0;
  }

  get rangeEnd(): number {
    return Math.min(
      this.page * this.pageSize,
      this.filteredUsers.length
    );
  }

  setSearch(value: string): void {
    this.search = value;
    this.page = 1;
  }

  setStatus(value: 'all' | 'active' | 'inactive'): void {
    this.statusFilter = value;
    this.page = 1;
  }

  goToPage(page: number): void {
    this.page = Math.min(Math.max(page, 1), this.pageCount);
  }

  openCreate(): void {
    this.editingId = null;
    this.selectedFile = null;
    this.preview = null;

    this.form.reset({
      first_name: '',
      last_name: '',
      email: '',
      password: ''
    });

    this.form.controls.password.setValidators([
      Validators.required,
      Validators.minLength(6)
    ]);

    this.form.controls.password.updateValueAndValidity();

    this.modalOpen = true;
  }

  openEdit(user: User): void {
    this.editingId = user.id;
    this.selectedFile = null;
    this.preview = this.userService.imageUrl(user.profileImage);

    this.form.patchValue({
      first_name: user.firstName,
      last_name: user.lastName,
      email: user.email,
      password: ''
    });

    this.form.controls.password.clearValidators();
    this.form.controls.password.updateValueAndValidity();

    this.modalOpen = true;
  }

  closeModal(): void {
    if (!this.saving) {
      this.modalOpen = false;
      this.selectedFile = null;
      this.preview = null;
    }
  }

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    const file = input.files?.[0];

    if (!file) return;

    if (!file.type.startsWith('image/')) {
      this.notify('error', 'Please choose an image file.');
      input.value = '';
      return;
    }

    if (file.size > 5 * 1024 * 1024) {
      this.notify('error', 'Image must be smaller than 5 MB.');
      input.value = '';
      return;
    }

    this.selectedFile = file;

    const reader = new FileReader();

    reader.onload = () => {
      this.preview = String(reader.result);
    };

    reader.readAsDataURL(file);
  }

  save(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const isCreate = this.editingId === null;
    const currentId = this.editingId;

    const data = new FormData();

    data.append('first_name', this.form.value.first_name ?? '');
    data.append('last_name', this.form.value.last_name ?? '');
    data.append('email', this.form.value.email ?? '');

    if (isCreate) {
      data.append('password', this.form.value.password ?? '');
    }

    if (this.selectedFile) {
      data.append('image', this.selectedFile);
    }

    this.saving = true;

    const request = isCreate
      ? this.userService.create(data)
      : this.userService.update(currentId!, data);

    request.subscribe({
      next: () => {
        this.saving = false;
        this.modalOpen = false;

        this.notify(
          'success',
          isCreate
            ? 'User created successfully.'
            : 'Changes saved successfully.'
        );

        this.loadUsers();
      },
      error: (err) => {
        this.saving = false;

        this.notify(
          'error',
          err?.error?.message || 'Could not save user. Please try again.'
        );
      }
    });
  }

  deleteUser(user: User): void {
    if (
      !confirm(
        `Delete ${user.firstName} ${user.lastName}? This action cannot be undone.`
      )
    ) {
      return;
    }

    this.userService.delete(user.id).subscribe({
      next: () => {
        this.notify('success', 'User removed.');
        this.loadUsers();
      },
      error: (err) => {
        this.notify(
          'error',
          err?.error?.message || 'Unable to delete this user.'
        );
      }
    });
  }

  imageUrl(filename: string | null): string | null {
    return this.userService.imageUrl(filename);
  }

  initials(user: User): string {
    return `${user.firstName?.[0] ?? ''}${user.lastName?.[0] ?? ''}`
      .toUpperCase();
  }

  notify(
    kind: 'success' | 'error',
    message: string
  ): void {
    this.toast = { kind, message };

    if (this.toastTimer) {
      clearTimeout(this.toastTimer);
    }

    this.toastTimer = setTimeout(() => {
      this.toast = null;
    }, 3800);
  }
}
