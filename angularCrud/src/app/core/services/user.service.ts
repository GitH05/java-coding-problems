
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User, UsersResponse } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly api = 'http://localhost:8080/api/users';
  private readonly uploads = 'http://localhost:8080/uploads';

  constructor(private http: HttpClient) {}

  getAll(): Observable<UsersResponse> {
    return this.http.get<UsersResponse>(this.api);
  }

  getById(id: number): Observable<User> {
    return this.http.get<User>(`${this.api}/${id}`);
  }

  create(data: FormData): Observable<User> {
    return this.http.post<User>(this.api, data);
  }

  update(id: number, data: FormData): Observable<User> {
    return this.http.put<User>(`${this.api}/${id}`, data);
  }

  delete(id: number): Observable<unknown> {
    return this.http.delete(`${this.api}/${id}`);
  }

  imageUrl(filename: string | null): string | null {
    return filename
      ? `${this.uploads}/${encodeURIComponent(filename)}`
      : null;
  }
}
