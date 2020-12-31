import {Component, OnInit} from '@angular/core';
import {TransactionService} from '../transaction.service';
import {Transaction} from '../models/transaction';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit {

  transactions: Array<Transaction> = [];
  constructor(private transactionService: TransactionService) {
  }

  ngOnInit(): void {
    this.list();
  }

  list() {
    const resp = this.transactionService.listTransactions();
    resp.subscribe((data: any) => {
        if (data.length > 0) {
          this.transactions = data;
          (async () => {
            for (let i = 0; i < 5; i++) {
              await this.delay(50);
              this.applyAccordionStyle();
            }
          })();
        } else {
        }
      }
    );
  }

  applyAccordionStyle() {
    for (const t of this.transactions) {
      const panelId = `accordion-panel-${t.id}-header`;
      const activePanelElem = document.getElementById(panelId);
      console.log(activePanelElem);
      if (activePanelElem) {
        activePanelElem.classList.add(t.type === 'debit' ? 'bg-info' : 'bg-success');
      }
    }
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

}
