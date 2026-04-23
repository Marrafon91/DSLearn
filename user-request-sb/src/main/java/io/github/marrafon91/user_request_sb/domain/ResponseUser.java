package io.github.marrafon91.user_request_sb.domain;

import io.github.marrafon91.user_request_sb.dto.UserDTO;

import java.util.List;

public class ResponseUser {

    private List<UserDTO> content;

    public ResponseUser() {
    }

    public ResponseUser(List<UserDTO> content) {
        this.content = content;
    }

    public List<UserDTO> getContent() {
        return content;
    }

}
