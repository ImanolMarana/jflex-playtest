/*
 * Copyright 2019, Régis Décamps
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jflex.maven.plugin.cup;

import com.google.common.base.Strings;
import java.io.File;

public class JavaUtils {
  /** Constant {@code .java}. */
  private static final String JAVA_FILE_EXT = ".java";

  private JavaUtils() {}

  public static String packageToPath(String javaPackage) {
    return javaPackage.replace('.', File.separatorChar);
  }

  /**
   * Returns the Java source file associated with a class name.
   *
   * @param srcDirectory The root source directory, e.g. {@code /src}.
   * @param javaPackage The java package, e.g. {@code foo.bar}.
   * @param className The Java class name, e.g. {@code MyLexer}.
   * @return source file associated with the class name.
   */
  public static File file(File srcDirectory, String javaPackage, String className) {
    File dir = directory(srcDirectory, javaPackage);
    return new File(dir, className + JAVA_FILE_EXT);
  }

  /**
   * Returns the path to associated with a Java package.
   *
   * @param srcDirectory The root source directory, e.g. {@code /src}.
   * @param javaPackage The java package, e.g. {@code foo.bar}.
   */
  public static File directory(File srcDirectory, String javaPackage) {
    if (Strings.isNullOrEmpty(javaPackage)) {
      return srcDirectory;
    }
    return new File(srcDirectory, packageToPath(javaPackage));
  }
}
