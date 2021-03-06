/*
 *   Copyright 2018 Google LLC
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package io.plaidapp.core.data;

import androidx.annotation.DrawableRes;
import io.plaidapp.core.R;

import java.util.Comparator;
import java.util.Objects;

/**
 * Representation of a data source
 */
public class Source {

    public final String key;
    public final int sortOrder;
    public final String name;
    public final @DrawableRes int iconRes;
    private boolean active;

    public Source(String key,
                  int sortOrder,
                  String name,
                  @DrawableRes int iconResId,
                  boolean active) {
        this.key = key;
        this.sortOrder = sortOrder;
        this.name = name;
        this.iconRes = iconResId;
        this.active = active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Source)) return false;
        Source source = (Source) o;
        return sortOrder == source.sortOrder &&
                iconRes == source.iconRes &&
                active == source.active &&
                key.equals(source.key) &&
                name.equals(source.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, sortOrder, name, iconRes, active);
    }

    @Override
    public String toString() {
        return "Source{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }

    public boolean isSwipeDismissable() {
        return false;
    }

    public static class DribbbleSource extends Source {

        public DribbbleSource(String key,
                              int sortOrder,
                              String name,
                              boolean active) {
            super(key, sortOrder, name, R.drawable.ic_dribbble, active);
        }
    }

    public static class DribbbleSearchSource extends DribbbleSource {

        public static final String DRIBBBLE_QUERY_PREFIX = "DRIBBBLE_QUERY_";
        private static final int SEARCH_SORT_ORDER = 400;

        public final String query;

        public DribbbleSearchSource(String query,
                                    boolean active) {
            super(DRIBBBLE_QUERY_PREFIX + query, SEARCH_SORT_ORDER, "“" + query + "”", active);
            this.query = query;
        }

        @Override
        public boolean isSwipeDismissable() {
            return true;
        }
    }

    public static class DesignerNewsSource extends Source {

        public DesignerNewsSource(String key,
                                  int sortOrder,
                                  String name,
                                  boolean active) {
            super(key, sortOrder, name, R.drawable.ic_designer_news, active);
        }
    }

    public static class DesignerNewsSearchSource extends DesignerNewsSource {

        public static final String DESIGNER_NEWS_QUERY_PREFIX = "DESIGNER_NEWS_QUERY_";
        private static final int SEARCH_SORT_ORDER = 200;

        public final String query;

        public DesignerNewsSearchSource(String query,
                                        boolean active) {
            super(DESIGNER_NEWS_QUERY_PREFIX + query, SEARCH_SORT_ORDER, "“" + query + "”", active);
            this.query = query;
        }

        @Override
        public boolean isSwipeDismissable() {
            return true;
        }
    }

    public static class SourceComparator implements Comparator<Source> {

        @Override
        public int compare(Source lhs, Source rhs) {
            return lhs.sortOrder - rhs.sortOrder;
        }
    }
}


