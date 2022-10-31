package utils;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;

public class Bindingz {

    public static BidirectionalNumberBinding bindBidirectional(Property<Number> property1, Property<Number> property2) {
        return new BidirectionalNumberBinding(property1, property2, null);
    }

    public static BidirectionalNumberBinding bindBidirectional(Property<Number> property1, Property<Number> property2, Double factor) {
        return new BidirectionalNumberBinding(property1, property2, factor);
    }

    public static class BidirectionalNumberBinding extends BidirectionalBinding<Number, Number> {

        protected Double factor = 1d;

        protected BidirectionalNumberBinding(Property<Number> property1, Property<Number> property2, Double factor) {
            super(property1, property2);
            this.factor = factor;
        }

        @Override
        protected Number convert(Number value) {
            double v = value.doubleValue();
            return factor == null ? v : v * factor;
        }

        @Override
        protected Number inverseConvert(Number value) {
            double v = value.doubleValue();
            return factor == null ? v : v / factor;
        }
    }

    public static abstract class BidirectionalBinding<S, T> {

        protected final Property<S> property1;
        protected final Property<T> property2;

        protected boolean calculating = false;
        private final InvalidationListener listener;

        protected abstract T convert(S value);

        protected abstract S inverseConvert(T value);

        protected BidirectionalBinding(Property<S> property1, Property<T> property2) {
            if (property2.isBound() || property1 == null) {
                throw new IllegalArgumentException();
            }

            this.property1 = property1;
            this.property2 = property2;
            property2.setValue(convert(property1.getValue()));

            listener = o -> {
                if (!calculating) {
                    calculating = true;
                    try {
                        if (o == property1) {
                            T value = convert(property1.getValue());
                            property2.setValue(value);
                        } else {
                            S value = inverseConvert(property2.getValue());
                            property1.setValue(value);
                        }
                    } catch (Exception ex) {
                        dispose();
                    }
                    calculating = false;
                }
            };

            property1.addListener(listener);
            property2.addListener(listener);
        }

        public void dispose() {
            property1.removeListener(listener);
            property2.removeListener(listener);
        }
    }
}