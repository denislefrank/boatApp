import { Component } from '@angular/core';
import { BoatService } from 'src/app/service/boat.service';
import { Boat } from './boat';

declare var M: any;

@Component({
  selector: 'app-boat',
  templateUrl: './boat.component.html',
  styleUrls: ['./boat.component.css']
})
export class BoatComponent {
  boats: Boat[] = [];
  selectedBoat: Boat = { id: 0, name: '', description: '' };

  constructor(private boatService: BoatService) { }

  ngOnInit(): void {
    this.getBoats();
    document.addEventListener('DOMContentLoaded', function () {
      const modalElement = document.querySelectorAll('.modal');
      M.Modal.init(modalElement);
    });
  }

  getBoats(): void {
    this.boatService.getBoats().subscribe({
      next: (data) => this.boats = data,
      error: (err) => console.error('Error fetching boats', err)
    });
  }

  openNewBoatModal(): void {
    this.selectedBoat = { id: 0, name: '', description: '' };
    const modalElement = document.querySelector('#addBoatModal');
    const modalInstance = M.Modal.getInstance(modalElement);
    modalInstance.open();
  }

  openEditBoatModal(boat: Boat): void {
    this.selectedBoat = { ...boat };
    const modalElement = document.querySelector('#addBoatModal');
    const modalInstance = M.Modal.getInstance(modalElement);
    modalInstance.open();
  }

  openDeleteBoatModal(boat: Boat): void {
    this.selectedBoat = { ...boat };
    const modalElement = document.querySelector('#confirmDeleteModal');
    const modalInstance = M.Modal.getInstance(modalElement);
    modalInstance.open();
  }

  deleteBoat(): void {
    if (this.selectedBoat.id) {
      this.boatService.deleteBoat(this.selectedBoat.id).subscribe(() => {
        this.getBoats();
      });
    }
  }
}
