# Software Patterns

## Abstract Factory
`TerminalFactory` creates the three related products required by a terminal: loading equipment, stability validator, and shipping document. It keeps each terminal family consistent without large conditionals.

## Factory Method
`ManifestRecorder` defines the processing flow and delegates unit creation to `createUnit()`. The three recorder classes parse their own manifest format and calculate weight according to the case rules.

## Builder
`StowagePlan.Builder` creates the immutable `StowagePlan` while validating all required fields. It also makes optional plan data easy to add without a long constructor.

These are the three required patterns used in the solution.
