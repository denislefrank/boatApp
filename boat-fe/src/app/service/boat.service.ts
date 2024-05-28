import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class BoatService {
    private apiUrl = 'http://localhost:8080/boat';

    constructor(private http: HttpClient) { }

    getBoats(): Observable<any[]> {
        return this.http.get<any[]>(this.apiUrl);
    }
}
