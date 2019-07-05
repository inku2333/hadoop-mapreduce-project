package demo2;


public class WeblogBean {
	
	private String hotCommodity;//热门商品
    private String popularCategories;// 热门类别
	private String price;//货物价格
    private String hottime;// 交易时间
    
	
	public String getHotCommodity() {
		return hotCommodity;
	}


	public void setHotCommodity(String hotCommodity) {
		this.hotCommodity = hotCommodity;
	}


	public String getPopularCategories() {
		return popularCategories;
	}


	public void setPopularCategories(String popularCategories) {
		this.popularCategories = popularCategories;
	}
	
	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getHottime() {
		return hottime;
	}


	public void setHottime(String hottime) {
		this.hottime = hottime;
	}

}