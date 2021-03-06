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

import java.time.OffsetDateTime;

/**
 * Session interface over an OCFL object. Changes to the object are accumulated in a staging directory until the
 * session is committed, at which point all of the changes are written to a new OCFL object version.
 *
 * @author pwinckles
 */
public interface OcflObjectSession {

    /**
     * @return the id of the session
     */
    String sessionId();

    /**
     * @return the OCFL object id of the object the session is on
     */
    String ocflObjectId();

    /**
     * Writes a RDF resource to the session.
     *
     * @param resourceId the Fedora resource id of the resource to write
     * @param content the content to write
     */
    void writeRdfResource(final String resourceId, final ResourceContent content);

    /**
     * Writes a non-RDF resource to the session.
     *
     * @param resourceId the Fedora resource id of the resource to write
     * @param content the content to write
     */
    void writeNonRdfResource(final String resourceId, final ResourceContent content);

    /**
     * Writes an ACL for a RDF resource to the session.
     *
     * @param resourceId the Fedora resource id of the resource to write, this should end in "/fcr:acl"
     * @param content the content to write
     */
    void writeAclRdfResource(final String resourceId, final ResourceContent content);

    /**
     * Writes an ACL for a non-RDF resource to the session.
     *
     * @param resourceId the Fedora resource id of the resource to write, this should end in "/fcr:acl"
     * @param content the content to write
     */
    void writeAclNonRdfResource(final String resourceId, final ResourceContent content);

    /**
     * Deletes a content file from the session, and updates the associated headers.
     *
     * @param resourceId the Fedora resource id of the resource that's associated content file should be delete
     * @param headers the updated resource headers
     */
    void deleteContentFile(final String resourceId, final ResourceHeaders headers);

    /**
     * Deletes a resource's header file. This should only be called if its content file has already been deleted.
     *
     * @param resourceId the Fedora resource id of the resource that's associated header file should be deleted
     */
    void deleteHeaderFile(final String resourceId);

    /**
     * Reads a resource's header file.
     *
     * @param resourceId the Fedora resource id to read
     * @return the resource's headers
     * @throws NotFoundException if the resource cannot be found
     */
    ResourceHeaders readResourceHeaders(final String resourceId);

    /**
     * Reads a resource's content.
     *
     * @param resourceId the Fedora resource id to read
     * @return the resource's content
     * @throws NotFoundException if the resource cannot be found
     */
    ResourceContent readContent(final String resourceId);

    /**
     * Sets the timestamp that's stamped on the OCFL version. If this value is not set, the current system time
     * at the time the version is created is used.
     *
     * @param timestamp version creation timestamp
     */
    void versionCreationTimestamp(final OffsetDateTime timestamp);

    /**
     * Sets the author the OCFL version is attributed to.
     *
     * @param name the author's name
     * @param address the author's address
     */
    void versionAuthor(final String name, final String address);

    /**
     * Sets the OCFL version message.
     *
     * @param message the OCFL version message
     */
    void versionMessage(final String message);

    /**
     * Commits the session, persisting all changes to a new OCFL version.
     */
    void commit();

    /**
     * Aborts the session, abandoning all changes.
     */
    void abort();

    /**
     * @return true if the session is still open
     */
    boolean isOpen();

}
