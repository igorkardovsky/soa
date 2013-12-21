package test2;
//http://java.dzone.com/articles/jpa-implementation-patterns-6
//http://stackoverflow.com/questions/3964059/jpa-default-column-name-mapping-for-manytoone-relations
//
//http://stackoverflow.com/questions/15425377/how-to-show-sql-parameters-in-hibernate-logimport java.util.List;

import java.util.List;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
@Access(value=AccessType.FIELD)
public class Category {
	

	String guid;

	@Column(length=64,nullable=false)
	String categoryName;

	@ManyToOne(fetch=FetchType.LAZY)
	Category parent;

	@OneToMany(fetch=FetchType.LAZY,orphanRemoval=true,mappedBy="parent",cascade = CascadeType.ALL)
	List<Category> childs;

	@Id
	@Column(length=40)
	@Access(value=AccessType.PROPERTY)
	public String getGuid() {
		if(guid == null){
			guid = UUID.randomUUID().toString();
		}
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public List<Category> getChilds() {
		return childs;
	}

	public void setChilds(List<Category> childs) {
		this.childs = childs;
	}

}
