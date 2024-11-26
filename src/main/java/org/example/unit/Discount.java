package org.example.unit;

@FunctionalInterface
public interface Discount {
    double apply(double amount);
}
