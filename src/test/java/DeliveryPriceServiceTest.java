import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

public class DeliveryPriceServiceTest {
    int minimumPrice;
    RatesService rates;
    DeliveryPriceService service;

    @Before
    public void setUp() {
        minimumPrice = 400;
        rates = new RatesService(
                new int[] {50, 100, 200, 300},  // distance
                new int[] {100, 200},           // size
                new int[] {0, 300},             // fragility
                new int[] {5, 5, 6, 7, 8});     // workload basement and factors
        service = new DeliveryPriceService(minimumPrice, rates);
    }

    @Test
    public void calculate_expectMinimumPrice() {
        int price = service.calculate(Distance.TINY, Size.SMALL, Fragility.REGULAR, Workload.NORMAL);
        assertThat(price).isEqualTo(minimumPrice);
    }

    @Test
    public void calculate_fragileGoodsForLongDistance_exceptionThrown() {
        assertThrows(UnavailableDeliveryException.class, () -> service.calculate(Distance.LONG, Size.SMALL,
                Fragility.FRAGILE, Workload.NORMAL));
    }

    @Test
    public void calculate_fragileGoodsForLonger30kmDistance_exceptionThrown() {
        assertThrows(UnavailableDeliveryException.class, () -> service.calculate(31, Size.SMALL,
                Fragility.FRAGILE, Workload.NORMAL));
    }

    @Test
    public void calculate_increasedDistanceAndSize_expectIncreasedPrice() {
        int price = service.calculate(Distance.LONG, Size.LARGE, Fragility.REGULAR, Workload.NORMAL);
        assertThat(price).isEqualTo(500);
    }

    @Test
    public void calculate_fragilityAndWorkload_expectIncreasedPrice() {
        int price = service.calculate(Distance.SHORT, Size.SMALL, Fragility.FRAGILE, Workload.MODERATE);
        assertThat(price).isEqualTo(600);
    }

    @Test
    public void calculate_workloadHigh_expectIncreasedPrice() {
        int price = service.calculate(Distance.MEDIUM, Size.LARGE, Fragility.FRAGILE, Workload.HIGH);
        assertThat(price).isEqualTo(980);
    }

    @Test
    public void calculate_workloadExtreme_expectIncreasedPrice() {
        int price = service.calculate(Distance.MEDIUM, Size.LARGE, Fragility.FRAGILE, Workload.EXTREME);
        assertThat(price).isEqualTo(1120);
    }
}
