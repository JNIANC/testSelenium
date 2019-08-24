/**
  * Copyright 2019 bejson.com 
  */
package com.cjn.task.music.pojo;
import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2019-06-14 10:32:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ObjectInfo {

	/**
     *      "singer":"黄明昊",
     *      "itemId":"24664373",
     *      "contentId":"600927015009000215",
     *      "resourceType":"5",
     *      "singerId":"1112497367",
     *      "title":"《Hard Road》",
     *      "price":"200",
     *      "firstEndDate":"2019-06-01",
     *      "isInFirstdate":"0",
     *      "vipType":"",
     *      "imgItems":[{"imgSizeType":"00","img":"https://content.nf.migu.cn/soe/uniaccess?fileID=22oXC7GN0ma7000A3T000"}]
     */

    /*private Date firstStartDate;
    private String copyrightId;
    private OpNumItem opNumItem;
    private List<TagItems> tagItems;
    private Date issueDate;
    private String contentId;
    private String resourceType;
    private String singerId;
    private Date firstEndDate;
    private String isInFirstdate;
    private String vipType;
    private List<ImgItems> imgItems;*/
    private String pay_singer;
    private String pay_content_id;
    private String pay_title;
    private String pay_price;
	
	public String getPay_singer() {
		return pay_singer;
	}
	public void setPay_singer(String pay_singer) {
		this.pay_singer = pay_singer;
	}
	
	public String getPay_content_id() {
		return pay_content_id;
	}
	public void setPay_content_id(String pay_content_id) {
		this.pay_content_id = pay_content_id;
	}
	
	public String getPay_title() {
		return pay_title;
	}
	public void setPay_title(String pay_title) {
		this.pay_title = pay_title;
	}
	public String getPay_price() {
		return pay_price;
	}
	public void setPay_price(String pay_price) {
		this.pay_price = pay_price;
	}
    
    /*public void setFirstStartDate(Date firstStartDate) {
         this.firstStartDate = firstStartDate;
     }
     public Date getFirstStartDate() {
         return firstStartDate;
     }

    public void setCopyrightId(String copyrightId) {
         this.copyrightId = copyrightId;
     }
     public String getCopyrightId() {
         return copyrightId;
     }

    public void setOpNumItem(OpNumItem opNumItem) {
         this.opNumItem = opNumItem;
     }
     public OpNumItem getOpNumItem() {
         return opNumItem;
     }

    public void setTagItems(List<TagItems> tagItems) {
         this.tagItems = tagItems;
     }
     public List<TagItems> getTagItems() {
         return tagItems;
     }
*/
    

}