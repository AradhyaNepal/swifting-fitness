package com.a2.swifting_fitness.common.model;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GenericResponseEntity<T> extends ResponseEntity<GenericResponse<T>> {
    public GenericResponseEntity(GenericResponse<T> response, HttpStatusCode status) {
        super(response, status);
    }

    static public <T> GenericResponseEntity<T> successWithData(T data, String message) {
        return new GenericResponseEntity<>(GenericResponse.<T>builder().data(data).message(message).build(), HttpStatus.OK);
    }

    static public <T> GenericResponseEntity<List<T>> successWithPagination(Page<T> data, String message) {
        var items = data.get().toList();
        var totalItems=data.getTotalElements();
        var pageSize = data.getSize();
        var totalPage=data.getTotalPages();
        var currentPage= data.getNumber()+1;//Page number starts from 0 and for frontend it starts from 1
        return new GenericResponseEntity<>(
                GenericResponse.<List<T>>builder()
                        .data(items)
                        .message(message)
                        .haveNext(totalItems>((long) currentPage *pageSize))
                        .totalPages(totalPage)
                        .totalItems(totalItems)
                        .pageSize(pageSize)
                        .currentPage(currentPage)
                        .build(),
                HttpStatus.OK );
    }

    static public GenericResponseEntity<Void> successWithMessage(String message) {
        return new GenericResponseEntity<>(GenericResponse.<Void>builder().message(message).build(), HttpStatus.OK);
    }

    static public GenericResponseEntity<Void> error(List<String> errors, HttpStatus status) {
        return new GenericResponseEntity<>(GenericResponse.<Void>builder().errors(errors).build(), status);
    }

    static public <T> GenericResponseEntity<T> errorWithErrorData(T errorData, List<String> errors, HttpStatus status) {
        return new GenericResponseEntity<>(GenericResponse.<T>builder().data(errorData).errors(errors).build(), status);
    }

}
