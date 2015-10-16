package com.test;

public class Response {

    private final long id;
    private final String reseponseCode;

    public Response(long id, String reseponseCode) {
        this.id = id;
        this.reseponseCode = reseponseCode;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return reseponseCode;
    }
}
