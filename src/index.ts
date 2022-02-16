import { registerPlugin } from '@capacitor/core';

import type { SimpleSimPlugin } from './definitions';

const SimpleSim = registerPlugin<SimpleSimPlugin>('SimpleSim', {
  web: () => import('./web').then(m => new m.SimpleSimWeb()),
});

export * from './definitions';
export { SimpleSim };
