export interface SimpleSimPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
