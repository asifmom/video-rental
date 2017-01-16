package com.refactor;

import static com.refactor.Movie.CHILDRENS;
import static com.refactor.Movie.NEW_RELEASE;
import static com.refactor.Movie.REGULAR;

class Price {
    double getCharge(int daysRented,int priceCode){
        IPrice priceCalculator;
        switch (priceCode) {
            case REGULAR:
                priceCalculator=new RegularPrice();
                break;
            case NEW_RELEASE:
                priceCalculator=new NewReleasePrice();
                break;
            case CHILDRENS:
                priceCalculator=new ChildrenPrice();
                break;
            default:
                priceCalculator=new RegularPrice();

        }
        return priceCalculator.getCharge(daysRented);

    }
}

abstract class IPrice {
    abstract double getCharge(int daysRented);
}


class RegularPrice extends IPrice {
    double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2)
            result += (daysRented - 2) * 1.5;
        return result;

    }
}

class NewReleasePrice extends IPrice {
    double getCharge(int daysRented) {
        return daysRented * 3;

    }
}

class ChildrenPrice extends IPrice {
    double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;

    }
}
