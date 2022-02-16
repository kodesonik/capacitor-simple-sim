export interface SimpleSimPlugin {
  getSims(): Promise<{ result: {id: number, name: string, displayName: string, mnc: number, mcc: number }[] }>;
}
