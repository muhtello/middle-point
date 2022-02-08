package com.example.middlepoint.deck.entity;


import com.example.middlepoint.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
   private Long id;

   @Column(
	   name="name_Deck",
	   nullable = false
   )
   private String name;
   private String collection;

   @OneToMany(fetch = FetchType.LAZY,
	   cascade = CascadeType.ALL,
	   mappedBy = "deck",
	   orphanRemoval = true
   )
   @JsonManagedReference
   private List <Card> cards;

   @JsonBackReference
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_Id")
	private User user;

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

   public User getUser() {
	  return user;
   }

   public void setUser(User user) {
	  this.user = user;
   }
}
