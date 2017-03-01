package com.pvirtech.pzpolice.enumeration;

import com.pvirtech.pzpolice.entity.Leave;

/**
 * Created by youpengda on 2017/3/1.
 */

public enum LeaveType {
    /**
     * 事假、病假、年假、调休、婚假、产假、陪产假、路途假、医疗假、工伤假、丧假、其他
     */

    /**
     * 事假
     */
    COMPASSIONATE_LEAVE(new Leave("事假", 1)),
    /**
     * 病假
     */
    SICK_LEAVE(new Leave("病假", 2)),
    /**
     * 年假
     */
    ANNUAL_LEAVE(new Leave("年假", 3)),
    /**
     * 调休
     */
    OFF_LEAVE(new Leave("调休", 4)),
    /**
     * 婚假
     */
    MARRIAGE_HOLIDAY(new Leave("婚假", 5)),
    /**
     * 产假
     */
    MATERNITY_LEAVE(new Leave("产假", 6)),
    /**
     * 陪产假
     */
    PATERNITY_LEAVE(new Leave("陪产假", 7)),
    /**
     * 路途假
     */
    ROAD_TRIP(new Leave("路途假", 8)),
    /**
     * 医疗假
     */
    MEDICAL_LEAVE(new Leave("医疗假", 9)),
    /**
     * 工伤假
     */
    WORK_RELATED_INJURY(new Leave("工伤假", 10)),
    /**
     * 丧假
     */
    FUNERAL(new Leave("丧假", 11)),
    /**
     * 其他
     */
    OTHER(new Leave("其他", 12));


    private Leave value;

    LeaveType(Leave value) {
        this.value = value;
    }

    public Leave getValue() {
        return value;
    }

    public void setValue(Leave value) {
        this.value = value;
    }
}
