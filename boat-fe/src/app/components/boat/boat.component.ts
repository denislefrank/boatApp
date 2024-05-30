import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { BoatService } from 'src/app/service/boat.service';
import { Boat } from './boat';
import { BoatFormComponent } from './boat-form/boat-form.component';
import { AuthService } from 'src/app/service/auth.service';
import { Router } from '@angular/router';

declare var M: any;

@Component({
  selector: 'app-boat',
  templateUrl: './boat.component.html',
  styleUrls: ['./boat.component.css']
})
export class BoatComponent implements OnInit, AfterViewInit {
  boats: Boat[] = [];
  selectedBoat: Boat = { id: 0, name: '', description: '' };

  @ViewChild(BoatFormComponent)
  boatFormComponent!: BoatFormComponent;

  constructor(private boatService: BoatService, private auth: AuthService, private router: Router) { }

  ngAfterViewInit(): void {
    const modals = document.querySelectorAll('.modal');
    M.Modal.init(modals);
  }

  ngOnInit(): void {
    this.auth.isLoggedIn() ? null : this.router.navigate(['/login']);;
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
    this.boatFormComponent.openModal(false);
  }

  openEditBoatModal(boat: Boat): void {
    this.selectedBoat = { ...boat };
    this.boatFormComponent.openModal(false);
  }

  openViewBoatModal(boat: Boat): void {
    this.selectedBoat = { ...boat };
    this.boatFormComponent.openModal(true);
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
