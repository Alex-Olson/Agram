package com.alex;


import java.util.LinkedList;

public class Player {
    private String name;
    private LinkedList<Card> hand;
    private boolean cpu;
    private boolean playedTurn;
    private boolean roundWinner;
    private Card playedCard;

    public Player(String name, boolean cpu){
        this.name = name;
        hand = new LinkedList<Card>();
        this.cpu = cpu;
        roundWinner = false;
        playedTurn = false;

    }

    public int suitCardsInHand(String suit){
        int cards = 0;
        for (Card c : hand){
            if (suit.equalsIgnoreCase(c.getSuit())){
                cards++;
            }
        }
        return cards;
    }

    //region get/setters


    public boolean isPlayedTurn() {
        return playedTurn;
    }

    public void setPlayedTurn(boolean playedTurn) {
        this.playedTurn = playedTurn;
    }

    public Card getPlayedCard() {
        return playedCard;
    }

    public void setPlayedCard(Card playedCard) {
        this.playedCard = playedCard;
        hand.remove(playedCard);
    }

    public void setCpu(boolean cpu) {
        this.cpu = cpu;
    }

    public boolean isRoundWinner() {
        return roundWinner;
    }

    public void setRoundWinner(boolean roundWinner) {
        this.roundWinner = roundWinner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Card> getHand() {
        return hand;
    }

    public void addToHand(Card drawnCard) {
        hand.push(drawnCard);
    }

    public void removeFromHand(Card drawnCard) {
         hand.remove(drawnCard);
    }

    public void setHand(LinkedList<Card> hand) {
        this.hand = hand;
    }

    public boolean isCpu() {
        return cpu;
    }


    //endregion
}
