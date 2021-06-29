package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListDTO<T> {
    public List<T> list;

    public ListDTO(List<T> list) {
        this.list = list;
    }
}
