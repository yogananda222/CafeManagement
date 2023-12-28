import { TestBed } from '@angular/core/testing';
import { Food } from './food';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

describe('Food', () => {
  let service: Food;
  let httpClient: HttpClient;
  let observableInstance: Observable<any>; // Replace 'any' with the type you expect

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [Food, HttpClient],
    });

    // Inject the required services
    service = TestBed.inject(Food);
    httpClient = TestBed.inject(HttpClient);
    observableInstance = TestBed.inject(Observable);
  });

  it('should create an instance', () => {
    expect(service).toBeTruthy();
  });
});
