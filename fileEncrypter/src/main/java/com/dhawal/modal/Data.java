package com.dhawal.modal;

// Instead of Class | record can be used to avoid all these gettre/setter , constructor and all other boilerplate
public class Data {

    private int id;
    private String filenmae;
    private String path;
    private String email;

    

    public Data(int id, String filenmae, String path) {
        this.id = id;
        this.filenmae = filenmae;
        this.path = path;
    }

    public Data(int id, String filenmae, String path, String email) {
        this.id = id;
        this.filenmae = filenmae;
        this.path = path;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilenmae() {
        return filenmae;
    }

    public void setFilenmae(String filenmae) {
        this.filenmae = filenmae;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
