package cn.fjmz.agt;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * Entity mapped to table SBJC_LIST_INFO.
 */
public class DeviceInfo {

    private Long id;
    private Integer cpid;
    private String prodname;
    private String sccheckname;
    private String sccheckdate;
    private String sccheckstat;
    private String sccheckdetail;
    private String mDeviceLocation;

    public DeviceInfo() {
    }


    DeviceInfo(Long id, Integer cpid, String prodname, String sccheckname, String sccheckdate, String sccheckstat, String sccheckdetail, String deviceLocation) {
        this.id = id;
        this.cpid = cpid;
        this.prodname = prodname;
        this.sccheckname = sccheckname;
        this.sccheckdate = sccheckdate;
        this.sccheckstat = sccheckstat;
        this.sccheckdetail = sccheckdetail;
        this.mDeviceLocation = deviceLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCpid() {
        return cpid;
    }

    public void setCpid(Integer cpid) {
        this.cpid = cpid;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getSccheckname() {
        return sccheckname;
    }

    public void setSccheckname(String sccheckname) {
        this.sccheckname = sccheckname;
    }

    public String getSccheckdate() {
        return sccheckdate;
    }

    public void setSccheckdate(String sccheckdate) {
        this.sccheckdate = sccheckdate;
    }

    public String getSccheckstat() {
        return sccheckstat;
    }

    public void setSccheckstat(String sccheckstat) {
        this.sccheckstat = sccheckstat;
    }

    public String getSccheckdetail() {
        return sccheckdetail;
    }

    public void setSccheckdetail(String sccheckdetail) {
        this.sccheckdetail = sccheckdetail;
    }

    public String getDeviceLocation() {
        return mDeviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        mDeviceLocation = deviceLocation;
    }
}