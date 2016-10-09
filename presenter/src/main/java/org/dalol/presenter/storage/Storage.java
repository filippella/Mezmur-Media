/*
 * Copyright (c) 2016 Amharic Mezmur Lyrics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.presenter.storage;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/11/2016
 */
public interface Storage {

    void putBoolean(final String key, boolean value);

    boolean getBoolean(final String key, boolean defaultValue);

    void putString(final String key, final String value);

    String getString(final String key, final String defaultValue);

    void putInt(final String key, int value);

    int getInt(final String key, int defaultValue);

    void putLong(final String key, long value);

    long getLong(final String key, long defaultValue);

    void remove(final String key);

    boolean contains(final String key);
}
