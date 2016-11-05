package com.alex;


public class Card {

    private int value;
    private String suit;

    public Card(){

    }

    public Card (String suit, int value){
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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
        String valueString;

        if (value == 11){
            valueString = "Jack";
        } else if (value == 12){
            valueString = "Queen";
        } else if (value == 13){
            valueString = "King";
        } else if (value == 14){
            valueString = "Ace";
        } else {
            valueString = Integer.toString(value);
        }

        return (valueString + " of " + suit);
    }
}
