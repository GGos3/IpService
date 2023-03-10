package ggos3.IpService;

import org.assertj.core.api.Assertions;

public class SimpleTest {
    public static void main(String[] args) {
        int forwarded = 2;
        int[] array = new int[]{0, 1, 2, 3, 4};
        int i = (array.length) - forwarded;

        Assertions.assertThat(array[i]).isEqualTo(3);
    }
}
