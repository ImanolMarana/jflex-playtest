// test: caseless
/*
 * Copyright 2020, Google, LLC.
 * SPDX-License-Identifier: BSD-3-Clause
 */

package de.jflex.testcase.caseless_jflex;

import de.jflex.testing.testsuite.golden.AbstractGoldenTest;
import de.jflex.testing.testsuite.golden.GoldenInOutFilePair;
import de.jflex.util.scanner.ScannerFactory;
import java.io.File;
import org.junit.Test;

/**
 * tests %ignorecase with jflex semantics (only strings and chars are caseless)
 *
 * <p>Note: This test was generated from {@code jflex-testsuite-maven-plugin} test cases. The test
 * relies on golden files for testing, expecting the scanner to output logs on the {@code
 * System.out}. Please migrate to proper unit tests, as describe in <a
 * href="https://github.com/jflex-de/jflex/tree/master/javatests/de/jflex/testcase">
 * //javatest/jflex/testcase</a>.
 */
// TODO Migrate this test to proper unit tests.
public class CaselessGoldenTest extends AbstractGoldenTest {

  /** Creates a scanner conforming to the {@code caseless.flex} specification. */
  private final ScannerFactory<CaselessScanner> scannerFactory =
      ScannerFactory.of(CaselessScanner::new);

  private final File testRuntimeDir = new File("javatests/de/jflex/testcase/caseless_jflex");

  @Test
  public void goldenTest0() throws Exception {
    GoldenInOutFilePair golden =
        new GoldenInOutFilePair(
            new File(testRuntimeDir, "caseless-0.input"),
            new File(testRuntimeDir, "caseless-0.output"));
    compareSystemOutWith(golden);

    CaselessScanner scanner = scannerFactory.createScannerForFile(golden.inputFile);
    scanner.yylex();
  }
}
