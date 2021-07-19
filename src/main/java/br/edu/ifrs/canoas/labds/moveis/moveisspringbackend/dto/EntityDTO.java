package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

public interface EntityDTO<T> {
    /**
     * Convert DTO to the base entity
     *
     * @return T
     */
    T toEntity();
}
