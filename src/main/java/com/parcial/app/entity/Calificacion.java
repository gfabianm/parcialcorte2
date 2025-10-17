package com.parcial.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "calificaciones")
public class Calificacion {
	@Id
	private String id;
	private String numeroDocumento;
	private String numeroRegistro;
	private Double puntaje;
	private String saberProPuntajeNivel;
	private Double comunicacionEscrita;
	private String comunicacionEscritaNivel;
	private Double razonamientoCuantitativo;
	private String razonamientoCuantitativoNivel;
	private Double lecturaCritica;
	private String lecturaCriticaNivel;
	private Double competenciasCiudadanas;
	private String competenciasCiudadanasNivel;
	private Double ingles;
	private String inglesNivel;
	private Double formulacionProyectosIngenieria;
	private String formulacionProyectosIngenieriaNivel;
	private Double pensamientoCientificoMatematicasEstadistica;
	private String pensamientoCientificoMatematicasEstadisticaNivel;
	private Double disenoSoftware;
	private String disenoSoftwareNivel;
	private String nivelIngles;

	// Getters y Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public Double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Double puntaje) {
		this.puntaje = puntaje;
	}

	public String getSaberProPuntajeNivel() {
		return saberProPuntajeNivel;
	}

	public void setSaberProPuntajeNivel(String saberProPuntajeNivel) {
		this.saberProPuntajeNivel = saberProPuntajeNivel;
	}

	public Double getComunicacionEscrita() {
		return comunicacionEscrita;
	}

	public void setComunicacionEscrita(Double comunicacionEscrita) {
		this.comunicacionEscrita = comunicacionEscrita;
	}

	public String getComunicacionEscritaNivel() {
		return comunicacionEscritaNivel;
	}

	public void setComunicacionEscritaNivel(String comunicacionEscritaNivel) {
		this.comunicacionEscritaNivel = comunicacionEscritaNivel;
	}

	public Double getRazonamientoCuantitativo() {
		return razonamientoCuantitativo;
	}

	public void setRazonamientoCuantitativo(Double razonamientoCuantitativo) {
		this.razonamientoCuantitativo = razonamientoCuantitativo;
	}

	public String getRazonamientoCuantitativoNivel() {
		return razonamientoCuantitativoNivel;
	}

	public void setRazonamientoCuantitativoNivel(String razonamientoCuantitativoNivel) {
		this.razonamientoCuantitativoNivel = razonamientoCuantitativoNivel;
	}

	public Double getLecturaCritica() {
		return lecturaCritica;
	}

	public void setLecturaCritica(Double lecturaCritica) {
		this.lecturaCritica = lecturaCritica;
	}

	public String getLecturaCriticaNivel() {
		return lecturaCriticaNivel;
	}

	public void setLecturaCriticaNivel(String lecturaCriticaNivel) {
		this.lecturaCriticaNivel = lecturaCriticaNivel;
	}

	public Double getCompetenciasCiudadanas() {
		return competenciasCiudadanas;
	}

	public void setCompetenciasCiudadanas(Double competenciasCiudadanas) {
		this.competenciasCiudadanas = competenciasCiudadanas;
	}

	public String getCompetenciasCiudadanasNivel() {
		return competenciasCiudadanasNivel;
	}

	public void setCompetenciasCiudadanasNivel(String competenciasCiudadanasNivel) {
		this.competenciasCiudadanasNivel = competenciasCiudadanasNivel;
	}

	public Double getIngles() {
		return ingles;
	}

	public void setIngles(Double ingles) {
		this.ingles = ingles;
	}

	public String getInglesNivel() {
		return inglesNivel;
	}

	public void setInglesNivel(String inglesNivel) {
		this.inglesNivel = inglesNivel;
	}

	public Double getFormulacionProyectosIngenieria() {
		return formulacionProyectosIngenieria;
	}

	public void setFormulacionProyectosIngenieria(Double formulacionProyectosIngenieria) {
		this.formulacionProyectosIngenieria = formulacionProyectosIngenieria;
	}

	public String getFormulacionProyectosIngenieriaNivel() {
		return formulacionProyectosIngenieriaNivel;
	}

	public void setFormulacionProyectosIngenieriaNivel(String formulacionProyectosIngenieriaNivel) {
		this.formulacionProyectosIngenieriaNivel = formulacionProyectosIngenieriaNivel;
	}

	public Double getPensamientoCientificoMatematicasEstadistica() {
		return pensamientoCientificoMatematicasEstadistica;
	}

	public void setPensamientoCientificoMatematicasEstadistica(Double pensamientoCientificoMatematicasEstadistica) {
		this.pensamientoCientificoMatematicasEstadistica = pensamientoCientificoMatematicasEstadistica;
	}

	public String getPensamientoCientificoMatematicasEstadisticaNivel() {
		return pensamientoCientificoMatematicasEstadisticaNivel;
	}

	public void setPensamientoCientificoMatematicasEstadisticaNivel(
			String pensamientoCientificoMatematicasEstadisticaNivel) {
		this.pensamientoCientificoMatematicasEstadisticaNivel = pensamientoCientificoMatematicasEstadisticaNivel;
	}

	public Double getDisenoSoftware() {
		return disenoSoftware;
	}

	public void setDisenoSoftware(Double disenoSoftware) {
		this.disenoSoftware = disenoSoftware;
	}

	public String getDisenoSoftwareNivel() {
		return disenoSoftwareNivel;
	}

	public void setDisenoSoftwareNivel(String disenoSoftwareNivel) {
		this.disenoSoftwareNivel = disenoSoftwareNivel;
	}

	public String getNivelIngles() {
		return nivelIngles;
	}

	public void setNivelIngles(String nivelIngles) {
		this.nivelIngles = nivelIngles;
	}
}
