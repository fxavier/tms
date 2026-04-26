package pt.xavier.tms.shared.dto;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Paginated response wrapper returned by list endpoints.
 *
 * @param content       the items on the current page
 * @param page          zero-based current page number
 * @param size          number of items per page
 * @param totalElements total number of items across all pages
 * @param totalPages    total number of pages
 */
public record PagedResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {

    /**
     * Convenience factory that builds a {@code PagedResponse} from a Spring Data {@link Page}.
     */
    public static <T> PagedResponse<T> from(Page<T> page) {
        return new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
