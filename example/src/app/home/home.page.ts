import { Component } from '@angular/core';
import { SimpleSim } from 'capacitor-simple-sim'

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  sims: { id: number; name: string; displayName:string; mnc: number; mcc: number }[];
  simIndex: number = null;
  phoneNumber: string;

  constructor() {
    this.getSims();
  }

  async getSims() {
    this.sims = (await SimpleSim.getSims()).result;
    console.log(this.sims);
  }

  async call() {
    return SimpleSim.callNumber({ simIndex: this.simIndex, phoneNumber: this.phoneNumber })
  }
}
