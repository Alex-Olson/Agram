package com.alex;


public class Card {

    private String value;
    private String suit;

    public Card (String suit, String value){
        this.value = value;
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString(){
        return (value + " of " + suit);
    }
}
