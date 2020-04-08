/*
 * Licensed to CRATE Technology GmbH ("Crate") under one or more contributor
 * license agreements.  See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.  Crate licenses
 * this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.  You may
 * obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * However, if you have executed another commercial license agreement
 * with Crate these terms will supersede the license and you may use the
 * software solely pursuant to the terms of the relevant commercial agreement.
 */

package io.crate.common;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

public final class StringUtils {

    public static String toString(Class<?> clazz, Object...attributes) {
        if (attributes.length % 2 != 0) {
            throw new IllegalArgumentException("expected event number of entries");
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append(clazz.getName()).append('{');
        if (attributes.length > 0) {
            for (int i = 0; i <= attributes.length / 2; i += 2) {
                sb.append(Objects.requireNonNull(attributes[i]))
                    .append("=")
                    .append(attributes[i + 1])
                    .append(", ");
            }
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }

    public static List<String> splitToList(char delim, String value) {
        ArrayList<String> result = new ArrayList<>();
        int lastStart = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c == delim) {
                result.add(value.substring(lastStart, i));
                lastStart = i + 1;
            }
        }
        if (lastStart <= value.length()) {
            result.add(value.substring(lastStart));
        }
        return result;
    }

    @Nullable
    public static String nullOrString(@Nullable Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }
}
