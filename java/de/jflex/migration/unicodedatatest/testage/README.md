<!--
  Copyright 2023, Gerwin Klein, Régis Décamps, Steve Rowe
  SPDX-License-Identifier: CC-BY-SA-4.0
-->

# Generator of UnicodeAgeTest_x_y

## Usage

### Generate for one version

To create the test for Unicode 6.1

    bazel run //java/de/jflex/migration/unicodedatatest/testage:generator -- 6.1 "$(git rev-parse --show-toplevel)"

### Generate all versions

    bazel run //java/de/jflex/migration/unicodedatatest/testage:generate

