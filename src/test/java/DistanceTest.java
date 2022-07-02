import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

public class DistanceTest {
    @Test
    public void parse_tinyDistance() {
        assertThat(Distance.parse(0)).isEqualTo(Distance.TINY);
        assertThat(Distance.parse(1)).isEqualTo(Distance.TINY);
        assertThat(Distance.parse(2)).isEqualTo(Distance.TINY);
    }

    @Test
    public void parse_shortDistance() {
        assertThat(Distance.parse(3)).isEqualTo(Distance.SHORT);
        assertThat(Distance.parse(9)).isEqualTo(Distance.SHORT);
        assertThat(Distance.parse(10)).isEqualTo(Distance.SHORT);
    }

    @Test
    public void parse_mediumDistance() {
        assertThat(Distance.parse(11)).isEqualTo(Distance.MEDIUM);
        assertThat(Distance.parse(29)).isEqualTo(Distance.MEDIUM);
        assertThat(Distance.parse(30)).isEqualTo(Distance.MEDIUM);
    }

    @Test
    public void parse_longDistance() {
        assertThat(Distance.parse(31)).isEqualTo(Distance.LONG);
        assertThat(Distance.parse(100)).isEqualTo(Distance.LONG);
    }

    @Test
    public void parse_negativeDistance_expectException() {
        assertThrows(IllegalArgumentException.class, () -> Distance.parse(-1));
    }
}
