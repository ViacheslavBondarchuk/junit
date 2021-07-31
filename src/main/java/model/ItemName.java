package model;

public enum ItemName implements Valuable<String> {
    A("A"),B("B"),C("C"),D("D");

    final String value;
    ItemName(String value) {
        this.value = value;
    }


    @Override
    public String value() {
        return value;
    }
}
