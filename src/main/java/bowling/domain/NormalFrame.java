package bowling.domain;

public class NormalFrame implements Frame {
    private static final int FIRST_INDEX = 1;
    private static final int NEXT_INDEX = 1;
    private static final int MAXIMUM_NORMAL_FRAME_INDEX = 9;

    private final int index;
    private final Pitches pitches = new Pitches();

    private NormalFrame(int index) {
        this.index = index;
    }

    public static Frame initiate() {
        return new NormalFrame(FIRST_INDEX);
    }

    @Override
    public Frame next() {
        if (!pitches.isMovableToNextFrame()) {
            return this;
        }
        return index == MAXIMUM_NORMAL_FRAME_INDEX ? new FinalFrame() : new NormalFrame(index + NEXT_INDEX);
    }

    @Override
    public void bowl(int hitCounts) {
        pitches.recordPitch(hitCounts);
    }

    @Override
    public Pitches getPitches() {
        return pitches;
    }

    public int getIndex() {
        return index;
    }
}
