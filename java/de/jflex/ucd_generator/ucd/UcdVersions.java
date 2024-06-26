/*
 * Copyright (C) 2018-2020 Google, LLC.
 * SPDX-License-Identifier: BSD-3-Clause
 */
package de.jflex.ucd_generator.ucd;

import static java.lang.Math.min;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import de.jflex.ucd.UcdVersion;
import de.jflex.version.Version;
import java.util.List;
import java.util.Map;

/** A set of {@link UcdVersion}s. */
public class UcdVersions {

  // version –> Map<UcdFileType, File>
  private final ImmutableSortedMap<Version, UcdVersion> versions;

  private UcdVersions(ImmutableSortedMap<Version, UcdVersion> versions) {
    this.versions = versions;
  }

  public ImmutableSet<Version> versionSet() {
    return versions.keySet();
  }

  public List<String> versionsAsList() {
    ImmutableList.Builder<String> versionList = ImmutableList.builder();
    for (Version v : versions.keySet()) {
      versionList.add(v.toString());
    }
    return versionList.build();
  }

  public UcdVersion get(Version version) {
    return versions.get(version);
  }

  public Version getLastVersion() {
    return versions.lastKey();
  }

  @SuppressWarnings("unused") // Used in .vm
  public static String getClassNameForVersion(String version) {
    List<String> v = Splitter.on('.').splitToList(version);
    return "Unicode_" + Joiner.on('_').join(v.subList(0, min(2, v.size())));
  }

  /** Expands the version {@code x.y.z} into {@code x}, {@code x.y}, {@code x.y.z}. */
  @SuppressWarnings("unused") // Used in .vm
  public static ImmutableList<String> expandVersion(Version version) {
    ImmutableList.Builder<String> expandedVersions = ImmutableList.builder();
    // Add the major version x if it is a x.0.z version
    if (version.minor == -1 || (version.minor == 0 && version.patch != -1)) {
      expandedVersions.add(String.valueOf(version.major));
    }
    if (version.minor != -1) {
      expandedVersions.add(version.major + "." + version.minor);
    }
    if (version.minor != -1 && version.patch != -1) {
      expandedVersions.add(version.major + "." + version.minor + "." + version.patch);
    }
    return expandedVersions.build();
  }

  public static ImmutableList<String> expandVersion(String version) {
    return expandVersion(new Version(version));
  }

  /**
   * Expands all versionSet.
   *
   * @return the set of all versionSet, in decreasing order.
   */
  public List<String> expandAllVersions() {
    ImmutableList.Builder<String> expandedVersions = ImmutableList.builder();
    for (Version v : versionSet()) {
      expandedVersions.addAll(expandVersion(v));
    }
    return expandedVersions.build();
  }

  public Builder toBuilder() {
    return builder().putAll(versions);
  }

  @Override
  public String toString() {
    return "[" + Joiner.on(", ").join(versionsAsList()) + "]";
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    ImmutableSortedMap.Builder<Version, UcdVersion> versions =
        ImmutableSortedMap.orderedBy(Version.EXACT_VERSION_COMPARATOR);

    public Builder putAll(Map<Version, UcdVersion> versions) {
      this.versions.putAll(versions);
      return this;
    }

    private Builder put(Version version, UcdVersion ucdFiles) {
      versions.put(version, ucdFiles);
      return this;
    }

    public Builder put(String version, UcdVersion ucdFiles) {
      return put(new Version(version), ucdFiles);
    }

    public UcdVersions build() {
      return new UcdVersions(versions.build());
    }
  }
}
