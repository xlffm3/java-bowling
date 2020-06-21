package bowling.domain.pitch;

import bowling.domain.bowling.BowlingPinsGroup;
import bowling.domain.exception.BowlingBuildingException;

public class Pitch {
    private static final int MINIMUM_HIT_COUNTS = 0;
    private static final int MAXIMUM_HIT_COUNTS = 10;

    private final int hitCounts;

    public Pitch(int hitCounts) {
        validateHitCounts(hitCounts);
        this.hitCounts = hitCounts;
    }

    private void validateHitCounts(int hitCounts) {
        if (hitCounts < MINIMUM_HIT_COUNTS || hitCounts > MAXIMUM_HIT_COUNTS) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_PITCH);
        }
    }

    public void throwBall(BowlingPinsGroup bowlingPinsGroup) {
        bowlingPinsGroup.hitByBall(hitCounts);
    }

    public boolean isStrike() {
        return hitCounts == MAXIMUM_HIT_COUNTS;
    }

    public boolean isGutter() {
        return hitCounts == MINIMUM_HIT_COUNTS;
    }

    public int getHitCounts() {
        return hitCounts;
    }
}
