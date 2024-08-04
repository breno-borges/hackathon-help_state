package br.com.tripleahackathon.help_state.exceptions;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException() {
        super("Id não localizado");
    }
}
