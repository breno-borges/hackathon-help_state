package br.com.tripleahackathon.help_state.modules.profile.dto;

public record UpdateCitizenRequestDTO(
        String name,
        int age,
        String email,
        String state,
        String profilePicture,
        String username,
        String password) {
}
