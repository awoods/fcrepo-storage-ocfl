/*
 * Licensed to DuraSpace under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.
 *
 * DuraSpace licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fcrepo.storage.ocfl;

import java.util.Optional;

/**
 * Factory for creating OcflObjectSessions.
 *
 * @author pwinckles
 */
public interface OcflObjectSessionFactory {

    /**
     * Creates a new OCFL object session for the specified OCFL object.
     *
     * @param ocflObjectId the OCFL object id to open a session for
     * @return new session
     */
    OcflObjectSession newSession(final String ocflObjectId);

    /**
     * Returns an existing session, if one exists.
     *
     * @param sessionId the id of an OcflObjectSession
     * @return an existing session
     */
    Optional<OcflObjectSession> existingSession(final String sessionId);

}
