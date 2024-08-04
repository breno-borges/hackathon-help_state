package br.com.tripleahackathon.help_state.exceptions;

public class HelpPlaceFoundException extends RuntimeException {
    public HelpPlaceFoundException() {
        super("Local ja existente");
    }
}
