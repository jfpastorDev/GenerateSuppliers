package es.jfpastor.app.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedores")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class Supplier extends PrintRecord {
	final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre", nullable = false)
	private String name;
	
	@Column(name="fechaalta", nullable = false)
	private LocalDateTime registrationDate;
	
	@ManyToMany(mappedBy = "customerssuppliers")
    List<Customer> customerssuppliers;
	
    public String parseDate() {
		if (registrationDate==null) {
			return "";
		} else {
			return registrationDate.format(CUSTOM_FORMATTER);
		}
    }
	
	@Override
	public String getHeader() {
		return "id, nombre, fechaalta";
	}
	
	@Override
	public String getRecord() {
		return this.getId() + ", " + this.getName() + ", " + this.parseDate();
	}
}
