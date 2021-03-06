/**
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
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
package org.jasig.portal.version;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import org.jasig.portal.version.om.Version;
import org.junit.Test;

public class VersionUtilsTest {
    @Test
    public void testVersionParsing() {
        Version version = VersionUtils.parseVersion("4.0.5");
        assertNotNull(version);
        assertEquals(4, version.getMajor());
        assertEquals(0, version.getMinor());
        assertEquals(5, version.getPatch());
    }
}
