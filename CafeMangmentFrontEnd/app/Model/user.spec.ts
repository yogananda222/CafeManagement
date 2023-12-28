import { TestBed } from '@angular/core/testing';

import { Observable } from 'rxjs/internal/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';

describe('User', () => {
  let service: User;
  let httpClient: HttpClient;
  let observableInstance: Observable<any>; 

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [User, HttpClient],
    });

    // Inject the required services
    service = TestBed.inject(User);
    httpClient = TestBed.inject(HttpClient);
    observableInstance = TestBed.inject(Observable);
  });

  it('should create an instance', () => {
    expect(service).toBeTruthy();
  });
});
