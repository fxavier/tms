package pt.xavier.tms.shared.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pt.xavier.tms.shared.dto.ApiResponse;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Centralised exception-to-HTTP-response mapping for all REST controllers.
 *
 * <p>Response format follows the {@link ApiResponse} envelope:
 * {@code { "data": null, "error": { "code": ..., "message": ... } }}</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // -------------------------------------------------------------------------
    // 404 — Resource not found
    // -------------------------------------------------------------------------

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(ResourceNotFoundException ex) {
        log.debug("Resource not found: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("RESOURCE_NOT_FOUND", ex.getMessage()));
    }

    // -------------------------------------------------------------------------
    // 422 — Business rule violation
    // -------------------------------------------------------------------------

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException ex) {
        log.debug("Business rule violation [{}]: {}", ex.getCode(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ApiResponse.error(ex.getCode(), ex.getMessage()));
    }

    // -------------------------------------------------------------------------
    // 422 — Allocation blocked
    // -------------------------------------------------------------------------

    @ExceptionHandler(AllocationException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleAllocationException(AllocationException ex) {
        log.debug("Allocation blocked with {} blocker(s)", ex.getBlockers().size());

        // Build a structured payload that lists every blocker
        var blockersPayload = ex.getBlockers().stream()
                .map(b -> Map.of("code", (Object) b.code(), "message", (Object) b.message()))
                .toList();

        var body = new ApiResponse<Map<String, Object>>(
                null,
                new ApiResponse.ErrorDetail("ALLOCATION_BLOCKED", ex.getMessage())
        );

        // Return the blockers list inside the data field so clients can iterate
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ApiResponse<>(Map.of("blockers", blockersPayload), null));
    }

    // -------------------------------------------------------------------------
    // 400 — Validation errors (Bean Validation)
    // -------------------------------------------------------------------------

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fe -> fe.getDefaultMessage() != null ? fe.getDefaultMessage() : "Invalid value",
                        // keep first error per field when there are multiple
                        (existing, replacement) -> existing
                ));

        log.debug("Validation failed for {} field(s)", fieldErrors.size());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(fieldErrors, new ApiResponse.ErrorDetail("VALIDATION_ERROR", "Request validation failed")));
    }

    // -------------------------------------------------------------------------
    // 500 — Unexpected errors
    // -------------------------------------------------------------------------

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
        String correlationId = UUID.randomUUID().toString();
        log.error("Unexpected error [correlationId={}]", correlationId, ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(
                        "INTERNAL_ERROR",
                        "An unexpected error occurred. Please contact support with correlationId: " + correlationId,
                        correlationId
                ));
    }
}
