package test2;
//http://java.dzone.com/articles/jpa-implementation-patterns-6
//http://stackoverflow.com/questions/3964059/jpa-default-column-name-mapping-for-manytoone-relations

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	@Id
	@Column(length=32)
	String guid;

	@Column(length=64,nullable=false)
	String categoryName;

	@ManyToOne(fetch=FetchType.LAZY)
	Category parent;

	@OneToMany(fetch=FetchType.LAZY,orphanRemoval=true)
	List<Category> childs;

	public String getGuid() {
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