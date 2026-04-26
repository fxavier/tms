package pt.xavier.tms.shared.enums;

import pt.xavier.tms.shared.exception.BusinessException;

import java.util.Map;
import java.util.Set;

/**
 * Lifecycle status of an activity and its allowed state transitions (Requisito 8.3).
 */
public enum ActivityStatus {

    PLANEADA,
    EM_CURSO,
    SUSPENSA,
    CONCLUIDA,
    CANCELADA;

    /**
     * Allowed transitions per source state (Requisito 8.3).
     */
    private static final Map<ActivityStatus, Set<ActivityStatus>> ALLOWED_TRANSITIONS = Map.of(
            PLANEADA,  Set.of(EM_CURSO, CANCELADA),
            EM_CURSO,  Set.of(SUSPENSA, CONCLUIDA, CANCELADA),
            SUSPENSA,  Set.of(EM_CURSO, CANCELADA),
            CONCLUIDA, Set.of(),   // terminal
            CANCELADA, Set.of()    // terminal
    );

    /**
     * Returns {@code true} if transitioning to {@code target} is permitted.
     */
    public boolean canTransitionTo(ActivityStatus target) {
        return ALLOWED_TRANSITIONS.getOrDefault(this, Set.of()).contains(target);
    }

    /**
     * Validates the transition and throws {@link BusinessException} if it is not allowed.
     *
     * @throws BusinessException with code {@code INVALID_STATUS_TRANSITION} (Requisito 8.4)
     */
    public void validateTransition(ActivityStatus target) {
        if (!canTransitionTo(target)) {
            throw new BusinessException(
                    "INVALID_STATUS_TRANSITION",
                    String.format("Transition from %s to %s is not allowed", this, target)
            );
        }
    }
}
