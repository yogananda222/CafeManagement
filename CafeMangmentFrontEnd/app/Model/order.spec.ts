import { TestBed } from '@angular/core/testing';
import { OrderService } from './order';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

describe('order', () => {
  let service: OrderService;
  let httpClient: HttpClient;
  let observableInstance: Observable<any>; // Replace 'any' with the type you expect

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OrderService, HttpClient],
    });

    // Inject the required services
    service = TestBed.inject(OrderService);
    httpClient = TestBed.inject(HttpClient);
    observableInstance = TestBed.inject(Observable);
  });

  it('should create an instance', () => {
    expect(service).toBeTruthy();
  });
});
