package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

public interface UpdateEntityDTO<T> extends EntityDTO<T> {
    /**
     * Copy values from an entity into the DTO
     *
     * @param t
     * @return UpdateEntityDTO<T>
     */
    UpdateEntityDTO<T> from(T t);
}
