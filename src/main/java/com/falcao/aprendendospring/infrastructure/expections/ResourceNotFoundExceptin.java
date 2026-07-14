package com.falcao.aprendendospring.infrastructure.expections;

public class ResourceNotFoundExceptin extends RuntimeException {

    public ResourceNotFoundExceptin(String mensagem) {
        super(mensagem);
    }

    public ResourceNotFoundExceptin(String mensagem, Throwable throwable) {
        super(mensagem, throwable);
    }
}
