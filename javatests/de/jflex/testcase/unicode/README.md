<!--
  Copyright 2023, Gerwin Klein, Régis Décamps, Steve Rowe
  SPDX-License-Identifier: CC-BY-SA-4.0
-->

# End-to-end tests on Unicode data

## Directory layout

```text
javatests/de/jflex/testcase/unicode  All tests related to Unicode supported.
├── unicode_x_y
│   └── *Test.java  Tests for version x.y
├── *Test.java      Tests unrelated to a specific version.
└── README.md       This documentation.
```

NB All content in unicode_x_y has been generated by
java/de/jflex/migration/unicodedatatest
