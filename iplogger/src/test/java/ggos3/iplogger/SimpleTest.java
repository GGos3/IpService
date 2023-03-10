package ggos3.iplogger;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

public class SimpleTest {
    public static void main(String[] args) {
        int forwarded = 2;
        int[] array = new int[]{0, 1, 2, 3, 4};
        int i = (array.length) - forwarded;

        Assertions.assertThat(array[i]).isEqualTo(3);
    }
}
