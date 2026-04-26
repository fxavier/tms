package pt.xavier.tms.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Standard API response envelope.
 * Success: {@code { "data": ..., "error": null }}
 * Error:   {@code { "data": null, "error": { "code": ..., "message": ... } }}
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public record ApiResponse<T>(T data, ErrorDetail error) {

    /**
     * Wraps a successful result.
     */
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(data, null);
    }

    /**
     * Wraps an error result.
     */
    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(null, new ErrorDetail(code, message));
    }

    /**
     * Wraps an error result with a correlation ID for server-side tracing.
     */
    public static <T> ApiResponse<T> error(String code, String message, String correlationId) {
        return new ApiResponse<>(null, new ErrorDetail(code, message, correlationId));
    }

    /**
     * Nested error detail object.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record ErrorDetail(String code, String message, String correlationId) {

        public ErrorDetail(String code, String message) {
            this(code, message, null);
        }
    }
}
