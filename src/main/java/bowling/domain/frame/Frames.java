package bowling.domain.frame;

import bowling.domain.score.FrameScore;
import bowling.domain.score.FrameScores;
import bowling.domain.score.PitchScore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frames {
    private static final int INDEX_CONSTANT = 1;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames initiate() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.initiate());
        return new Frames(frames);
    }

    public void bowl(PitchScore pitchScore) {
        getCurrentFrame().bowl(pitchScore);
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - INDEX_CONSTANT);
    }

    public void moveToNextFrame() {
        Frame currentFrame = getCurrentFrame();
        if (currentFrame.isFinished()) {
            Frame nextFrame = currentFrame.next(getCurrentIndex());
            frames.add(nextFrame);
        }
    }

    public boolean hasNextTurn() {
        return !frames.contains(null);
    }

    public FrameScores getFrameScores() {
        List<FrameScore> frameScores = frames.stream()
                .map(Frame::calculateFrameScore)
                .filter(Optional::isPresent)
                .flatMap(frameScore -> Stream.of(frameScore.get()))
                .collect(Collectors.toList());
        return FrameScores.of(frameScores);
    }

    public int getCurrentIndex() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
