package model;

// BusinessId.java is a simple business representation class.
// It contains the business id, name.
public class BusinessId {
  private String businessId;
  private String businessUid;
  private String businessName;

  public BusinessId(String businessUid) {
    this.businessUid = businessUid;
  }

  public BusinessId(String businessId, String businessName) {
    this.businessId = businessId;
    this.businessName = businessName;
  }

  public BusinessId(String businessId, String businessUid, String businessName) {
    this.businessId = businessId;
    this.businessUid = businessUid;
    this.businessName = businessName;
  }

  public String toString() {
    return businessId + "(" + businessUid + "): " + businessName;
  }

  public String getBusinessId() {
    return businessId;
  }

  public void setBusinessId(String businessId) {
    this.businessId = businessId;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public String getBusinessUid() {
    return businessUid;
  }

  public void setBusinessUid(String businessUid) {
    this.businessUid = businessUid;
  }
}
