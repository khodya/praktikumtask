public enum Distance {
    TINY, SHORT, MEDIUM, LONG;

    static Distance parse(int distance) {
        if (distance < 0) throw new IllegalArgumentException("distance cannot be a negative number");
        if (distance <= 2) {
            return Distance.TINY;
        } else if (distance <= 10) {
            return Distance.SHORT;
        } else if (distance <= 30) {
            return Distance.MEDIUM;
        } else {
            return Distance.LONG;
        }
    }
}
