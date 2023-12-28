import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookaslotComponent } from './bookaslot.component';

describe('BookaslotComponent', () => {
  let component: BookaslotComponent;
  let fixture: ComponentFixture<BookaslotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BookaslotComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BookaslotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
