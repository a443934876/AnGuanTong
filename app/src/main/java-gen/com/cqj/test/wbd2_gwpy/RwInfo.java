package com.cqj.test.wbd2_gwpy;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import com.cqj.test.wbd2_gwpy.myinterface.IChooseItem;

/**
 * Entity mapped to table RW_INFO.
 */
public class RwInfo implements IChooseItem{

    private Long id;
    private String partid;
    private String partname;
    private Boolean is_choose;

    public RwInfo() {
    }

    public RwInfo(Long id) {
        this.id = id;
    }

    public RwInfo(Long id, String partid, String partname, Boolean is_choose) {
        this.id = id;
        this.partid = partid;
        this.partname = partname;
        this.is_choose = is_choose;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartid() {
        return partid;
    }

    public void setPartid(String partid) {
        this.partid = partid;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }

    public Boolean getIs_choose() {
        return is_choose;
    }

    public void setIs_choose(Boolean is_choose) {
        this.is_choose = is_choose;
    }

    @Override
    public String getItemName() {
        return partname;
    }

    @Override
    public String getItemId() {
        return partid;
    }

    @Override
    public String getCsId() {
        return null;
    }
}
