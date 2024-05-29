import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Boat } from '../components/boat/boat';

@Injectable({
    providedIn: 'root'
})
export class BoatService {
    private apiUrl = 'http://localhost:8080/boat';

    constructor(private http: HttpClient) { }

    getBoats(): Observable<any[]> {
        return this.http.get<Boat[]>(this.apiUrl);
    }

    addBoat(boat: Boat): Observable<Boat> {
        return this.http.post<Boat>(this.apiUrl, boat);
    }

    updateBoat(boat: Boat): Observable<Boat> {
        return this.http.put<Boat>(`${this.apiUrl}`, boat);
    }

    deleteBoat(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
