import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Boat } from '../boat';

@Component({
  selector: 'app-boat-delete',
  templateUrl: './boat-delete.component.html',
  styleUrls: ['./boat-delete.component.css']
})
export class BoatDeleteComponent {
  @Input() boat: Boat = { id: 0, name: '', description: '' };
  @Output() onConfirm = new EventEmitter<void>();



  confirmDelete() {
    this.onConfirm.emit();
  }
}