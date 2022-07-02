/**
 * Implementation of {@code RatesService} for unit testing.
 * See {@code DeliveryPriceServiceTest} for examples.
 */
public class RatesService {
    private final int[] distanceRates;
    private final int[] sizeRates;
    private final int[] fragilityRates;
    private final int[] loadFactor;

    RatesService(int[] distanceRates, int[] sizeRates, int[] fragilityRates, int[] loadFactor) {
        this.distanceRates = distanceRates;
        this.sizeRates = sizeRates;
        this.fragilityRates = fragilityRates;
        this.loadFactor = loadFactor;
    }

    int getRateFor(Distance distance) {
        switch (distance) {
            case TINY:
                return distanceRates[0];
            case SHORT:
                return distanceRates[1];
            case MEDIUM:
                return distanceRates[2];
            case LONG:
                return distanceRates[3];
        }
        return distanceRates[0];
    }

    int getRateFor(Size size) {
        switch (size) {
            case SMALL:
                return sizeRates[0];
            case LARGE:
                return sizeRates[1];
        }
        return sizeRates[0];
    }

    int getRateFor(Fragility fragility) {
        switch (fragility) {
            case REGULAR:
                return fragilityRates[0];
            case FRAGILE:
                return fragilityRates[1];
        }
        return fragilityRates[0];
    }

    int getRateFactorFor(Workload workload) {
        switch (workload) {
            case NORMAL:
                return loadFactor[1];
            case MODERATE:
                return loadFactor[2];
            case HIGH:
                return loadFactor[3];
            case EXTREME:
                return loadFactor[4];
        }
        return loadFactor[1];
    }

    int getRateBasement() {
        return loadFactor[0];
    }
}
