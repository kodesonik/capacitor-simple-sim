import { WebPlugin } from '@capacitor/core';

import type { SimpleSimPlugin } from './definitions';

export class SimpleSimWeb extends WebPlugin implements SimpleSimPlugin {
 async getSims(): Promise<{ result: {id: number, name: string, displayName: string, mnc: number, mcc: number }[] }> {
  return { result: [{
    id: 1,
    name:'Name',
    displayName: 'Display Name',
    mnc: 111,
    mcc:1
  }]} 
 }

}
