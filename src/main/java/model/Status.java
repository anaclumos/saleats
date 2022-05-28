package model;

// Status is the Data Transfer Object between MVC Layers.
public class Status {
  private boolean ok;
  private String message;

  public Status(boolean ok, String message) {
    this.ok = ok;
    this.message = message;
  }

  public boolean isOk() {
    return ok;
  }

  public String says() {
    return message;
  }
}