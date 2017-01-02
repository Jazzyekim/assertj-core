/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.core.api.atomic.referencearray;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.error.ShouldHaveSameSizeAs.shouldHaveSameSizeAs;
import static org.assertj.core.test.ExpectedException.none;
import static org.assertj.core.util.Arrays.array;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import java.util.concurrent.atomic.AtomicReferenceArray;

import org.assertj.core.test.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

public class AtomicReferenceArrayAssert_hasSameSizeAs_with_Arrays_Test {

  @Rule
  public ExpectedException thrown = none();

  @Test
  public void should_pass_if_actual_object_array_has_same_size_as_other_object_array() {
    assertThat(new String[]{"1", "2"}).hasSameSizeAs(new Byte[]{2, 3});
    assertThat(new String[]{"1", "2"}).hasSameSizeAs(new String[]{"1", "2"});
  }

  @Test
  public void should_pass_if_actual_object_array_has_same_size_as_other_primitive_array() {
    assertThat(new String[]{"1", "2"}).hasSameSizeAs(new byte[]{2, 3});
    assertThat(new String[]{"1", "2"}).hasSameSizeAs(new int[]{2, 3});
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    final String[] actual = null;
    assertThat(actual).hasSameSizeAs(new String[]{"1"});
  }

  @Test
  public void should_fail_if_other_is_not_an_array() {
    thrown.expectAssertionError(format("%nExpecting an array but was:<\"a string\">"));
    assertThat(new AtomicReferenceArray<Byte>(new Byte[]{1, 2})).hasSameSizeAs("a string");
  }

  @Test
  public void should_fail_if_size_of_actual_has_same_as_other_array() {
    final String[] actual = array("Luke", "Yoda");
    final String[] other = array("Yoda");
    thrown.expectAssertionError(shouldHaveSameSizeAs(actual, actual.length, other.length).create());
    assertThat(actual).hasSameSizeAs(other);
  }
}