package com.example.middlepoint.deck.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;

@SequenceGenerator(
	name="card_sequence",
	sequenceName = "card_sequence",
	allocationSize=100,
	initialValue = 10
)

@Entity
@Table(name = "card")
public class Card {

   @Id
   @GeneratedValue(
	   strategy = GenerationType.SEQUENCE,
	   generator = "card_sequence"
   )

   private long id;

   @JsonBackReference
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "deck_Id")
   private Deck deck;


   @Column(columnDefinition="Text")
   private String question;


   @Column(columnDefinition="Text")
   private String answer;

   private String cardStatus;

   @JsonFormat(pattern="dd-MM-yyyy")
   private Date lastReview;

   @JsonFormat(pattern="dd-MM-yyyy")
   private Date NextReview;

   public Card() {
   }

   public Card(
   	long id,
	   String question,
	   String answer,
	   String cardStatus,
	   Date lastReview,
	   Date nextReview)
   {
	  this.id = id;
	  this.question = question;
	  this.answer = answer;
	  this.cardStatus = cardStatus;
	  this.lastReview = lastReview;
	  this.NextReview = nextReview;
   }

   public Card(
   	String question,
	String answer,
	String cardStatus,
	Date lastReview,
	Date nextReview,
   	Deck deck
   )
   {
	  this.question = question;
	  this.answer = answer;
	  this.cardStatus = cardStatus;
	  this.lastReview = lastReview;
	  this.NextReview = nextReview;
	  this.deck = deck;
   }

   public long getId() {
	  return id;
   }

   public void setId(long id) {
	  this.id = id;
   }

   public Deck getDeck() {
	  return deck;
   }

   public void setDeck(Deck deck) {
	  this.deck = deck;
   }

   public String getQuestion() {
	  return question;
   }

   public void setQuestion(String question) {
	  this.question = question;
   }

   public String getAnswer() {
	  return answer;
   }

   public void setAnswer(String answer) {
	  this.answer = answer;
   }

   public String getCardStatus() {
	  return cardStatus;
   }

   public void setCardStatus(String cardStatus) {
	  this.cardStatus = cardStatus;
   }

   public Date getLastReview() {
	  return lastReview;
   }

   public void setLastReview(Date lastReview) {
	  this.lastReview = lastReview;
   }

   public Date getNextReview() {
	  return NextReview;
   }

   public void setNextReview(Date nextReview) {
	  NextReview = nextReview;
   }
}


