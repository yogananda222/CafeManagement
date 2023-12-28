import { TestBed } from '@angular/core/testing';
import { Admin } from './admin';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

describe('Admin', () => {
  let service: Admin;
  let httpClient: HttpClient;
  let observableInstance: Observable<any>; // Replace 'any' with the type you expect

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [Admin, HttpClient],
    });

    // Inject the required services
    service = TestBed.inject(Admin);
    httpClient = TestBed.inject(HttpClient);
    observableInstance = TestBed.inject(Observable);
  });

  it('should create an instance', () => {
    expect(service).toBeTruthy();
  });
});
