package com.appcare.followconnect.SimpleRecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SearchFilter {
    public SearchFilter() {
    }

    public static <T> Collection<T> filter(Collection<T> sourceList, SearchFilterInterface<T> filterCondition) {
        Collection<T> result = new ArrayList();
        Iterator var3 = sourceList.iterator();

        while(var3.hasNext()) {
            T item = (T) var3.next();
            if (filterCondition.applyConditionToAdd(item)) {
                result.add(item);
            }
        }

        return result;
    }
}

