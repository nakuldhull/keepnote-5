package com.stackroute.keepnote.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  */
@Document
public class Category {

	/*
	 * This class should have five fields
	 * (categoryId,categoryName,categoryDescription,
	 * categoryCreatedBy,categoryCreationDate). Out of these five fields, the field
	 * categoryId should be annotated with @Id. This class should also contain the
	 * getters and setters for the fields along with the no-arg , parameterized
	 * constructor and toString method. The value of categoryCreationDate should not
	 * be accepted from the user but should be always initialized with the system
	 * date. 
	 */
	

	@Id
	private String id;
	private String categoryName;
	private String categoryDescription;
	private Date categoryCreationDate;
	private String categoryCreatedBy;
	
    public Category() {
    	
    }

	public Category(String id, String categoryName, String categoryDescription, Date categoryCreationDate,
			String categoryCreatedBy) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.categoryCreationDate = categoryCreationDate;
		this.categoryCreatedBy = categoryCreatedBy;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public Date getCategoryCreationDate() {
		return categoryCreationDate;
	}

	public void setCategoryCreationDate(Date categoryCreationDate) {
		this.categoryCreationDate = categoryCreationDate;
	}

	public String getCategoryCreatedBy() {
		return categoryCreatedBy;
	}

	public void setCategoryCreatedBy(String categoryCreatedBy) {
		this.categoryCreatedBy = categoryCreatedBy;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", categoryDescription=" + categoryDescription
				+ ", categoryCreationDate=" + categoryCreationDate + ", categoryCreatedBy=" + categoryCreatedBy + "]";
	}



}
