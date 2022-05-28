package model;

// User class is the data transfer object for the server and client.
public class User {
  private boolean ok;
  private String email;
  private String name;

  public User(String name, String email) {
    this.ok = true;
    this.name = name;
    this.email = email;
  }

  public User(boolean ok) {
    this.ok = ok;
    this.email = null;
    this.name = null;
  }

  public boolean isOk() {
    return ok;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public static String toJson(User user) {
    return "{\"ok\":" + user.ok + ",\"email\":\"" + user.email + "\",\"name\":\"" + user.name + "\"}";
  }
}