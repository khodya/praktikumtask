public class DeliveryPriceService {
    private final int minimumPrice;
    private final RatesService ratesService;

    DeliveryPriceService(int minimumPrice, RatesService ratesService) {
        this.minimumPrice = minimumPrice;
        this.ratesService = ratesService;
    }

    int calculate(int distance, Size size, Fragility fragility, Workload workload) {
        return calculate(Distance.parse(distance), size, fragility, workload);
    }

    int calculate(Distance distance, Size size, Fragility fragility, Workload workload) {
        if (fragility == Fragility.FRAGILE && distance == Distance.LONG) {
            throw new UnavailableDeliveryException("delivery of fragile goods is not possible for long distances");
        }
        int price = 0;
        price += ratesService.getRateFor(distance);
        price += ratesService.getRateFor(size);
        price += ratesService.getRateFor(fragility);
        price = (price * ratesService.getRateFactorFor(workload)) / ratesService.getRateBasement();
        return minimumPrice > price ? minimumPrice : price;
    }
}
