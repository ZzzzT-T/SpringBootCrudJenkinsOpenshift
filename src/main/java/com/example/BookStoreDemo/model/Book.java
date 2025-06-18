package com.example.BookStoreDemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	@Id
	private int id;
	private String title;
	private String author;
	private int year_published;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int id, String title, String author, int year_published) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.year_published = year_published;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYear_published() {
		return year_published;
	}
	public void setYear_published(int year_published) {
		this.year_published = year_published;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", year_published=" + year_published
				+ "]";
	}
	
	
}
