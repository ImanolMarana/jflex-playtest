/*
 * Copyright (C) 2021 Google, LLC.
 * SPDX-License-Identifier: BSD-3-Clause
 */
package de.jflex.testcase.unicode.unicode_6_1;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.ImmutableList;
import de.jflex.testing.unicodedata.AbstractSimpleParser.PatternHandler;
import de.jflex.testing.unicodedata.SimpleIntervalsParser;
import de.jflex.testing.unicodedata.UnicodeDataScanners;
import de.jflex.ucd.NamedCodepointRange;
import de.jflex.util.scanner.ScannerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.annotation.Generated;
import org.junit.BeforeClass;
import org.junit.Test;

/** Test Tests the {@code %caseless} directive for Unicode 6.1. */
@Generated("de.jflex.migration.unicodedatatest.testdigit.UnicodeDigitTestGenerator")
public class UnicodeDigitTest_6_1 {

  private static final Path packageDirectory = Paths.get("javatests/de/jflex/testcase/unicode");

  private static ImmutableList<NamedCodepointRange<Boolean>> expected;

  @BeforeClass
  public static void golden() throws Exception {
    expected = readGolden();
  }

  /**
   * Tests character class syntax of the Unicode 6_1 DecimalDigit property ({@code Nd}) using the
   * {@code [:digit:]} syntax.
   */
  @Test
  public void digit() throws Exception {
    UnicodeDigit_digit_6_1 scanner =
        UnicodeDataScanners.scanAllCodepoints(
            ScannerFactory.of(UnicodeDigit_digit_6_1::new),
            UnicodeDigit_digit_6_1.YYEOF,
            UnicodeDataScanners.Dataset.ALL);
    assertThat(scanner.blocks()).containsExactlyElementsIn(expected);
  }

  /**
   * Tests character class syntax of the Unicode 6_1 DecimalDigit property ({@code Nd}) using the
   * {@code \D} syntax.
   */
  @Test
  public void upperD() throws Exception {
    UnicodeDigit_upperD_6_1 scanner =
        UnicodeDataScanners.scanAllCodepoints(
            ScannerFactory.of(UnicodeDigit_upperD_6_1::new),
            UnicodeDigit_upperD_6_1.YYEOF,
            UnicodeDataScanners.Dataset.ALL);
    assertThat(scanner.blocks()).containsExactlyElementsIn(expected);
  }

  /**
   * Tests character class syntax of the Unicode 6_1 DecimalDigit property ({@code Nd}) using the
   * {@code \d} syntax.
   */
  @Test
  public void lowerD() throws Exception {
    UnicodeDigit_lowerD_6_1 scanner =
        UnicodeDataScanners.scanAllCodepoints(
            ScannerFactory.of(UnicodeDigit_lowerD_6_1::new),
            UnicodeDigit_lowerD_6_1.YYEOF,
            UnicodeDataScanners.Dataset.ALL);
    assertThat(scanner.blocks()).containsExactlyElementsIn(expected);
  }

  private static ImmutableList<NamedCodepointRange<Boolean>> readGolden() throws IOException {
    ImmutableList.Builder<NamedCodepointRange<Boolean>> expected = ImmutableList.builder();
    PatternHandler goldenHandler =
        new PatternHandler() {
          @Override
          public void onRegexMatch(List<String> regexpGroups) {
            int start = Integer.parseInt(regexpGroups.get(0), 16);
            int end = Integer.parseInt(regexpGroups.get(1), 16);
            boolean digit = regexpGroups.get(2).equals("Nd");
            expected.add(NamedCodepointRange.<Boolean>create(digit, start, end));
          }
        };
    String goldenFile = "unicode_6_1/UnicodeDigit_6_1.output";
    try (BufferedReader goldenReader =
        Files.newBufferedReader(packageDirectory.resolve(goldenFile))) {
      SimpleIntervalsParser parser = new SimpleIntervalsParser(goldenReader, goldenHandler);
      parser.parse();
    }
    return expected.build();
  }
}
