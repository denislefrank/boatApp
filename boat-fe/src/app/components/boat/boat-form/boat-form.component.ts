import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { BoatService } from '../../../service/boat.service';
import { Boat } from '../boat';

declare var M: any;

@Component({
  selector: 'app-boat-form',
  templateUrl: './boat-form.component.html',
  styleUrls: ['./boat-form.component.css']
})
export class BoatFormComponent {
  @Input() boat: Boat = { id: 0, name: '', description: '' };
  @Output() boatAdded = new EventEmitter<void>();
  @Output() boatUpdated = new EventEmitter<void>();
  viewMode: boolean = false;

  constructor(private boatService: BoatService) { }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['boat'] && changes['boat'].currentValue) {
      this.boat = { ...changes['boat'].currentValue };
    }
  }

  onSubmit() {
    if (this.boat.id) {
      this.boatService.updateBoat(this.boat).subscribe(() => {
        this.boatUpdated.emit();
        this.closeModal();
        console.log("updated");
      });
    } else {
      this.boatService.addBoat(this.boat).subscribe(() => {
        this.boatAdded.emit();
        this.closeModal();
      });
    }
  }

  closeModal() {
    const modalElement = document.querySelector('#boatFormModal');
    const modalInstance = M.Modal.getInstance(modalElement);
    modalInstance.close();
  }

  openModal(viewMode: boolean = false) {
    this.viewMode = viewMode;
    const modalElement = document.querySelector('#boatFormModal');
    const modalInstance = M.Modal.getInstance(modalElement);
    modalInstance.open();
  }
}
