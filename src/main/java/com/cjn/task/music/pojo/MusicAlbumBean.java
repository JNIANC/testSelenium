/**
  * Copyright 2019 bejson.com 
  */
package com.cjn.task.music.pojo;
import java.util.Date;

/**
 * Auto-generated: 2019-06-14 10:32:35
 *
 * @author jnc (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MusicAlbumBean {

	/*
    {
    "relationStatus": 1,
    "createTime": "2019-06-13 17:26:29.045",
    "objectInfo": {
        "firstStartDate": "2019-06-14",
        "copyrightId": "60050402939",
        "opNumItem": {
            "followNum": 0,
            "subscribeNumDesc": "0",
            "keepNumDesc": "0",
            "orderNumByTotal": 0,
            "thumbNumDesc": "0",
            "thumbNum": 0,
            "orderNumByTotalDesc": "0",
            "bookingNumDesc": "0",
            "orderNumByWeekDesc": "0",
            "livePlayNum": 0,
            "playNum": 213,
            "popularNumDesc": "0",
            "orderNumByWeek": 0,
            "followNumDesc": "0",
            "popularNum": 0,
            "commentNum": 0,
            "keepNum": 0,
            "shareNum": 57,
            "playNumDesc": "213",
            "bookingNum": 0,
            "commentNumDesc": "0",
            "subscribeNum": 0,
            "shareNumDesc": "57"
        },
        "tagItems": [
            {
                "tagDesc": "‘§ €",
                "tagName": "2.0 ‘§ €",
                "tagId": "17009960",
                "tagPicUrl": "https://content.nf.migu.cn/soe/uniaccess?code=MjJvWEM3R04wcDlEMDAwMU42MDAwfDIwMzcwNDIxMTA1ODA4%7C9A3617DB6DF4D1B6432275B6F99DA23F21C3C26CCB2B891FC9479080E7FCE3F2",
                "resourceType": "2034"
            }
        ],
        "singer": "ONER",
        "issueDate": "2019-06-14",
        "itemId": "26066853",
        "contentId": "600927015009000252",
        "resourceType": "5",
        "singerId": "1108513577",
        "title": "ŒËª·",
        "firstEndDate": "2019-09-14",
        "price": "1500",
        "isInFirstdate": "1",
        "vipType": "",
        "imgItems": [
            {
                "imgSizeType": "00",
                "img": "https://content.nf.migu.cn/soe/uniaccess?fileID=22oXC7GN0og90000mR000"
            }
        ]
    },
    "relationType": 4019,
    "updateTime": "2019-06-14 10:07:10.108",
    "contentId": "26067596"
}
     */
    private int relationStatus;
    private Date createTime;
    private ObjectInfo objectInfo;
    private int relationType;
    private Date updateTime;
    private String contentId;
    public void setRelationStatus(int relationStatus) {
         this.relationStatus = relationStatus;
     }
     public int getRelationStatus() {
         return relationStatus;
     }

    public void setCreateTime(Date createTime) {
         this.createTime = createTime;
     }
     public Date getCreateTime() {
         return createTime;
     }

    public void setObjectInfo(ObjectInfo objectInfo) {
         this.objectInfo = objectInfo;
     }
     public ObjectInfo getObjectInfo() {
         return objectInfo;
     }

    public void setRelationType(int relationType) {
         this.relationType = relationType;
     }
     public int getRelationType() {
         return relationType;
     }

    public void setUpdateTime(Date updateTime) {
         this.updateTime = updateTime;
     }
     public Date getUpdateTime() {
         return updateTime;
     }

    public void setContentId(String contentId) {
         this.contentId = contentId;
     }
     public String getContentId() {
         return contentId;
     }

}