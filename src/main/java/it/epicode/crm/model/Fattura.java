package it.epicode.crm.model;

import java.math.BigDecimal;
import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="fatture")
public class Fattura {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer numero;
	private Integer anno;
	private Date data;
	private BigDecimal importo;
	@Enumerated(EnumType.STRING)
	private StatoFattura statoFattura;
	
	@ManyToOne (cascade= {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="partitaIva")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties({"fatture"})
	private Cliente cliente;


}
