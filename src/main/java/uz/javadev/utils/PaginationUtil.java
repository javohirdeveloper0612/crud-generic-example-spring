package uz.javadev.utils;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public final class PaginationUtil {

    public static <T> String generateUnPaginationHttpHeaders(Slice<T> page, Pageable pageable) {
        final int hasNextCount = pageable.getPageSize() * (pageable.getPageNumber() + 1) + 1;
        final int lastPageCount = pageable.getPageSize() * (pageable.getPageNumber()) + page.getNumberOfElements();
        return page.hasNext() ? Integer.toString(hasNextCount) : Integer.toString(lastPageCount);
    }

}
