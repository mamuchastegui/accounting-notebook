import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

   transactionsUrl = 'http://localhost:8080/transactions';

  constructor(private http: HttpClient) { }

  listTransactions() {
    return this.http.get(this.transactionsUrl);
  }

}
