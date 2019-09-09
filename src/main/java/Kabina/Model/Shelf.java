package Kabina.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Shelf", uniqueConstraints = @UniqueConstraint(columnNames = {"floor", "positionNumber"}))
public class Shelf {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Column
	private long floor;

	@NotNull
	@Column
	private long positionNumber;

	@NotNull
	@Column
	private String status;

	@JsonManagedReference
	@ManyToOne
    @JoinColumn(name = "businessUnitId")
	private BusinessUnit businessUnit;

	public Shelf() {
		super();
	}
	
	public Shelf(long id, @NotNull long floor, @NotNull long positionNumber, @NotNull String status,
			BusinessUnit businessUnit) {
		super();
		this.id = id;
		this.floor = floor;
		this.positionNumber = positionNumber;
		this.status = status;
		this.businessUnit = businessUnit;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFloor() {
		return floor;
	}

	public void setFloor(long floor) {
		this.floor = floor;
	}

	public long getPositionNumber() {
		return positionNumber;
	}

	public void setPositionNumber(long positionNumber) {
		this.positionNumber = positionNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}
}
