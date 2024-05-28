import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authUrl = 'http://localhost:8080/auth';
  private loggedIn = false;

  constructor(private http: HttpClient) { }

  register(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.authUrl}/register`, { username, password });
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.authUrl}/login`, { username, password })
      .pipe(
        tap((response: { token: string; }) => {
          localStorage.setItem('token', response.token);
          this.loggedIn = true;
        })
      );
  }

  logout(): void {
    localStorage.removeItem('token');
    this.loggedIn = false;
  }

  isLoggedIn(): boolean {
    return this.loggedIn || !!localStorage.getItem('token');
  }
}