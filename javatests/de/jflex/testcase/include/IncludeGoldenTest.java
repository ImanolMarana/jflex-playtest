// test: include

/*
 * Copyright 2020, Google, LLC.
 * SPDX-License-Identifier: BSD-3-Clause
 */

package de.jflex.testcase.include;

import de.jflex.testing.testsuite.golden.AbstractGoldenTest;
import de.jflex.testing.testsuite.golden.GoldenInOutFilePair;
import de.jflex.util.scanner.ScannerFactory;
import java.io.File;
import org.junit.Test;

/**
 * tests include files, i.e. <a href="https://github.com/jflex-de/jflex/issues/51">yyPushStream and
 * yyPopStream bug</a>
 *
 * <p>Note: This test was generated from {@code jflex-testsuite-maven-plugin} test cases. The test
 * relies on golden files for testing, expecting the scanner to output logs on the {@code
 * System.out}. Please migrate to proper unit tests, as describe in <a
 * href="https://github.com/jflex-de/jflex/tree/master/javatests/de/jflex/testcase">
 * //javatest/jflex/testcase</a>.
 */
// TODO Migrate this test to proper unit tests.
public class IncludeGoldenTest extends AbstractGoldenTest {

  /** Creates a scanner conforming to the {@code include.flex} specification. */
  private final ScannerFactory<IncludeScanner> scannerFactory =
      ScannerFactory.of(IncludeScanner::new);

  private final File testRuntimeDir = new File("javatests/de/jflex/testcase/include");

  @Test
  public void goldenTest0() throws Exception {
    GoldenInOutFilePair golden =
        new GoldenInOutFilePair(
            new File(testRuntimeDir, "include-0.input"),
            new File(testRuntimeDir, "include-0.output"));
    compareSystemOutWith(golden);

    IncludeScanner scanner = scannerFactory.createScannerForFile(golden.inputFile);
    while (!scanner.yyatEOF()) {
      System.out.println(scanner.yylex());
    }
  }
}
