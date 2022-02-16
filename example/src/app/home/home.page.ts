import { Component } from '@angular/core';
import { SimpleSim } from 'capacitor-simple-sim'

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  sims: { id: number; name: string; displayName:string; mnc: number; mcc: number }[];
  constructor() {
    this.getSims();
  }

  async getSims() {
    this.sims = (await SimpleSim.getSims()).result;
    console.log(this.sims);
  }

}
