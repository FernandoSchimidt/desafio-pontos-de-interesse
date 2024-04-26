package com.fernandoschimidt.pontointeresse.service.exceptions;

public class IllegalValueException extends IllegalArgumentException {
    public IllegalValueException(String string) {
        super(string);
    }
}
