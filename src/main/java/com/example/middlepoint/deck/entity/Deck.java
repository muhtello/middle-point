package com.example.middlepoint.deck.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "deck")
public class Deck {
   @Id
   @GeneratedValue(
	strategy = GenerationType.IDENTITY
   )
   @Column(
   	name="id"
   )
   private Long id;

   @Column(
	   name="name_Deck",
	   nullable = false
   )
   private String name;
   @Column(
	   name="collection"
   )
   private String collection;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "deck", orphanRemoval = true)
   @JsonManagedReference
   private List <Card> cards;


   public Deck() {
   }

   public Deck(Long id, String name, String collection) {
	  this.id = id;
	  this.name = name;
	  this.collection = collection;
   }

   public Deck(String name, String collection) {
	  this.name = name;
	  this.collection = collection;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getCollection() {
	  return collection;
   }

   public void setCollection(String collection) {
	  this.collection = collection;
   }

   public List<Card> getCards() {
	  return cards;
   }

   public void setCards(List<Card> cards) {
	  this.cards = cards;
   }
}
