import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatDeleteComponent } from './boat-delete.component';

describe('BoatDeleteComponent', () => {
  let component: BoatDeleteComponent;
  let fixture: ComponentFixture<BoatDeleteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BoatDeleteComponent]
    });
    fixture = TestBed.createComponent(BoatDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
