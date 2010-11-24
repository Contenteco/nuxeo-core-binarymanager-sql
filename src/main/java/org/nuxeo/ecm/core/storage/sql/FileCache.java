/*
 * (C) Copyright 2010 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Florent Guillaume
 */
package org.nuxeo.ecm.core.storage.sql;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * A cache of {@link File}s.
 * <p>
 */
public interface FileCache {

    /**
     * Gets the size of the cache, in bytes.
     */
    long getSize();

    /**
     * Gets the number of items in the cache.
     */
    int getNumberOfItems();

    /**
     * Creates a temporary file.
     */
    File getTempFile() throws IOException;

    /**
     * Puts a file in the cache.
     *
     * @param key the cache key
     * @param in the input stream to cache (closed afterwards)
     * @return the cached file
     * @throws IllegalArgumentException if the key is illegal
     */
    File putFile(String key, InputStream in) throws IllegalArgumentException,
            IOException;

    /**
     * Puts a file in the cache.
     * <p>
     * The file must have been created through {@link #getTempFile()}. The file
     * is "given" to this method, who will delete it or rename it.
     *
     * @param key the cache key
     * @param file the file to cache
     * @return the cached file
     * @throws IllegalArgumentException if the key is illegal
     */
    File putFile(String key, File file) throws IllegalArgumentException,
            IOException;

    /**
     * Gets a file from the cache.
     * <p>
     * A returned file will never be deleted from the filesystem while the
     * returned file object is still referenced, although it may be purged from
     * the cache.
     *
     * @param key the cache key
     * @return the cached file, or {@code null} if absent
     */
    File getFile(String key);

    /**
     * Clears the cache.
     * <p>
     * Files will not be deleted from the filesystm while the returned file
     * objects are still referenced.
     */
    void clear();

}