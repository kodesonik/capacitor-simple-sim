import { WebPlugin } from '@capacitor/core';

import type { SimpleSimPlugin } from './definitions';

export class SimpleSimWeb extends WebPlugin implements SimpleSimPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
