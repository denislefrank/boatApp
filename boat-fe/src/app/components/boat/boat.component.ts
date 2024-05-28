import { Component } from '@angular/core';
import { BoatService } from 'src/app/service/boat.service';

@Component({
  selector: 'app-boat',
  templateUrl: './boat.component.html',
  styleUrls: ['./boat.component.css']
})
export class BoatComponent {
  boats: any[] = [];

  constructor(private boatService: BoatService) { }

  ngOnInit(): void {
    this.boatService.getBoats().subscribe({
      next: (data) => this.boats = data,
      error: (err) => console.error('Error fetching boats', err)
    });
  }
}
