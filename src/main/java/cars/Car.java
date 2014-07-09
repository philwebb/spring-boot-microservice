package cars;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Car {

	@Id
	@GeneratedValue
	private long id;

	private String make;

	private String model;

	private int year;

	public Car(String make, String model, int year) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
	}

	Car() {
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

}
