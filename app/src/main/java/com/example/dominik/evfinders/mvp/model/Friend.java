package com.example.dominik.evfinders.mvp.model;

/**
 * Created by Dominik on 23.06.2017.
 */

public class Friend {
    private Long idEvent;

    private Long idUser;
    private Long idFriend;

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(Long idFriend) {
        this.idFriend = idFriend;
    }
}
