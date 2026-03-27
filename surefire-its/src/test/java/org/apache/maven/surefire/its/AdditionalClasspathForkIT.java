/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.maven.surefire.its;

import org.apache.maven.surefire.its.fixture.SurefireJUnit4IntegrationTestCase;
import org.junit.jupiter.api.Test;

/**
 * Integration tests for fork-related {@code additionalClasspathElement} features:
 * <ul>
 *   <li>Relative classpath elements resolved against the fork's working directory
 *       (regression guard for the manifest-JAR path-resolution bug).</li>
 *   <li>{@code ${surefire.forkNumber}} placeholder expansion inside classpath elements.</li>
 * </ul>
 */
public class AdditionalClasspathForkIT extends SurefireJUnit4IntegrationTestCase {

    /**
     * Verifies that a relative {@code additionalClasspathElement} (e.g. {@code ../cp-extra}) is
     * resolved against the fork's {@code workingDirectory}, not against the Maven base directory
     * or the location of the manifest-only JAR.
     */
    @Test
    public void relativeClasspathElementResolvedAgainstWorkingDirectory() {
        unpack("/additional-classpath-relative-workdir").executeTest().verifyErrorFree(1);
    }

    /**
     * Verifies that {@code ${surefire.forkNumber}} (and its alias {@code ${surefire.threadNumber}})
     * is expanded inside {@code additionalClasspathElement} values, allowing fork-specific
     * classpath directories.
     */
    @Test
    public void forkNumberPlaceholderInClasspathElement() {
        unpack("/additional-classpath-fork-number").executeTest().verifyErrorFree(1);
    }
}
