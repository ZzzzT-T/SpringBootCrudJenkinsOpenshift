package com.example.BookStoreDemo.model;



public class CRUDBookStatus {
	private boolean isCrudSuccessful;
	private Book crudBook;
	private String crudRemarks;
	public CRUDBookStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isCrudSuccessful() {
		return isCrudSuccessful;
	}
	public void setCrudSuccessful(boolean isCrudSuccessful) {
		this.isCrudSuccessful = isCrudSuccessful;
	}
	public Book getCrudBook() {
		return crudBook;
	}
	public void setCrudBook(Book crudBook) {
		this.crudBook = crudBook;
	}
	public String getCrudRemarks() {
		return crudRemarks;
	}
	public void setCrudRemarks(String crudRemarks) {
		this.crudRemarks = crudRemarks;
	}	
	
}
