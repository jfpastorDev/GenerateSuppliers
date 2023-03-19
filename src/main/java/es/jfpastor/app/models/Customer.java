package es.jfpastor.app.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends PrintRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre", nullable = false)
	private String name;
	
	@ManyToMany
	@JoinTable(
			  name = "clientesproveedores", 
			  joinColumns = @JoinColumn(name = "clienteId"), 
			  inverseJoinColumns = @JoinColumn(name = "proveedorId"))
	private List<Supplier> customerssuppliers;
	
	@Override
	public String getHeader() {
		return "id, nombre";
	}
	
	@Override
	public String getRecord() {
		return this.getId() + ", " + this.getName();
	}
}
