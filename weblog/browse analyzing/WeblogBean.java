package demo2;


public class WeblogBean {
	
	private String hotCommodity;//������Ʒ
    private String popularCategories;// �������
    
	
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

//
//	@Override
//	public String toString() {
//        StringBuilder sb = new StringBuilder();
//		sb.append(this.attribute).append("\t");
//        sb.append(this.time).append("\t");
//        sb.append(this.body_bytes_sent).append("\t");
//		//sb.append(this.valid);
//
//        return sb.toString();
//	}
}