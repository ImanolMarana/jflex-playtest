#!/bin/bash
#
# Copyright 2022, Gerwin Klein, Régis Décamps, Steven Rowe
# SPDX-License-Identifier: BSD-3-Clause
#

CWD="$PWD"
BASEDIR="$(cd "$(dirname "$0")" && pwd -P)"/..
# Provides the logi function
source "$BASEDIR"/scripts/logger.sh
# fail on error
set -e

if [[ "${CI}" ]]; then
  loge "This script is only for manual invocation"
  exit 1
fi

# Clean environment
"$BASEDIR"/scripts/clean.sh

logi "Test *** ${TEST_SUITE}"

# CI then runs _in parallel_ (but we do it in sequence on dev machine)

if [[ -z "$TEST_SUITE" || "$TEST_SUITE" == "java-format" ]]; then
  "$BASEDIR"/scripts/test-java-format.sh
fi
if [[ ! "${CI}" || -z "$TEST_SUITE" ]]; then
  buildifier -r=true .
elif [[ "${CI}" && "$TEST_SUITE" == "bzl-format"  ]]; then
  "$BASEDIR"/scripts/test-bzl-format.sh
fi
if [[ -z "$TEST_SUITE" || "$TEST_SUITE" == "unit" ]]; then
  "$BASEDIR"/scripts/test-unit.sh
fi
if [[ -z "$TEST_SUITE" || "$TEST_SUITE" == "regression" ]]; then
  "$BASEDIR"/scripts/test-regression.sh
fi
if [[ -z "$TEST_SUITE" || "$TEST_SUITE" == "ant" ]]; then
  "$BASEDIR"/scripts/ant-build.sh
  "$BASEDIR"/scripts/test-examples.sh
fi

logi "Success"
cd "$CWD"
