package bowling.pitch;

import bowling.exception.BowlingBuildingException;
import bowling.score.Score;
import bowling.score.ScoreType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalPitchesTest {

    @DisplayName("첫 투구면 Pitch를 initiate를 통해 생성하여 내부 컬렉션에 추가함")
    @Test
    public void throwBall_첫_투구() {
        Score score = Score.valueOf(5);
        NormalPitches normalPitches = new NormalPitches();
        List<Pitch> normalPitchesList = normalPitches.getPitches();

        normalPitches.throwBall(score);
        Pitch currentPitch = normalPitchesList.get(0);

        assertThat(currentPitch.getScore()).isEqualTo(5);
        assertThat(currentPitch.getScoreType()).isEqualTo(ScoreType.NORMAL);
    }

    @DisplayName("두 번째 투구면 Pitch를 next를 통해 생성하여 내부 컬렉션에 추가하며, 스페어임")
    @Test
    public void throwBall_두번째_투구() {
        Score score = Score.valueOf(5);
        NormalPitches normalPitches = new NormalPitches();
        List<Pitch> normalPitchesList = normalPitches.getPitches();

        normalPitches.throwBall(score);
        normalPitches.throwBall(Score.valueOf(5));

        Pitch currentPitch = normalPitchesList.get(1);

        assertThat(currentPitch.getScore()).isEqualTo(5);
        assertThat(currentPitch.getScoreType()).isEqualTo(ScoreType.SPARE);
    }

    @DisplayName("Normal Frame의 Normal Pitches는 2번 초과 투구시 예외 발생")
    @Test
    public void throwBall_세번째_예외() {
        NormalPitches normalPitches = new NormalPitches();
        normalPitches.throwBall(Score.valueOf(0));
        normalPitches.throwBall(Score.valueOf(3));

        assertThatThrownBy(() -> {
            normalPitches.throwBall(Score.valueOf(3));
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_NORMAL_PITCHES_TRY);
    }
}
