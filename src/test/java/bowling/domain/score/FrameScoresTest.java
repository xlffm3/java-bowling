package bowling.domain.score;

import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FrameScoresTest {

    @DisplayName("값이 계산가능한 FrameScore만 내부 컬렉션에 추가됨.")
    @Test
    public void makeFrameScores_크기_1() {
        Frames frames = Frames.initiate();
        frames.bowl(PitchScore.valueOf(5));
        frames.bowl(PitchScore.valueOf(3));
        frames.moveToNextFrame();
        frames.bowl(PitchScore.valueOf(10));

        FrameScores frameScores = frames.getFrameScores();

        assertThat(frameScores.getFrameScoreCounts()).isEqualTo(1);
        assertThat(frameScores.getFrameScores()).containsExactly(8);
    }

    @DisplayName("각 프레임의 점수를 누적 계산하여 리턴함")
    @Test
    public void getFrameScores_8점_25점_32점() {
        Frames frames = Frames.initiate();
        frames.bowl(PitchScore.valueOf(5));
        frames.bowl(PitchScore.valueOf(3));
        frames.moveToNextFrame();
        frames.bowl(PitchScore.valueOf(10));
        frames.moveToNextFrame();
        frames.bowl(PitchScore.valueOf(3));
        frames.bowl(PitchScore.valueOf(4));

        FrameScores frameScores = frames.getFrameScores();

        assertThat(frameScores.getFrameScores()).containsExactly(8, 25, 32);
    }

    @DisplayName("12번 스트라이크를 치면 300점")
    @Test
    public void getFrameScores_300점() {
        Frames frames = Frames.initiate();
        for (int i = 0; i < 11; i++) {
            frames.bowl(PitchScore.valueOf(10));
            frames.moveToNextFrame();
        }
        frames.bowl(PitchScore.valueOf(10));

        FrameScores frameScores = frames.getFrameScores();
        List<Integer> cumulativeFrameScores = frameScores.getFrameScores();

        assertThat(cumulativeFrameScores.get(cumulativeFrameScores.size() - 1)).isEqualTo(300);
    }
}
